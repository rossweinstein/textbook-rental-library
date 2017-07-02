package textbookRentalLibrary.checkInAndOut;

import helpers.InputHelper;
import model.patron.Patron;

/**
 * This class does the basic Patron verification for TRL application. It checks
 * if the patron is in the system and that they information is correct before
 * they are allowed to check in and check out copies.
 * 
 * @author Ross Weinstein
 *
 */
public class SessionController {

	protected InputHelper input;

	public SessionController() {
		this.input = new InputHelper();
	}

	public boolean patronCanBeValidated(Patron thePatron) {
		return this.patronFoundInDB(thePatron) && this.patronInfoIsValid(thePatron);
	}

	public boolean patronFoundInDB(Patron thePatron) {
		return thePatron != null ? true : false;
	}

	public boolean patronInfoIsValid(Patron thePatron) {
		return this.validatePatronInfo(thePatron) ? true : false;
	}

	private boolean validatePatronInfo(Patron patron) {

		if (!this.patronInfoVerified(patron)) {

			if (this.input.askBinaryQuestion(
					"\nIs this the wrong patron or do they have a mispelled name? (wrong/name)", "wrong", "name")) {
				return false;
			} else {
				this.updatePatronName(patron);
			}
		}
		return true;
	}


	private void updatePatronName(Patron patron) {
		String correctName = this.input.askForString("\nWhat is the correct spelling of your name?");
		patron.setName(correctName);
	}

	private boolean patronInfoVerified(Patron patron) {
		System.out.println("\nName: " + patron.getName());
		return this.input.askBinaryQuestion("\nIs this information correct? (y/n)", "y", "n");
	}
}
