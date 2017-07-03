package model.patron.hold;

import model.copy.Copy;

public class CopyDamagedHold extends Hold {

	public CopyDamagedHold(int fineAmount, Copy copy) {
		super(fineAmount, copy);
	}

	@Override
	public String getHoldMessage() {
		return this.holdDescription("DAMAGED") + "\nCopy: " + super.getHoldCopy().getTitle() + " [ID: "
				+ super.getHoldCopy().getCopyID() + "]";
	}
}
