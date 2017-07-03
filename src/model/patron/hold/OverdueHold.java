package model.patron.hold;

import model.copy.Copy;

public class OverdueHold extends Hold {

	public OverdueHold(int fineAmount, Copy copy) {
		super(fineAmount, copy);
	}

	@Override
	public String getHoldMessage() {
		return this.holdDescription("OVERDUE") + "\nCopy: " + super.getHoldCopy().getTitle() + " [ID: "
				+ super.getHoldCopy().getCopyID() + "]";
	}
}
