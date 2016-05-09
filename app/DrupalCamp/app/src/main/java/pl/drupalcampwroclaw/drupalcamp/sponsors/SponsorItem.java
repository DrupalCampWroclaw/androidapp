package pl.drupalcampwroclaw.drupalcamp.sponsors;


public class SponsorItem implements InterfaceSponsor {

    /**
     * Sponsor name.
     */
	private final String title;

    /**
     * Resource identifier logo.
     */
	private final int logo_id;

    /**
     * Constructor sponsor.
     *
     * @param title Sponsor name.
     */
    public SponsorItem(String title) {
        this.title = title;
        this.logo_id = 0;
    }

    /**
     * Constructor sponsor.
     *
     * @param title Sponsor name.
     * @param image_resource Resource identifier logo.
     */
	public SponsorItem(String title, int image_resource) {
		this.title = title;
        this.logo_id = image_resource;
	}

    public String getTitle() {
        return this.title;
    }

    public int getImageIdResource() {
        return this.logo_id;
    }

    @Override
	public boolean isGroup() {
		return false;
	}

}
