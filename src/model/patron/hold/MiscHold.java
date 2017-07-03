package model.patron.hold;

public class MiscHold extends Hold {

	private String foundItem;
	private String location;

	public MiscHold(String foundItem, String location) {
		super(0, null);
		this.foundItem = foundItem;
		this.location = location;
	}

	@Override
	public String getHoldMessage() {
		return "A(n) " + this.foundItem + " with your name on it was found in the " + this.location
				+ ". Have you picked it up yet?";
	}
}
