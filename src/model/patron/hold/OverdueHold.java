package model.patron.hold;

import model.copy.Copy;

/**
 * An OverdueHold is placed on a Patron's record when they keep a textbook
 * longer than its due date.
 * 
 * @author rweinstein
 *
 */

public class OverdueHold extends Hold {

	public OverdueHold(int fineAmount, Copy copy) {
		super(fineAmount, copy);
	}

	@Override
	public boolean equals(Object o) {

		// self check
		if (this == o) {
			return true;
		}

		// null check and type check
		if (o == null || !(o instanceof OverdueHold)) {
			return false;
		}

		// cast and comparisons
		OverdueHold otherOverdueCopy = (OverdueHold) o;
		return super.getFineAmount() == otherOverdueCopy.getFineAmount()
				&& super.getHoldCopy().equals(otherOverdueCopy.getHoldCopy());
	}

	@Override
	public String getHoldMessage() {
		return this.holdDescription("OVERDUE") + "\nCopy: " + super.getHoldCopy().getTitle() + " [ID: "
				+ super.getHoldCopy().getCopyID() + "]";
	}
}
