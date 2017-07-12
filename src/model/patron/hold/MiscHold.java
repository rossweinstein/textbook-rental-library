package model.patron.hold;

/**
 * A miscellaneous hold is the only hold that does not carry a fine. These holds
 * are placed if an item which belongs to a patron, say a backpack or notebook,
 * is found in the Library. This allows for when Patron uses the Library, that a
 * notice will be generated so the Patron knows to pick up their item.
 * 
 * @author Ross Weinstein
 *
 */

public class MiscHold extends Hold {

	private String foundItem;
	private String location;

	public MiscHold(String foundItem, String location) {
		super(0, null);
		this.foundItem = foundItem;
		this.location = location;
	}

	@Override
	public boolean equals(Object o) {

		// self check
		if (this == o) {
			return true;
		}

		// null check and type check
		if (o == null || !(o instanceof MiscHold)) {
			return false;
		}
		// cast and comparisons
		MiscHold otherMiscHold = (MiscHold) o;
		return this.foundItem.equals(otherMiscHold.foundItem) && this.location.equals(otherMiscHold.location);
	}

	@Override
	public String getHoldMessage() {
		return "A(n) " + this.foundItem + " with your name on it was found in the " + this.location
				+ ". Have you picked it up yet?";
	}
}
