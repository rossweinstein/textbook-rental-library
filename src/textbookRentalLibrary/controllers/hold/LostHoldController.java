package textbookRentalLibrary.controllers.hold;

import model.copy.Copy;
import model.patron.Patron;
import model.patron.hold.HoldType;

/**
 * This controller interacts with our LostHold class to mark an unreturned
 * Copy as lost (or presumably lost).
 * 
 * @author Ross Weinstein
 *
 */

public class LostHoldController extends PlaceHoldController {

	@Override
	public void displayHolds() {
		this.displayPatronsWith(" with lost textbook holds ", super.queryDB().getAllPatronsWithLostHolds());
	}

	@Override
	public boolean markHold() {
		return super.confirmHoldForType("Lost") ? this.markLostHold() : false;
	}

	private boolean markLostHold() {

		Patron offendingPatron = super.queryDB().locatePatronInDB();

		if (super.unableToFindPatron(offendingPatron)) {
			return false;
		}

		Copy lostCopy = super.queryDB().locateCopyInDB();

		if (super.unableToFindCopy(lostCopy)) {
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
