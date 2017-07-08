package textbookRentalLibrary.controllers.hold;

import model.copy.Copy;
import model.patron.Patron;

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

		Patron offendingPatron = super.getDB().locatePatronInDB();

		if (offendingPatron == null) {
			return false;
		}

		Copy lostCopy = super.getDB().locateCopyInDB();

		if (lostCopy == null) {
			return false;
		}

		int fineAmount = super.enterFineAmout();
		return super.getManage().markLostHold(offendingPatron, lostCopy, fineAmount);

	}

}
