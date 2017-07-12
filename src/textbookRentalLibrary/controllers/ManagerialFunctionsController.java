package textbookRentalLibrary.controllers;

import java.util.List;

import model.Manager;
import model.patron.Patron;
import textbookRentalLibrary.controllers.hold.*;
/**
 * This class controls all the functions that a manager can do: See all records,
 * see all records with holds, mark holds, generate overdue notices
 * 
 * @author Ross Weinstein
 *
 */
public class ManagerialFunctionsController {

	private Manager manage;

	public ManagerialFunctionsController() {
		this.manage = new Manager();
	}

	/********** DISPLAY FUNCTIONS **************************************/

	public void displayAllPatrons() {
		
		List<Patron> allPatrons = this.manage.getAllPatronsInTRL();
		String message = "ALL PATRONS";
		
		if (allPatrons.size() == 0) {
			System.out.println("There are currently no patrons in the database");
		} else {
			this.displaySelectedPatrons(allPatrons, message);
		}
	}

	public void displayAllPatronsWithHolds() {
		
		List<Patron> patronsWithHolds = this.manage.getAllPatronsWithHolds();
		String message = "PATRONS WITH HOLDS";
		
		if (patronsWithHolds.size() == 0) {
			System.out.println("There are currently no patrons with holds");
		} else {
			this.displaySelectedPatrons(patronsWithHolds, message);
		}
	}
	
	public void displayPatronsWithUnreturnedTextbooks() {
		
		List<Patron> patronsWithUnreturnedTextbooks = this.manage.getAllPatronsWithUnreturnedTextBooks();
		String message = "PATRONS WITH UNRETURNED BOOKS";
		
		if (patronsWithUnreturnedTextbooks.size() == 0) {
			System.out.println("There are currently no patrons with unreturned textbooks");
		} else {
			this.displaySelectedPatrons(patronsWithUnreturnedTextbooks, message);
		}
	}
	
	private void displaySelectedPatrons(List<Patron> selectedPatrons, String title) {
		
		System.out.println("--DISPLAY: " + title + "--");
		System.out.println("..." + selectedPatrons.size() + " Patrons found...\n");
		
		for (int i = 0; i < selectedPatrons.size(); i++) {
			System.out.println((i + 1) + ": " + selectedPatrons.get(i).showPatronIDAndName());
		}
	}
	
	/********** GENERATE HOLD NOTICES ********************************/
	
	public void generateHoldNotices() {
		if (this.manage.canGenerateHoldNotices()) {
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
		this.displayHolds(new OverdueHoldController());
	}

	public boolean markOverdueHolds() {
		return this.markHolds(new OverdueHoldController());
	}

	/********** UNSHELVED HOLDS **************************************/

	public void displayPatronsWithUnshelvedHolds() {
		this.displayHolds(new UnshelvedHoldController());
	}

	public boolean markUnshelvedHold() {
		return this.markHolds(new UnshelvedHoldController());
	}

	/********** DAMAGED HOLDS **************************************/

	public void displayPatronsWithDamageHolds() {
		this.displayHolds(new DamageHoldController());
	}

	public boolean markDamageHold() {
		return this.markHolds(new DamageHoldController());
	}

	/********** LOST HOLDS **************************************/

	public void displayPatronsWithLostHolds() {
		this.displayHolds(new LostHoldController());
	}

	public boolean markLostHold() {
		return this.markHolds(new LostHoldController());
	}

	/********** MISC HOLDS **************************************/

	public void displayPatronsWithMiscHolds() {
		this.displayHolds(new MiscHoldController());
	}

	public boolean markMiscHold() {
		return this.markHolds(new MiscHoldController());
	}

	/********** HELPER METHODS **************************************/
	
	private void displayHolds(PlaceHoldController theHolds) {
		theHolds.displayHolds();
	}
	
	private boolean markHolds(PlaceHoldController theHold) {
		return theHold.markHold();
	}
}
