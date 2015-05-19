package pl.drupalcampwroclaw.drupalcamp2015.sponsors;

import java.util.ArrayList;
import java.util.List;


public class SponsorGroup {

    private String name_group;
    private List<Sponsor> sponsors;

    public SponsorGroup(String name) {
        this.name_group = name;
        this.sponsors = new ArrayList<Sponsor>();
    }

    /**
     * Add sponsor to group.
     */
    public void addSponsor(Sponsor sponsor) {
        this.sponsors.add(sponsor);
    }

    /**
     * Get sponsors group name.
     */
    public String getName() {
        return this.name_group;
    }

    /**
     * Get sponsors lists.
     */
    public List<Sponsor> getSponsors() {
        return sponsors;
    }

    /**
     * Get count sponsors in group.
     */
    public int getSize() {
        return this.sponsors.size();
    }

}
