package textbookRentalLibrary.checkInAndOut;

import fakeDatabase.DBConnect;
import patron.Copy;
import patron.Patron;

/**
 * This class handles the Check In Sessions for the TRL application
 * 
 * @author Ross Weinstein
 *
 */
public class CheckInController extends SessionVerification implements TRLSession {

	private DBConnect db;

	public CheckInController() {
		super();
		this.db = new DBConnect();
	}

	/**
	 * From TRLSession interface, starts the Check In Session
	 */
	public boolean startSession() {
		System.out.println("------------------BEGINNING CHECK IN SESSION------------------");

		Patron thePatron = this.db.locatePatronInDB();

		if (!super.patronCanBeValidated(thePatron)) {
			return false;
		}

		if (thePatron.copiesCurrentlyCheckedOut() > 0) {
			this.checkInCopies(thePatron);
		} else {
			System.out.println("\n" + thePatron.getName() + " [ID:" + thePatron.getPatronID()
					+ "]  does not have any copies currently checked out");
		}
		return true;
	}

	/**
	 * Allows a Patron to check in as may books as they have checked out
	 * 
	 * @param thePatron
	 *            Patron the Patron who wants to check in copies
	 */
	private void checkInCopies(Patron thePatron) {

		boolean endSession = false;
		while (!endSession) {

			this.checkInTextbookCopy(thePatron);
			endSession = !super.input.askBinaryQuestion("\nCheck in another book? (y/n)", "y", "n");
		}

	}

	/**
	 * Where the copy is actually returned. It searches the database for the
	 * copy, see if it exists, checks if the copy is currently checked out by
	 * the Patron, and then returns it
	 * 
	 * @param thePatron
	 *            Patron the Patron who wants to check in copies
	 */
	private void checkInTextbookCopy(Patron thePatron) {
		Copy theCopy = this.db.locateCopyInDB();

		if (theCopy != null) {

			boolean patronCanReturnCopy = thePatron.checkCopyIn(theCopy);

			if (!patronCanReturnCopy) {
				this.displayCopyIsNotCheckedOutToPatronAlert(theCopy, thePatron);
			}
		}

	}

	/**
	 * Alert message that states Patron is trying to check in a book that is not
	 * currently checked out to them.
	 * 
	 * @param theCopy
	 *            Copy the Copy the Patron wants to check in
	 * @param thePatron
	 *            Patron the Patron who wants to check the Copy in
	 */
	private void displayCopyIsNotCheckedOutToPatronAlert(Copy theCopy, Patron thePatron) {
		System.out.println("\nALERT:" + " Cannot check in " + theCopy.getTitle() + " [copyID:" + theCopy.getCopyID()
				+ "] because that copyID is not associated with Patron [ID:" + thePatron.getPatronID()
				+ "]. \nCheck the copyID number. If the copyID was entered correctly, contact a manager.");

	}
}
