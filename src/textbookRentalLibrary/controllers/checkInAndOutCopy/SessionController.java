package textbookRentalLibrary.controllers.checkInAndOutCopy;

import model.patron.Patron;
import textbookRentalLibrary.controllers.DatabaseController;
import textbookRentalLibrary.controllers.TRLController;
import textbookRentalLibrary.menus.CommandLineMenu;
import textbookRentalLibrary.menus.PatronInfoUpdateMenu;

/**
 * This class does the basic Patron verification for TRL application. It checks
 * if the patron is in the system and that they information is correct before
 * they are allowed to check in and check out copies.
 * 
 * @author Ross Weinstein
 *
 */
public class SessionController extends TRLController {
	
	private DatabaseController db;

	public SessionController() {
		super();
		this.db = new DatabaseController();
	}
	
	protected DatabaseController queryDB() {
		return this.db;
	}

	public boolean patronCannotBeValidated(Patron thePatron) {
		return !this.patronFoundInDB(thePatron) || !this.patronInfoIsValid(thePatron);
	}

	public boolean patronFoundInDB(Patron thePatron) {
		return thePatron != null ? true : false;
	}

	public boolean patronInfoIsValid(Patron thePatron) {
		return this.validatePatronInfo(thePatron) ? true : false;
	}

	private boolean validatePatronInfo(Patron patron) {

		if (!this.patronInfoVerified(patron)) {

			if (super.userInput().askBinaryQuestion(
					"\nIs this the wrong patron or do they have incorrect information? (patron/info)", "patron",
					"info")) {
				System.out.println("\n--ALERT--\nInform the patron to see a manager to resolve any issues with their Patron ID");
				return false;
			} else {
				CommandLineMenu patronInfoUpdate = new PatronInfoUpdateMenu(patron);
				patronInfoUpdate.displayMenu();
			}
		}
		return true;
	}

	private boolean patronInfoVerified(Patron patron) {
		System.out.println("\n----------VERIFY CONTACT INFORMATION----------\n" + patron.toString());
		return super.userInput().askBinaryQuestion("\nIs this information correct? (y/n)", "y", "n");
	}

	protected void showCopiesOutToPatron(Patron thePatron) {
		System.out.println("\nCopies Currently Out to " + thePatron.getContactInfo().getFirstName() + " " + thePatron.getContactInfo().getLastName() + ":");
		thePatron.getCopiesOut().stream().forEach(eachCopy -> System.out.println(eachCopy.toString()));
	}
}
