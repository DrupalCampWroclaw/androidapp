package pl.drupalcampwroclaw.drupalcamp.sponsors;

public class GroupSponsorsItem implements InterfaceSponsor {

	private final String title;
	
	public GroupSponsorsItem(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

    @Override
	public boolean isGroup() {
		return true;
	}

}
