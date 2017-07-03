package model.patron.hold;

import model.copy.Copy;

public class UnshelevedCopyHold extends Hold {

	public UnshelevedCopyHold(int fineAmount, Copy copy) {
		super(fineAmount, copy);
	}

	@Override
	public String getHoldMessage() {
		return super.holdDescription("UNSHELVED") + "\nCopy: " + super.getHoldCopy().getTitle() + " [ID: "
				+ super.getHoldCopy().getCopyID() + "]";
	}

}
