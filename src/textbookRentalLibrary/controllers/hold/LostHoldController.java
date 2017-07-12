package textbookRentalLibrary.controllers.hold;

import model.copy.Copy;
import model.patron.Patron;
import model.patron.hold.HoldType;

public class LostHoldController extends PlaceHoldController {

	public LostHoldController() {
		super();
	}

	@Override
	public void displayHolds() {
		this.displayPatronsWith(" with lost textbook holds ", super.getManage().getAllPatronsWithLostHolds());
	}

	@Override
	public boolean markHold() {
		return super.confirmHoldForType("Lost") ? this.markLostHold() : false;
	}

	private boolean markLostHold() {

		Patron offendingPatron = super.queryDB().locatePatronInDB();

		if (offendingPatron == null) {
			return false;
		}

		Copy lostCopy = super.queryDB().locateCopyInDB();

		if (lostCopy == null) {
			return false;
		}

		return this.successfulHoldMarked(offendingPatron, lostCopy);
	}
	
	private boolean successfulHoldMarked(Patron offendingPatron, Copy damagedCopy) {
		int fineAmount = super.enterFineAmout();
		return this.markingHold(offendingPatron, damagedCopy, fineAmount);
	}
	
	public boolean markingHold(Patron offendingPatron, Copy lostCopy, int fineAmount) {
		return this.placePostCheckInHold(offendingPatron, lostCopy, fineAmount, HoldType.LOST);
	}
}
