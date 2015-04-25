package pl.drupalcampwroclaw.drupalcamp2015;

import android.app.ActionBar;
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
        tabSpec.setIndicator("Sessions");
        tabHost.addTab(tabSpec);

        // Add tab - People.
        tabSpec = tabHost.newTabSpec("people");
        tabSpec.setContent(R.id.tabPeople);
        tabSpec.setIndicator("People");
        tabHost.addTab(tabSpec);

        // Add tab - Web site.
        tabSpec = tabHost.newTabSpec("website");
        tabSpec.setContent(R.id.tabWebSite);
        tabSpec.setIndicator("Web Site");
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
                Toast.makeText(this, "Refresh data...", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onContextItemSelected(item);
    }

}
