package textbookRentalLibrary.controllers.hold;

import model.copy.Copy;
import model.patron.Patron;
import model.patron.hold.HoldType;

/**
 * This controller interacts with our CopyDamagedHold class to mark a recently
 * returned Copy as damaged.
 * 
 * @author Ross Weinstein
 *
 */

public class DamageHoldController extends PlaceHoldController {

	@Override
	public void displayHolds() {
		this.displayPatronsWith(" with damaged textbook holds ", super.queryDB().getAllPatronsWithDamageHolds());
	}

	@Override
	public boolean markHold() {
		return super.confirmHoldForType("Damage") ? this.markDamageHold() : false;
	}

	private boolean markDamageHold() {

		Patron offendingPatron = super.queryDB().locatePatronInDB();

		if (super.unableToFindPatron(offendingPatron)) {
			return false;
		}

		Copy damagedCopy = super.queryDB().locateCopyInDB();

		if (super.unableToFindCopy(damagedCopy)) {
			return false;
		}

		return this.successfulHoldMarked(offendingPatron, damagedCopy);
	}

	private boolean successfulHoldMarked(Patron offendingPatron, Copy damagedCopy) {
		int fineAmount = super.enterFineAmout();
		return this.markingHold(offendingPatron, damagedCopy, fineAmount);
	}

	private boolean markingHold(Patron offendingPatron, Copy damagedCopy, int fineAmount) {
		return super.placePostCheckInHold(offendingPatron, damagedCopy, fineAmount, HoldType.DAMAGED);
	}

}
