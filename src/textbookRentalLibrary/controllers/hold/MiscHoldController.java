package textbookRentalLibrary.controllers.hold;

import model.patron.Patron;

public class MiscHoldController extends PlaceHoldController {
	
	public MiscHoldController() {
		super();
	}

	@Override
	public void displayHolds() {
		this.displayPatronsWith(" with misc. holds ", super.getManage().getAllPatronsWithMiscHolds());
		
	}

	@Override
	public boolean markHold() {
		return super.confirmHoldForType("Miscellaneous") ? this.markMiscHold() : false;
	}
	
	private boolean markMiscHold() {
		
		Patron thePatron = super.queryDB().locatePatronInDB();
		
		if (thePatron == null) {
			return false;
		}
		
		String item = super.getInput().askForString("What item was found: ");
		String location = super.getInput().askForString("Where was it found: ");

		return this.placeLostAndFoundHold(thePatron, item, location);
	}
	
	public boolean placeLostAndFoundHold(Patron offendingPatron, String item, String location) {
		int holdTally = super.getHoldTotal();
		offendingPatron.placeLostAndFoundHold(item, location);
		return super.holdsUpdatedCorrectly(++holdTally);
	}
}
