package pl.drupalcampwroclaw.drupalcamp2015;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    // Sessions day.
    private List<SessionAdapter> sessions_list = new ArrayList<SessionAdapter>();

    // Save JSON to memory phone.
    SharedPreference shared_json = new SharedPreference("json_sessions");

    // Context.
    Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tabs.
        this.onCreateTabs();

        // Load sessions.
        this.onLoadSessions();
    }

    private void onLoadSessions() {

        sessions_list.add(new SessionAdapter(new ArrayList<Session>(), this));      // Day 1.
        sessions_list.add(new SessionAdapter(new ArrayList<Session>(), this));      // Day 2.
        sessions_list.add(new SessionAdapter(new ArrayList<Session>(), this));      // Day 3.

        // Lists.
        ListView lView_friday = (ListView) findViewById(R.id.listSession_Friday);
        ListView lView_saturday = (ListView) findViewById(R.id.listSession_Saturday);
        ListView lView_sunday = (ListView) findViewById(R.id.listSession_Sunday);

        // Set adapter for list.
        lView_friday.setAdapter(sessions_list.get(0));
        lView_saturday.setAdapter(sessions_list.get(1));
        lView_sunday.setAdapter(sessions_list.get(2));

        // Load sessions lists.
        this.loadListsSession();
    }

    /**
     * Create tabs.
     */
    private void onCreateTabs() {
        // Initialization tabs.
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        // Add tab - sessions in friday.
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("session_friday");
        tabSpec.setContent(R.id.tabSession_Friday);
        String sessions_friday_label = getString(R.string.friday);
        tabSpec.setIndicator(sessions_friday_label);
        tabHost.addTab(tabSpec);

        // Add tab - sessions in saturday.
        tabSpec = tabHost.newTabSpec("session_saturday");
        tabSpec.setContent(R.id.tabSession_Saturday);
        String sessions_saturday_label = getString(R.string.saturday);
        tabSpec.setIndicator(sessions_saturday_label);
        tabHost.addTab(tabSpec);

        // Add tab - sessions in sunday.
        tabSpec = tabHost.newTabSpec("session_sunday");
        tabSpec.setContent(R.id.tabSession_Sunday);
        String sessions_sunday_label = getString(R.string.sunday);
        tabSpec.setIndicator(sessions_sunday_label);
        tabHost.addTab(tabSpec);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Action with ID action_refresh was selected.
            case R.id.action_refresh:

                // Flag refresh lists.
                this.refresh = true;

                // Refresh list sessions.
                this.loadListsSession();
                break;
            default:
                break;
        }

        return super.onContextItemSelected(item);
    }

    /**
     * Load lists sessions.
     */
    private void loadListsSession() {

        ConnectionDetector connect = new ConnectionDetector(getApplicationContext());
        JSONObject json = shared_json.getJson(context);

        if (connect.isConnectingToInternet() || (json != null && this.refresh == false)) {
            // URL JSON.
            String json_server = getString(R.string.json_server);
            String path_session = getString(R.string.session_json_path);

            // Thread.
            (new AsyncListViewLoader()).execute(json_server, path_session);
        }
        else {
            // Message no internet.
            String no_connect_message = getString(R.string.no_connect);
            Toast.makeText(this, no_connect_message, Toast.LENGTH_SHORT).show();
        }

    }

    // Flag error
    private int error_no = 0;

    /**
     * Show error.
     */
    private void showError() {
        if (this.error_no == 0) {
            return;
        }

        // Message error.
        String message = null;
        switch (this.error_no) {
            case 100:
                message = getString(R.string.error_connect_json);
                break;
            case 200:
                message = getString(R.string.error_parser_json);
                break;
            case 300:
                message = getString(R.string.error_load_json);
                break;
            default:
        }

        if (message != null) {
            // Show toast.
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Set error number.
     */
    public void setError(int error) {
        if (this.error_no == 0) {
            this.error_no = error;
        }
    }

    /**
     * Clear error.
     */
    public void clearError() {
        this.error_no = 0;
    }

    /**
     * Flag - refresh list sessions.
     */
    private boolean refresh = false;


    private class AsyncListViewLoader extends AsyncTask<String, Void, ArrayList<List>> {
        private final ProgressDialog dialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPostExecute(ArrayList<List> result) {
            super.onPostExecute(result);

            // Refresh the information for lists.
            for(int index = 0; index <= sessions_list.size()-1; index++) {
                // Get sessions with day.
                if (index <= (result.size()-1)) {
                    SessionAdapter session = sessions_list.get(index);
                    session.setItemList(result.get(index));
                    session.notifyDataSetChanged();

                    // Save changed.
                    sessions_list.set(index, session);
                }
            }

            // Hide dialog.
            dialog.dismiss();

            // Message error.
            showError();
            clearError();

            // Flag - refresh list.
            refresh = false;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Show dialog.
            String message = getString(R.string.loading_sessions_message);
            dialog.setMessage(message);
            dialog.show();
        }

        @Override
        protected ArrayList doInBackground(String... params) {
            ArrayList<List> result = new ArrayList<List>();

            try {
                JSONParser jParser = new JSONParser();
                JSONObject json;

                // Load list session with memory phone.
                json = shared_json.getJson(context);

                if (json == null || refresh == true) {
                    // Set address server JSON.
                    jParser.setUrlServer(params[0]);

                    // Load list sessions with file JSON.
                    json = jParser.getJSONFromUrl(params[1]);
                }

                // All sessions (group by day).
                JSONArray days = json.getJSONArray("sessions_data");

                for (int day = 0; day < days.length(); day++) {
                    JSONObject items = null;
                    try {
                        // Load all sessions of the day
                        items = days.getJSONObject(day);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        setError(300);
                    }

                    JSONArray ses = items.getJSONArray("sessions");
                    int ses_count = ses.length();

                    if (ses_count != 0) {
                        List<Session> session_day = new ArrayList<Session>();
                        for (int i = 0; i < ses_count; i++) {
                            try {
                                // Load session.
                                JSONObject item = ses.getJSONObject(i);

                                // Session data.
                                String session_name = item.getString("session_name");
                                String speakers_name = item.getString("speakers_name");
                                String time = item.getString("time_start_end");
                                String room = item.getString("room");
                                String language = item.getString("language");

                                // Add session to the list.
                                session_day.add(new Session(session_name, speakers_name, time, room, language));

                            } catch (JSONException e) {
                                e.printStackTrace();
                                setError(200);
                            }
                        }
                        result.add(session_day);
                    }
                    else {
                        result.add(null);
                    }
                }

                // Save file JSON on memory phone, if success load.
                if (error_no == 0) {
                    shared_json.putJson(context, json);
                }

                return result;
            }
            catch(Throwable t){
                t.printStackTrace();
                setError(100);
            }

            result.add(null);

            return result;
        }


    }
}
