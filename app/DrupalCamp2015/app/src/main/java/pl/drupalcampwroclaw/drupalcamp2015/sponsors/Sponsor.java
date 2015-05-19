package pl.drupalcampwroclaw.drupalcamp2015.sponsors;

import android.graphics.ImageFormat;


public class Sponsor {

    private String name;
    private ImageFormat logo;

    public Sponsor(String name) {
        this.name = name;
        this.logo = new ImageFormat();
    }

    public Sponsor(String name, ImageFormat logo) {
        this.name = name;
        this.logo = logo;
    }

    /**
     * Get sponsor name.
     */
    public String getNameSponsor() {
        return this.name;
    }

    /**
     * Get logo sponsor.
     */
    public ImageFormat getImageFormat() {
        return this.logo;
    }

}
