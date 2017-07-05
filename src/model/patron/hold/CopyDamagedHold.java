package model.patron.hold;

import model.copy.Copy;

public class CopyDamagedHold extends Hold {

	public CopyDamagedHold(int fineAmount, Copy copy) {
		super(fineAmount, copy);
	}

	@Override
	public boolean equals(Object o) {

		// self check
		if (this == o) {
			return true;
		}

		// null check and type check
		if (o == null || !(o instanceof CopyDamagedHold)) {
			return false;
		}
		// cast and comparisons
		CopyDamagedHold otherDamagedCopy = (CopyDamagedHold) o;
		return super.getFineAmount() == otherDamagedCopy.getFineAmount()
				&& super.getHoldCopy().equals(otherDamagedCopy.getHoldCopy());
	}

	@Override
	public String getHoldMessage() {
		return this.holdDescription("DAMAGED") + "\nCopy: " + super.getHoldCopy().getTitle() + " [ID: "
				+ super.getHoldCopy().getCopyID() + "]";
	}
}
