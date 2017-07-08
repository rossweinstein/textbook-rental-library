package textbookRentalLibrary.controllers.hold;

import model.copy.Copy;
import model.patron.Patron;

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
		
		Patron offendingPatron = super.getDB().locatePatronInDB();

		if (offendingPatron == null) {
			return false;
		}

		Copy damagedCopy = super.getDB().locateCopyInDB();

		if (damagedCopy == null) {
			return false;
		}

		int fineAmount = super.enterFineAmout();
		return super.getManage().markDamageHold(offendingPatron, damagedCopy, fineAmount);
	}
}
