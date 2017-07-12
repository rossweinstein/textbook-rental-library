package textbookRentalLibrary.controllers.hold;

import model.patron.Patron;

/**
 * This controller interacts with our MistHold class to mark a forgotten item in
 * the Library as a hold to hopefully return to a Patron at a later date.
 * 
 * @author Ross Weinstein
 *
 */

public class MiscHoldController extends PlaceHoldController {

	@Override
	public void displayHolds() {
		this.displayPatronsWith(" with misc. holds ", super.queryDB().getAllPatronsWithMiscHolds());

	}

	@Override
	public boolean markHold() {
		return super.confirmHoldForType("Miscellaneous") ? this.markMiscHold() : false;
	}

	private boolean markMiscHold() {

		Patron thePatron = super.queryDB().locatePatronInDB();

		if (super.unableToFindPatron(thePatron)) {
			return false;
		}

		String item = super.getInput().askForString("What item was found: ");
		String location = super.getInput().askForString("Where was it found: ");

		return this.placeLostAndFoundHold(thePatron, item, location);
	}

	public boolean placeLostAndFoundHold(Patron offendingPatron, String item, String location) {
		int holdTally = super.queryDB().getHoldTotal();
		offendingPatron.placeLostAndFoundHold(item, location);
		return super.holdsUpdatedCorrectly(++holdTally);
	}
}
