package textbookRentalLibrary.controllers.hold;

import model.copy.Copy;
import model.patron.Patron;
import model.patron.hold.HoldType;

public class DamageHoldController extends PlaceHoldController {
	
	public DamageHoldController() {
		super();
	}
	
	@Override
	public void displayHolds() {
		this.displayPatronsWith(" with damaged textbook holds ", super.getManage().getAllPatronsWithDamageHolds());
	}
	
	@Override
	public boolean markHold() {
		return super.confirmHoldForType("Damage") ? this.markDamageHold() : false;
	}
	
	private boolean markDamageHold() {
		
		Patron offendingPatron = super.queryDB().locatePatronInDB();

		if (offendingPatron == null) {
			return false;
		}

		Copy damagedCopy = super.queryDB().locateCopyInDB();

		if (damagedCopy == null) {
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
