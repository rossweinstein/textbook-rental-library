package model.patron.hold;

import model.copy.Copy;

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
