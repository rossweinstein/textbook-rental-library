package textbookRentalLibrary.controllers.hold;

import model.copy.Copy;
import model.patron.Patron;
import model.patron.hold.HoldType;

/**
 * This controller interacts with our UnshelvedCopyHold class to mark a recently
 * returned by unshelved Copy as hold on the offending Patron's record.
 * 
 * @author Ross Weinstein
 *
 */

public class UnshelvedHoldController extends PlaceHoldController {
	
	@Override
	public void displayHolds() {
		this.displayPatronsWith(" with unshelved holds ", super.queryDB().getAllPatronsWithUnshelvedHolds());
		
	}

	@Override
	public boolean markHold() {
		return super.confirmHoldForType("Unshelved") ? this.markUnshelvedHold() : false;
	}
	
	private boolean markUnshelvedHold() {
		
		Copy unshelvedCopy = super.queryDB().locateCopyInDB();
		
		if (super.unableToFindCopy(unshelvedCopy)) {
			return false;
		}
		
		Patron offendingPatron = unshelvedCopy.getLastPersonToCheckOut();
		
		if (super.unableToFindPatron(offendingPatron)) {
			return false;
		}
		
		return this.successfulHoldMarked(offendingPatron, unshelvedCopy);
	}
	
	private boolean successfulHoldMarked(Patron offendingPatron, Copy damagedCopy) {
		int fineAmount = super.enterFineAmout();
		return this.markingHold(offendingPatron, damagedCopy, fineAmount);
	}
	
	public boolean markingHold(Patron offendingPatron, Copy lostCopy, int fineAmount) {
		return super.placeHold(offendingPatron, lostCopy, fineAmount, HoldType.UNSHELVED);
	}
}
