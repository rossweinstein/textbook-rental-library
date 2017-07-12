package model.patron.hold;

import model.copy.Copy;

/**
 * A CopyDamagedHold is when a Patron tries to return a textbook in a condition
 * worse than when it was initially checked out to that Patron.
 * 
 * @author Ross Weinstein
 *
 */
public class DamagedHold extends Hold {

	public DamagedHold(int fineAmount, Copy copy) {
		super(fineAmount, copy);
	}

	@Override
	public boolean equals(Object o) {

		// self check
		if (this == o) {
			return true;
		}

		// null check and type check
		if (o == null || !(o instanceof DamagedHold)) {
			return false;
		}
		// cast and comparisons
		DamagedHold otherDamagedCopy = (DamagedHold) o;
		return super.getFineAmount() == otherDamagedCopy.getFineAmount()
				&& super.getHoldCopy().equals(otherDamagedCopy.getHoldCopy());
	}

	@Override
	public String getHoldMessage() {
		return this.holdDescription("DAMAGED") + "\nCopy: " + super.getHoldCopy().getTitle() + " [ID: "
				+ super.getHoldCopy().getCopyID() + "]";
	}
}
