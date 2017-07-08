package textbookRentalLibrary.controllers;

import model.Manager;
import model.patron.Patron;
import textbookRentalLibrary.controllers.hold.DamageHoldController;
import textbookRentalLibrary.controllers.hold.LostHoldController;
import textbookRentalLibrary.controllers.hold.MiscHoldController;
import textbookRentalLibrary.controllers.hold.OverdueHoldController;
import textbookRentalLibrary.controllers.hold.PlaceHoldController;
import textbookRentalLibrary.controllers.hold.ResolveHoldController;
import textbookRentalLibrary.controllers.hold.UnshelvedHoldController;

/**
 * This class controls all the functions that a manager can do: See all records,
 * see all records with holds, mark holds, generate overdue notices
 * 
 * @author Ross Weinstein
 *
 */
public class ManagerialFunctionsController {

	private Manager manage;
	private DatabaseSearch db;

	public ManagerialFunctionsController() {
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
		System.out.println("All Patrons In Database:");
		this.manage.getAllPatronsInTRL();
	}

	public void displayAllPatronsWithHolds() {
		System.out.println("All Patrons With Holds:");
		this.manage.getAllPatronsWithHolds();
	}
	
	public void displayPatronsWithUnreturnedTextbooks() {
		System.out.println("Patrons Textbooks Currently Checked Out"); 
				this.manage.getAllPatronsWithUnreturnedTextBooks();
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
