package textbookRentalLibrary.controllers.checkInAndOutCopy;

import model.copy.Copy;
import model.patron.Patron;
import textbookRentalLibrary.controllers.DatabaseSearch;
import textbookRentalLibrary.controllers.hold.DamageHoldController;

/**
 * This class handles the Check In Sessions for the TRL application
 * 
 * @author Ross Weinstein
 *
 */
public class CheckInController extends SessionController implements TRLSession {

	private DatabaseSearch db;

	public CheckInController() {
		super();
		this.db = new DatabaseSearch();
	}

	@Override
	public boolean startSession() {
		System.out.println("------------------BEGINNING CHECK IN SESSION------------------");

		Patron thePatron = this.db.locatePatronInDB();

		if (!super.patronCanBeValidated(thePatron)) {
			return false;
		}

		if (thePatron.copiesCurrentlyCheckedOut() > 0) {
			this.checkInCopies(thePatron);
		} else {
			System.out.println("\n" + thePatron.getContactInfo().getFirstName() + " [ID:" + thePatron.getPatronID()
					+ "]  does not have any copies currently checked out");
		}
		return true;
	}

	private void checkInCopies(Patron thePatron) {

		boolean endSession = false;
		while (!endSession) {

			if (thePatron.getCopiesOut().size() == 0) {
				System.out.println("All copies checked back in");
				endSession = true;
				
			} else {

				super.showCopiesOutToPatron(thePatron);
				this.checkInTextbookCopy(thePatron);
				endSession = !super.input.askBinaryQuestion("\nCheck in another book? (y/n)", "y", "n");
			}
		}
		this.printExitSessionMessage(thePatron);
	}
	
	private void printExitSessionMessage(Patron thePatron) {
		
		if (thePatron.getCopiesOut().size() == 0) {
			System.out.println("All copies checked back in");
		} else {
			super.showCopiesOutToPatron(thePatron);
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
			} else {
				this.displayBookJustCheckedIn(theCopy);
				this.copyIsDamaged();
			}
		}
	}

	private void displayCopyIsNotCheckedOutToPatronAlert(Copy theCopy, Patron thePatron) {
		System.out.println("\nALERT:" + " Cannot check in " + theCopy.getTitle() + " [copyID:" + theCopy.getCopyID()
				+ "] because that copyID is not associated with Patron [ID:" + thePatron.getPatronID()
				+ "]. \nCheck the copyID number. If the copyID was entered correctly, contact a manager.");

	}

	private void displayBookJustCheckedIn(Copy theCopy) {
		System.out.println("\nCopy Just Checked In:\n" + theCopy.toString());
	}

	private void copyIsDamaged() {

		boolean isBookDamaged = super.input.askBinaryQuestion("Is Copy Damaged? (y/n)", "y", "n");

		if (isBookDamaged) {
			DamageHoldController damage = new DamageHoldController();
			damage.markHold();
		}
	}
}
