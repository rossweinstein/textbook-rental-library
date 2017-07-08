package textbookRentalLibrary.controllers.hold;

import model.copy.Copy;
import model.patron.Patron;

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
		
		Copy unshelvedCopy = super.getDB().locateCopyInDB();
		
		if (unshelvedCopy == null) {
			return false;
		}
		
		Patron offendingPatron = unshelvedCopy.getLastPersonToCheckOut();
		
		if (offendingPatron == null) {
			return false;
		}
		
		int fineAmount = super.enterFineAmout();
		return super.getManage().markUnshelevedHold(offendingPatron, unshelvedCopy, fineAmount);
	}
}
