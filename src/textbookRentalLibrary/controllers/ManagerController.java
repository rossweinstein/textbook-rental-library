package textbookRentalLibrary.controllers;

import java.util.List;

import model.copy.Copy;
import model.patron.Patron;
import textbookRentalLibrary.controllers.hold.*;

/**
 * This class controls all the functions that a manager can do: See all records,
 * see all records with holds, mark holds, generate overdue notices
 * 
 * @author Ross Weinstein
 *
 */
public class ManagerController {

	private DatabaseController db;

	public ManagerController() {
		this.db = new DatabaseController();
	}

	/********** DISPLAY FUNCTIONS **************************************/

	public void displayAllPatrons() {

		List<Patron> allPatrons = this.db.getAllPatronsInTRL();
		String message = "ALL PATRONS";

		this.displayResults(allPatrons, message, "in the database");
	}

	public void displayAllCopies() {

		List<Copy> allCopies = this.db.getAllCopiesInTRL();

		if (allCopies.size() == 0) {
			System.out.println("There are currently no copies in the database");
		} else {

			System.out.println("--DISPLAY: ALL COPIES--");
			System.out.println("..." + allCopies.size() + " Copies found...\n");
			printAllCopies(allCopies);
		}
	}

	private void printAllCopies(List<Copy> allCopies) {
		for (int i = 0; i < allCopies.size(); i++) {
			System.out.println((i + 1) + ": " + allCopies.get(i).getCopyID() + " | " + allCopies.get(i).getTitle());
		}
	}

	public void displayAllPatronsWithHolds() {

		List<Patron> patronsWithHolds = this.db.getAllPatronsWithHolds();
		String message = "PATRONS WITH HOLDS";

		this.displayResults(patronsWithHolds, message, "with holds");
	}

	public void displayPatronsWithUnreturnedTextbooks() {

		List<Patron> patronsWithUnreturnedTextbooks = this.db.getAllPatronsWithUnreturnedTextBooks();
		String message = "PATRONS WITH UNRETURNED BOOKS";
		
		this.displayResults(patronsWithUnreturnedTextbooks, message, "with unreturned textbooks");
	}

	public void displayResults(List<Patron> results, String message, String holdType) {

		if (results.size() == 0) {
			this.displayTitle(message);
			System.out.println("There are currently no patrons " + holdType);
		} else {
			this.displaySelectedPatrons(results, message);
		}
	}

	private void displaySelectedPatrons(List<Patron> selectedPatrons, String title) {

		this.displayTitle(title);
		System.out.println("..." + selectedPatrons.size() + " Patrons found...\n");

		for (int i = 0; i < selectedPatrons.size(); i++) {
			System.out.println((i + 1) + ": " + selectedPatrons.get(i).showPatronIDAndName());
		}
	}
	
	private void displayTitle(String title) {
		System.out.println("--DISPLAY: " + title + "--");
	}

	/********** GENERATE HOLD NOTICES ********************************/

	public boolean canGenerateHoldNotices() {
		return this.db.getAllPatronsWithHolds().size() > 0;
	}

	public void generateHoldNotices() {
		if (this.canGenerateHoldNotices()) {
			System.out.println("Overdue notices generated...\n");
		} else {
			System.out.println("There are no holds in the system.");
		}
	}

	/********** RESOLVE HOLD ***************************************/

	public void resolvePatronHold() {

		ResolveHoldController resolve = new ResolveHoldController();
		resolve.resolvePatronHold();
	}

	/********** OVERDUE HOLDS **************************************/

	public void displayPatronsWithOverdueHolds() {
		this.displayHolds(new OverdueHoldController(), "OVERDUE HOLDS");
	}

	public void markOverdueHolds() {
		if (this.markHolds(new OverdueHoldController())) {
			System.out.println("Overdue holds successful marked");
		} else {
			System.out.println("ERROR: Overdue holds not marked");
		}
	}

	/********** UNSHELVED HOLDS **************************************/

	public void displayPatronsWithUnshelvedHolds() {
		this.displayHolds(new UnshelvedHoldController(), "UNSHELVED HOLDS");
	}

	public void markUnshelvedHold() {
		if (this.markHolds(new UnshelvedHoldController())) {
			System.out.println("Unshelved hold successful marked");
		} else {
			System.out.println(this.holdNotMarkedErrorMessage());
		}
	}

	/********** DAMAGED HOLDS **************************************/

	public void displayPatronsWithDamageHolds() {
		this.displayHolds(new DamageHoldController(), "DAMAGE HOLDS");
	}

	public void markDamageHold() {
		if (this.markHolds(new DamageHoldController())) {
			System.out.println("Damage hold successful marked");
		} else {
			System.out.println(this.holdNotMarkedErrorMessage());
		}
	}

	/********** LOST HOLDS **************************************/

	public void displayPatronsWithLostHolds() {
		this.displayHolds(new LostHoldController(), "LOST HOLDS");
	}

	public void markLostHold() {
		if (this.markHolds(new LostHoldController())) {
			System.out.println("Lost hold successful marked");
		} else {
			System.out.println(this.holdNotMarkedErrorMessage());
		}
	}

	/********** MISC HOLDS **************************************/

	public void displayPatronsWithMiscHolds() {
		this.displayHolds(new MiscHoldController(), "MISC HOLDS");
	}

	public boolean markMiscHold() {
		return this.markHolds(new MiscHoldController());
	}

	/********** HELPER METHODS **************************************/

	private void displayHolds(PlaceHoldController theHolds, String title) {
		this.displayTitle(title);
		theHolds.displayHolds();
	}

	private boolean markHolds(PlaceHoldController theHold) {
		return theHold.markHold();
	}

	private String holdNotMarkedErrorMessage() {
		return "\n-----ERROR: Hold unable to be marked-----\n\nThis could be due to several reasons:"
				+ "\n1) Patron cannot be found in datbase\n2) Copy cannot be found in database"
				+ "\n3) Patron was not the last Patron to check out copy"
				+ "\n\nDouble check PatronID, CopyID, and that Copy is associated with Patron before trying again";
	}
}
