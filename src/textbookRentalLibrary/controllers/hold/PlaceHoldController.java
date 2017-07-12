package textbookRentalLibrary.controllers.hold;

import java.util.List;

import model.copy.Copy;
import model.patron.Patron;
import model.patron.hold.Hold;
import model.patron.hold.HoldFactory;
import model.patron.hold.HoldType;
import model.patron.hold.MiscHold;

public abstract class PlaceHoldController extends HoldController {
	

	public PlaceHoldController() {
		super();
		
	}

	
	public abstract void displayHolds();
	public abstract boolean markHold();
	
	protected int enterFineAmout() {
		return super.getInput().askForInteger("Fine Amount: ");
	}
	
	protected boolean confirmHoldForType(String holdType) {
		return super.getInput().askBinaryQuestion("Mark " + holdType + " Hold? (y/n)", "y", "n");
	}
	
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
	
	private boolean patronNotLastToCheckOutCopy(Patron patron, Copy copy) {
		return !patron.equals(copy.getLastPersonToCheckOut());
	}

	
	protected boolean placePostCheckInHold(Patron offendingPatron, Copy unshelvedCopy, int fineAmount, HoldType type) {

		int holdTally = this.getHoldTotal();

		if (this.patronNotLastToCheckOutCopy(offendingPatron, unshelvedCopy)) {
			return false;
		}

		this.placeHoldOnRecord(offendingPatron, type, fineAmount, unshelvedCopy);

		return this.holdsUpdatedCorrectly(++holdTally);
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

	public void placeLostAndFoundHold(Patron offendingPatron, String item, String location) {
		offendingPatron.getAllHolds().add(new MiscHold(item, location));
	}
}
