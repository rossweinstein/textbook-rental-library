package textbookRentalLibrary.controllers.checkInAndOutCopy;

import model.copy.Copy;
import model.patron.Patron;
import textbookRentalLibrary.controllers.DatabaseController;
import textbookRentalLibrary.controllers.hold.DamageHoldController;

/**
 * This class handles the Check In Sessions for the TRL application
 * 
 * @author Ross Weinstein
 *
 */
public class CheckInController extends SessionController implements TRLSession {

	private DatabaseController db;

	public CheckInController() {
		super();
		this.db = new DatabaseController();
	}

	/********** CHECK IN SESSION **************************************/

	@Override
	public boolean startSession() {
		System.out.println("------------------BEGINNING CHECK IN SESSION------------------");

		Patron thePatron = this.db.locatePatronInDB();

		if (super.patronCannotBeValidated(thePatron)) {
			return false;
		}

		if (patronHasCopiesCheckedOut(thePatron)) {
			this.checkInCopies(thePatron);
		} else {
			printPatronHasNoCopiesOutAlert(thePatron);
		}
		return true;
	}

	/********** SESSION HELPER METHODS **************************************/

	private void checkInCopies(Patron thePatron) {

		boolean endSession = false;
		while (!endSession) {

			if (patronHasNoMoreCopiesOut(thePatron)) {
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

	private void checkInTextbookCopy(Patron thePatron) {
		Copy theCopy = this.db.locateCopyInDB();

		if (copyIsLocated(theCopy)) {

			boolean patronCanReturnCopy = thePatron.checkCopyIn(theCopy);

			if (patronCanReturnCopy) {
				this.handleReturn(theCopy);

			} else {
				this.displayCopyIsNotCheckedOutToPatronAlert(theCopy, thePatron);
			}
		}
	}

	private void handleReturn(Copy theCopy) {
		this.displayBookJustCheckedIn(theCopy);
		this.copyIsDamaged();
	}

	/********** ENCAPSULATED CONDITIONALS *************/

	private boolean patronHasCopiesCheckedOut(Patron thePatron) {
		return thePatron.copiesCurrentlyCheckedOut() > 0;
	}

	private boolean copyIsLocated(Copy theCopy) {
		return theCopy != null;
	}

	private boolean patronHasNoMoreCopiesOut(Patron thePatron) {
		return thePatron.getCopiesOut().size() == 0;
	}

	/********** ALERT PRINT MESSAGES **************************************/

	private void printPatronHasNoCopiesOutAlert(Patron thePatron) {
		System.out.println("\n" + thePatron.getContactInfo().getFirstName() + " [ID:" + thePatron.getPatronID()
				+ "]  does not have any copies currently checked out");
	}

	private void displayCopyIsNotCheckedOutToPatronAlert(Copy theCopy, Patron thePatron) {
		System.out.println("\nALERT:" + " Cannot check in " + theCopy.getTitle() + " [copyID:" + theCopy.getCopyID()
				+ "] because that copyID is not associated with Patron [ID:" + thePatron.getPatronID()
				+ "]. \nCheck the copyID number. If the copyID was entered correctly, contact a manager.");

	}

	private void displayBookJustCheckedIn(Copy theCopy) {
		System.out.println("\nCopy Just Checked In:\n" + theCopy.toString());
	}

	private void printExitSessionMessage(Patron thePatron) {

		if (patronHasNoMoreCopiesOut(thePatron)) {
			System.out.println("All copies checked back in");
		} else {
			super.showCopiesOutToPatron(thePatron);
		}
	}

	/********** COPY IS DAMAGED HELPER **************************************/

	private void copyIsDamaged() {

		boolean isBookDamaged = super.input.askBinaryQuestion("Is Copy Damaged? (y/n)", "y", "n");

		if (isBookDamaged) {
			DamageHoldController damage = new DamageHoldController();
			damage.markHold();
		}
	}
}
