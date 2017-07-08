package textbookRentalLibrary.controllers.hold;

import java.util.List;

import model.Manager;
import model.patron.Patron;

public abstract class PlaceHoldController extends HoldController {
	
	private Manager manage;

	public PlaceHoldController() {
		super();
		this.manage = new Manager();
	}
	
	protected Manager getManage() {
		return manage;
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
}
