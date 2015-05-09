package pl.drupalcampwroclaw.drupalcamp2015;

import android.app.ActionBar;
import android.app.Application;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tabs.
        this.onCreateTabs();
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
                String refresh_data_message = getString(R.string.refresh_data);
                Toast.makeText(this, refresh_data_message, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onContextItemSelected(item);
    }

}
