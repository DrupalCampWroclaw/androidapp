package pl.drupalcampwroclaw.drupalcamp2015;

import android.app.ProgressDialog;
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

    private SessionAdapter sessions_list_friday;
    private SessionAdapter sessions_list_saturday;
    private SessionAdapter sessions_list_sunday;

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
        sessions_list_friday  = new SessionAdapter(new ArrayList<Session>(), this);
        ListView lView = (ListView) findViewById(R.id.listSession_Friday);

        lView.setAdapter(sessions_list_friday);

        String json_server = getString(R.string.json_server);
        String path_session = getString(R.string.session_json_path);

        (new AsyncListViewLoader()).execute(json_server, path_session);
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
                // @TODO: Refresh data (file JSON).
                String refresh_data_message = getString(R.string.refresh_data_message);
                Toast.makeText(this, refresh_data_message, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onContextItemSelected(item);
    }

    private class AsyncListViewLoader extends AsyncTask<String, Void, List<Session>> {
        private final ProgressDialog dialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPostExecute(List<Session> result) {
            super.onPostExecute(result);

            // Hide dialog.
            dialog.dismiss();

            sessions_list_friday.setItemList(result);
            sessions_list_friday.notifyDataSetChanged();
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
        protected List<Session> doInBackground(String... params) {
            List<Session> result = new ArrayList<Session>();

            try {
                JSONParser jParser = new JSONParser();
                // Set address server JSON.
                jParser.setUrlServer(params[0]);

                // Load sessions.
                JSONObject json = jParser.getJSONFromUrl(params[1]);
                JSONArray items = null;
                int items_count = 0;
                try {
                    // Load all sessions of the day
                    items = json.getJSONArray("items");
                    items_count = items.length();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (items_count != 0) {
                    for (int i = 0; i < items_count; i++) {
                        try {
                            // Load session.
                            JSONObject item = items.getJSONObject(i);

                            // Session data.
                            String session_name = item.getString("session_name");
                            String speakers_name = item.getString("speaker_name");
                            String time = item.getString("time");
                            String room = item.getString("room");
                            String language = item.getString("language");

                            // Add session to the list.
                            result.add(new Session(session_name, speakers_name, time, room, language));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                return result;
            }
            catch(Throwable t) {
                t.printStackTrace();
            }

            return null;
        }

    }
}
