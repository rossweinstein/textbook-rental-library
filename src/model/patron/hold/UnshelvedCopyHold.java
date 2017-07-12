package model.patron.hold;

import model.copy.Copy;

/**
 * An Unshelved hold is placed on a Patron's record when they do not promptly
 * shelve their textbooks after checking them in.
 * 
 * @author Ross Weinstein
 *
 */

public class UnshelvedCopyHold extends Hold {

	public UnshelvedCopyHold(int fineAmount, Copy copy) {
		super(fineAmount, copy);
	}

	@Override
	public boolean equals(Object o) {

		// self check
		if (this == o) {
			return true;
		}

		// null check and type check
		if (o == null || !(o instanceof UnshelvedCopyHold)) {
			return false;
		}
		// cast and comparisons
		UnshelvedCopyHold otherUnshelvedCopy = (UnshelvedCopyHold) o;
		return super.getFineAmount() == otherUnshelvedCopy.getFineAmount()
				&& super.getHoldCopy().equals(otherUnshelvedCopy.getHoldCopy());
	}

	@Override
	public String getHoldMessage() {
		return super.holdDescription("UNSHELVED") + "\nCopy: " + super.getHoldCopy().getTitle() + " [ID: "
				+ super.getHoldCopy().getCopyID() + "]";
	}

}
