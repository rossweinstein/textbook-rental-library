package textbookRentalLibrary.controllers;

import java.util.List;

import model.Manager;
import model.copy.Copy;
import model.patron.Patron;
import textbookRentalLibrary.userInput.InputHelper;

/**
 * This class controls all the functions that a manager can do: See all records,
 * see all records with holds, mark holds, generate overdue notices
 * 
 * @author Ross Weinstein
 *
 */
public class ManagerialFunctionsController {

	private InputHelper input;
	private Manager manage;
	private DatabaseSearch db;

	public ManagerialFunctionsController() {
		this.input = new InputHelper();
		this.manage = new Manager();
		this.db = new DatabaseSearch();
	}

	/********** GENERAL MANAGER **************************************/

	public void generateHoldNotices() {

		if (this.manage.canGenerateHoldNotices()) {
			System.out.println("Overdue notices generated...\n");
		} else {
			System.out.println("There are no holds in the system.");
		}
	}

	public void displayAllPatrons() {
		this.displayPatronsWith(" ", this.manage.getAllPatronsInTRL());
	}

	/********** OVERDUE HOLDS **************************************/

	public void displayPatronsWithUnreturnedTextbooks() {
		this.displayPatronsWith(" with unreturned textbooks ", this.manage.getAllPatronsWithUnreturnedTextBooks());
	}

	public void displayPatronsWithOverdueHolds() {
		this.displayPatronsWith(" with overdue holds ", this.manage.getAllPatronsWithOverdueHolds());
	}

	public boolean markOverdueHolds() {

		boolean markHolds = this.input.askBinaryQuestion("Mark Overdue Holds? (y/n)", "y", "n");

		if (markHolds) {
			int fineAmount = this.input.askForInteger("Fine Amount: ");
			return this.manage.markOverdueHolds(fineAmount);
		}
		return false;
	}

	/********** UNSHELVED HOLDS **************************************/

	public void displayPatronsWithUnshelvedHolds() {
		this.displayPatronsWith(" with unshelved holds ", this.manage.getAllPatronsWithUnshelvedHolds());
	}

	public boolean markUnshelvedHold() {

		boolean markHold = this.input.askBinaryQuestion("Mark Unshelved Hold? (y/n)", "y", "n");

		if (markHold) {

			Patron offendingPatron = this.findPatronInDB();

			if (offendingPatron == null) {
				return false;
			}

			Copy damagedCopy = this.findCopyInDB();

			if (damagedCopy == null) {
				return false;
			}

			int fineAmount = this.input.askForInteger("Fine Amount: ");
			return this.manage.markUnshelevedHold(offendingPatron, damagedCopy, fineAmount);

		}
		return false;
	}

	/********** DAMAGED HOLDS **************************************/

	public void displayPatronsWithDamageHolds() {
		this.displayPatronsWith(" with damaged textbook holds ", this.manage.getAllPatronsWithDamageHolds());
	}

	public boolean markDamageHold() {

		boolean markHold = this.input.askBinaryQuestion("Mark Damage Hold? (y/n)", "y", "n");

		if (markHold) {

			Patron offendingPatron = this.findPatronInDB();

			if (offendingPatron == null) {
				return false;
			}

			Copy damagedCopy = this.findCopyInDB();

			if (damagedCopy == null) {
				return false;
			}

			int fineAmount = this.input.askForInteger("Fine Amount: ");
			return this.manage.markDamageHold(offendingPatron, damagedCopy, fineAmount);

		}
		return false;
	}

	/********** MISC HOLDS **************************************/

	public void displayPatronsWithMiscHolds() {
		this.displayPatronsWith(" with misc. holds ", this.manage.getAllPatronsWithMiscHolds());
	}

	public boolean markMiscHold() {

		boolean markHold = this.input.askBinaryQuestion("Mark Miscellaneous Hold? (y/n)", "y", "n");

		if (markHold) {

			Patron thePatron = this.findPatronInDB();

			if (thePatron == null) {
				return false;
			}

			String item = this.input.askForString("What item was found: ");
			String location = this.input.askForString("Where was it found: ");

			return this.manage.markMiscHold(thePatron, item, location);

		}
		return false;
	}

	private void displayPatronsWith(String holdType, List<Patron> holdList) {

		if (holdList.size() == 0) {
			System.out.println("There are no currently no patrons" + holdType + "in the system");
		} else {
			this.printDesiredPatrons(holdList);
		}
	}

	/********** HELPER METHODS **************************************/

	private void printDesiredPatrons(List<Patron> thePatrons) {
		for (int i = 0; i < thePatrons.size(); i++) {
			System.out.println((i + 1) + ": " + thePatrons.get(i));
		}
	}

	private Patron findPatronInDB() {

		Patron offendingPatron = this.db.locatePatronInDB();

		if (offendingPatron == null) {
			System.out.println("Unable to locate Patron in system...");
		}

		return offendingPatron;
	}

	private Copy findCopyInDB() {

		Copy theCopy = this.db.locateCopyInDB();

		if (theCopy == null) {
			System.out.println("Unable to locate Copy in system...");
		}
		return theCopy;
	}
}
