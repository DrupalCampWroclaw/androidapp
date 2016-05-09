package pl.drupalcampwroclaw.drupalcamp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import pl.drupalcampwroclaw.drupalcamp.sponsors.InterfaceSponsor;
import pl.drupalcampwroclaw.drupalcamp.sponsors.SponsorsListAdapter;
import pl.drupalcampwroclaw.drupalcamp.sponsors.GroupSponsorsItem;
import pl.drupalcampwroclaw.drupalcamp.sponsors.SponsorItem;


public class SponsorActivity extends ActionBarActivity {

    ArrayList<InterfaceSponsor> items = new ArrayList<InterfaceSponsor>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        // Load lists sponsors.
        this.loadListsSponsors();

        SponsorsListAdapter adapter = new SponsorsListAdapter(this, items);

        ListView lView_sponsors = (ListView) findViewById(R.id.listSponsorsView);
        lView_sponsors.setAdapter(adapter);
    }

    private ArrayList<InterfaceSponsor> loadListsSponsors() {

        // Diamond.
        String diamond_name = getString(R.string.sponsor_diamond);
        items.add(new GroupSponsorsItem(diamond_name));
        items.add(new SponsorItem("Droptica", R.drawable.logo_droptica));

        // Gold.
        // String gold_name = getString(R.string.sponsor_gold);
        // items.add(new GroupSponsorsItem(gold_name));

        // Bronze.
        // String bronze_name = getString(R.string.sponsor_bronze);
        // items.add(new GroupSponsorsItem(bronze_name));

        // Prize.
        String price_name = getString(R.string.sponsor_prize);
        items.add(new GroupSponsorsItem(price_name));
        items.add(new SponsorItem("Helion.pl",  R.drawable.logo_helion));

        return this.items;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Back button.
                this.finish();
                return (true);
        }

        return super.onOptionsItemSelected(item);
    }

}
