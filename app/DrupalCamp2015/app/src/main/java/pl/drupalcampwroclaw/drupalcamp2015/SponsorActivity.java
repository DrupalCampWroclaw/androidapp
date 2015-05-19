package pl.drupalcampwroclaw.drupalcamp2015;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pl.drupalcampwroclaw.drupalcamp2015.sponsors.Sponsor;
import pl.drupalcampwroclaw.drupalcamp2015.sponsors.SponsorGroup;
import pl.drupalcampwroclaw.drupalcamp2015.sponsors.SponsorGroupAdapter;


public class SponsorActivity extends ActionBarActivity {

    // All sponsors.
    private List<SponsorGroup> sponsors_list;

    ListView ListViewSponsors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        this.ListViewSponsors = (ListView) findViewById(R.id.listSponsorsView);

        // Init sponsors.
        this.initSponsors();

        // Load lists Sponsors.
        this.loadListSponsors();
    }

    /**
     * Init sponsors.
     */
    public List<SponsorGroup> initSponsors() {
        this.sponsors_list = new ArrayList<SponsorGroup>();

        // Diamond.
        String diamond_name = getString(R.string.sponsor_diamond);
        SponsorGroup diamond = new SponsorGroup(diamond_name);

        diamond.addSponsor(new Sponsor("Droptica"));

        sponsors_list.add(diamond);


        // Gold.
        String gold_name = getString(R.string.sponsor_gold);
        SponsorGroup gold = new SponsorGroup(gold_name);

        gold.addSponsor(new Sponsor("BlueOK"));

        sponsors_list.add(gold);


        // Bronze.
        String bronze_name = getString(R.string.sponsor_bronze);
        SponsorGroup bronze = new SponsorGroup(bronze_name);

        bronze.addSponsor(new Sponsor("KeyCDN"));

        sponsors_list.add(bronze);



        // Graphics sponsor.
        String graphics_name = getString(R.string.sponsor_graphics);
        SponsorGroup graphics = new SponsorGroup(graphics_name);

        graphics.addSponsor(new Sponsor("X-Coding IT Studio"));

        sponsors_list.add(graphics);


        // Prize.
        String price_name = getString(R.string.sponsor_prize);
        SponsorGroup prize = new SponsorGroup(price_name);

        prize.addSponsor(new Sponsor("DigitalOcean"));
        prize.addSponsor(new Sponsor("Helion.pl"));
        prize.addSponsor(new Sponsor("JetBrains PhpStorm"));

        sponsors_list.add(prize);


        return this.sponsors_list;
    }

    /**
     * Load list Sponsors.
     */
    private void loadListSponsors() {
        // Load lists.
        ListView lView_sponsors = (ListView) findViewById(R.id.listSponsorsView);

        if (this.sponsors_list.size() > 0) {

            for (int i = 0; i < this.sponsors_list.size(); i++) {

                for (int j = 0; j < this.sponsors_list.get(i).getSize(); j++) {
                    // Add group
                    SponsorGroupAdapter sga = new SponsorGroupAdapter(this.sponsors_list, this);
                    ListViewSponsors.setAdapter(sga);
                }

            }

        }

    }

}
