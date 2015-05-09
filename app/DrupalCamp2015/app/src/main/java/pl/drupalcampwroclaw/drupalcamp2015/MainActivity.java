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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private SessionAdapter sessions_list;

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
        sessions_list  = new SessionAdapter(new ArrayList<Session>(), this);
        ListView lView = (ListView) findViewById(R.id.listSession);

        lView.setAdapter(sessions_list);

        (new AsyncListViewLoader()).execute();
    }

    /**
     * Create tabs.
     */
    private void onCreateTabs() {
        // Initialization tabs.
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        // Add tab - Session.
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("sessions");
        tabSpec.setContent(R.id.tabSession);
        String sessions_label = getString(R.string.sessions_label);
        tabSpec.setIndicator(sessions_label);
        tabHost.addTab(tabSpec);

        // Add tab - People.
        tabSpec = tabHost.newTabSpec("people");
        tabSpec.setContent(R.id.tabPeople);
        String people_label = getString(R.string.people_label);
        tabSpec.setIndicator(people_label);
        tabHost.addTab(tabSpec);

        // Add tab - Web site.
        tabSpec = tabHost.newTabSpec("website");
        tabSpec.setContent(R.id.tabWebSite);
        String website_label = getString(R.string.website_label);
        tabSpec.setIndicator(website_label);
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

            sessions_list.setItemList(result);
            sessions_list.notifyDataSetChanged();
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

                // @TODO: Load data with JSON.

                for(int i = 1; i < 100; i++) {
                    result.add(new Session("Session " + i , "John Smith", "11:00 - 12:00", "Room " + i, "en"));
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
