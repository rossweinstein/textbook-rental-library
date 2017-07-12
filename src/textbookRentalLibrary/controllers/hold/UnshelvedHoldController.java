package textbookRentalLibrary.controllers.hold;

import model.copy.Copy;
import model.patron.Patron;
import model.patron.hold.HoldType;

public class UnshelvedHoldController extends PlaceHoldController {
	
	public UnshelvedHoldController() {
		super();
	}
	
	@Override
	public void displayHolds() {
		this.displayPatronsWith(" with unshelved holds ", super.getManage().getAllPatronsWithUnshelvedHolds());
		
	}

	@Override
	public boolean markHold() {
		return super.confirmHoldForType("Unshelved") ? this.markUnshelvedHold() : false;
	}
	
	private boolean markUnshelvedHold() {
		
		Copy unshelvedCopy = super.queryDB().locateCopyInDB();
		
		if (unshelvedCopy == null) {
			return false;
		}
		
		Patron offendingPatron = unshelvedCopy.getLastPersonToCheckOut();
		
		if (offendingPatron == null) {
			return false;
		}
		
		return this.successfulHoldMarked(offendingPatron, unshelvedCopy);
	}
	
	private boolean successfulHoldMarked(Patron offendingPatron, Copy damagedCopy) {
		int fineAmount = super.enterFineAmout();
		return this.markingHold(offendingPatron, damagedCopy, fineAmount);
	}
	
	public boolean markingHold(Patron offendingPatron, Copy lostCopy, int fineAmount) {
		return this.placePostCheckInHold(offendingPatron, lostCopy, fineAmount, HoldType.UNSHELVED);
	}
}
