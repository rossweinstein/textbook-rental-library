package textbookRentalLibrary.managerFunctions;

import fakeDatabase.DBConnect;
import fakeDatabase.FakeDB;
import helpers.InputHelper;
import helpers.MenuBuilder;
import model.copy.Copy;
import model.patron.Patron;

/**
 * This class creates a command line menu which lists and calls all the
 * functions a manager can do
 * 
 * @author Ross Weinstein
 *
 */
public class ManagerialMenu {

	private InputHelper input;
	private MenuBuilder menu;
	private ManagerialFunctionsController manage;
	private DBConnect db;

	public ManagerialMenu() {
		this.input = new InputHelper();
		this.menu = new MenuBuilder("Manager Functions", "Display All Patrons", "Display Patrons With Overdue Notices",
				"Mark Overdue Holds", "Mark Unshelved Holds", "Submit Misc Hold", "Generate Hold Notices", "Exit");
		this.manage = new ManagerialFunctionsController();
		this.db = new DBConnect();
	}

	public void managerMenu() {

		boolean runApp = true;
		while (runApp) {

			System.out.println(this.menu.displayMenuWithoutBanner());
			runApp = getSelection(runApp);
		}
	}

	private boolean getSelection(boolean runApp) {

		int selection = this.input.askForSelection(this.menu.getMenuItems());

		if (selection == 1) {
			displayAllPatrons();
		} else if (selection == 2) {
			displayPatronsWithOverdueBooks();
		} else if (selection == 3) {
			placeHoldsOnPatronsWithOverdueBooks();
		} else if (selection == 4) {
			placeHoldsOnPatronsWhoDidNotShelveTheirBooks();
		} else if (selection == 5) {
			placeHoldsOnPatronsMisc();
		} else if (selection == 6) {
			generatePatronHoldNotices();
		} else {
			runApp = false;
		}
		return runApp;
	}

	private void generatePatronHoldNotices() {
		System.out.println();
		this.manage.generateHoldNotices();
	}

	private void placeHoldsOnPatronsMisc() {
		System.out.println();
		String itemDescription = this.input.askForString("Item Description: ");
		String location = this.input.askForString("Where was the item found: ");
		String patronID = this.input.askForString("Patron ID: ");
		this.manage.applyMiscHold(itemDescription, location, FakeDB.getPatron(patronID));
	}

	private void placeHoldsOnPatronsWhoDidNotShelveTheirBooks() {
		Patron offendingPatron = this.db.locatePatronInDB();
		Copy unshelvedCopy = this.db.locateCopyInDB();
		int fineAmount = this.input.askForInteger("Fine Amount: ");
		this.manage.applyUnshelvedHold(fineAmount, offendingPatron, unshelvedCopy);
	}

	private void placeHoldsOnPatronsWithOverdueBooks() {
		int fineAmount = this.input.askForInteger("Fine Amount: ");
		this.manage.applyOverdueHolds(fineAmount);
	}

	private void displayPatronsWithOverdueBooks() {
		System.out.println();
		this.manage.displayPatronsWithBooksUnreturned();
	}

	private void displayAllPatrons() {
		System.out.println();
		this.manage.displayAllPatrons();
	}
}
