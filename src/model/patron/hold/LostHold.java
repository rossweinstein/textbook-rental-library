package model.patron.hold;

import model.copy.Copy;

/**
 * A LostHold is when a Patron cannot find a Copy previously checked out. This
 * hold will be placed on their account until they are able to find the book, in
 * which they will pay a fine equal to that of an overdue hold, or if they
 * cannot find the book, they will be fined an amount equal to the value of the
 * book.
 * 
 * @author Ross Weinstein
 *
 */

public class LostHold extends Hold {

	public LostHold(int fineAmount, Copy copy) {
		super(fineAmount, copy);
	}

	@Override
	public boolean equals(Object o) {

		// self check
		if (this == o) {
			return true;
		}

		// null check and type check
		if (o == null || !(o instanceof LostHold)) {
			return false;
		}
		// cast and comparisons
		LostHold otherLostCopy = (LostHold) o;
		return super.getFineAmount() == otherLostCopy.getFineAmount()
				&& super.getHoldCopy().equals(otherLostCopy.getHoldCopy());
	}

	@Override
	public String getHoldMessage() {
		return this.holdDescription("LOST") + "\nCopy: " + super.getHoldCopy().getTitle() + " [ID: "
				+ super.getHoldCopy().getCopyID() + "]";
	}

}
