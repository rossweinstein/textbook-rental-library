package textbookRentalLibrary.controllers.hold;

import model.copy.Copy;
import model.patron.Patron;
import textbookRentalLibrary.controllers.DatabaseController;
import textbookRentalLibrary.userInput.InputHelper;

/**
 * The basic tools needed for all the individual hold controllers such as
 * querying the database, receiving input, and being able to validate that the
 * hold was processed.
 * 
 * @author Ross Weinstein
 *
 */

public abstract class HoldController {

	private InputHelper input;
	private DatabaseController db;

	public HoldController() {
		this.input = new InputHelper();
		this.db = new DatabaseController();
	}

	protected InputHelper getInput() {
		return input;
	}

	protected DatabaseController queryDB() {
		return this.db;
	}

	protected boolean holdsUpdatedCorrectly(int tally) {
		return this.db.getHoldTotal() == tally;
	}

	protected boolean unableToFindPatron(Patron thePatron) {
		return thePatron == null;
	}

	protected boolean unableToFindCopy(Copy theCopy) {
		return theCopy == null;
	}
}
