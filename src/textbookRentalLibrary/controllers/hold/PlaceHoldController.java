package textbookRentalLibrary.controllers.hold;

import java.util.List;

import model.copy.Copy;
import model.patron.Patron;
import model.patron.hold.Hold;
import model.patron.hold.HoldFactory;
import model.patron.hold.HoldType;

/**
 * This class is where we have a few shared methods between our hold controllers.
 * 
 * @author Ross Weinstein
 *
 */

public abstract class PlaceHoldController extends HoldController {

	public abstract void displayHolds();

	public abstract boolean markHold();

	/***** USER INPUT *******************************/
	
	protected int enterFineAmout() {
		return super.getInput().askForInteger("Fine Amount: ");
	}

	protected boolean confirmHoldForType(String holdType) {
		return super.getInput().askBinaryQuestion("Mark " + holdType + " Hold? (y/n)", "y", "n");
	}
	
	/***** DISPLAY METHODS **************************/

	protected void displayPatronsWith(String holdType, List<Patron> holdList) {

		if (holdList.size() == 0) {
			System.out.println("There are no currently no patrons" + holdType + "in the system");
		} else {
			this.printDesiredPatrons(holdList);
		}
	}

	private void printDesiredPatrons(List<Patron> thePatrons) {
		for (int i = 0; i < thePatrons.size(); i++) {
			System.out.println((i + 1) + ": " + thePatrons.get(i).showPatronIDAndName());
		}
	}

	/***** PLACING A HOLD METHODS **************************/

	// Post check in holds apply to damaged, unshelved, and 
	protected boolean placeHold(Patron offendingPatron, Copy unshelvedCopy, int fineAmount, HoldType type) {

		int holdTally = this.getHoldTotal();

		if (this.patronNotLastToCheckOutCopy(offendingPatron, unshelvedCopy)) {
			return false;
		}

		this.placeHoldOnRecord(offendingPatron, type, fineAmount, unshelvedCopy);

		return this.holdsUpdatedCorrectly(++holdTally);
	}
	
	private boolean patronNotLastToCheckOutCopy(Patron patron, Copy copy) {
		return !patron.equals(copy.getLastPersonToCheckOut());
	}

	protected boolean placeHoldOnRecord(Patron offendingPatron, HoldType type, int fineAmount, Copy copy) {

		Hold copyHold = HoldFactory.createHold(type, fineAmount, copy);

		if (this.holdNotAlreadyPlacedOnPatron(offendingPatron, copyHold)) {
			return offendingPatron.getAllHolds().add(copyHold);
		}
		return false;
	}

	private boolean holdNotAlreadyPlacedOnPatron(Patron offendingPatron, Hold copyHold) {
		return !offendingPatron.getAllHolds().contains(copyHold);
	}
}
