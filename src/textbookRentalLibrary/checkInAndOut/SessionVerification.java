package textbookRentalLibrary.checkInAndOut;

import helpers.InputHelper;
import patron.Patron;

/**
 * This class does the basic Patron verification for TRL application. It checks
 * if the patron is in the system and that they information is correct before
 * they are allowed to check in and check out copies.
 * 
 * @author Ross Weinstein
 *
 */
public class SessionVerification {

	protected InputHelper input;

	public SessionVerification() {
		this.input = new InputHelper();
	}

	/**
	 * Main check if the Patron can be validated. This checks both the PatronID
	 * and if their information on file is correct
	 * 
	 * @param thePatron
	 *            Patron the Patron to be tested
	 * @return boolean true is Patron can be validated; false otherwise
	 */
	public boolean patronCanBeValidated(Patron thePatron) {
		return this.patronIDIsValid(thePatron) && this.patronInfoIsValid(thePatron);
	}

	/**
	 * Does the Patron exist and have a valid patronID.
	 * 
	 * @param thePatron
	 *            Patron
	 * @return boolean true if the Patron is found; false otherwise
	 */
	public boolean patronIDIsValid(Patron thePatron) {
		return thePatron != null ? true : false;
	}

	/**
	 * Validates the patron information
	 * 
	 * @param thePatron
	 *            Patron the Patron to be validated
	 * @return boolean true if all information is correct; false otherwise
	 */
	public boolean patronInfoIsValid(Patron thePatron) {
		return this.validatePatronInfo(thePatron) ? true : false;
	}

	/**
	 * Where the questions are asked to see if the Patron has the correct
	 * information
	 * 
	 * @param patron
	 *            Patron the Patron whose information we need to validate
	 * @return boolean true if information is correct; false otherwise
	 */
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

	/**
	 * Allows a Patron to update their information
	 * 
	 * @param patron
	 *            Patron the Patron whose name needs to be updated
	 */
	private void updatePatronName(Patron patron) {
		String correctName = this.input.askForString("\nWhat is the correct spelling of your name?");
		patron.setName(correctName);
	}

	/**
	 * Prompts to see if Patron information is correct
	 * 
	 * @param patron
	 *            Patron the Patron to check their information
	 * @return boolean true if information is correct; false otherwise
	 */
	private boolean patronInfoVerified(Patron patron) {
		System.out.println("\nName: " + patron.getName());
		return this.input.askBinaryQuestion("\nIs this information correct? (y/n)", "y", "n");
	}
}
