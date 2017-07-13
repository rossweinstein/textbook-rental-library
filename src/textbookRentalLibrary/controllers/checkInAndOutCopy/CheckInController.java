package textbookRentalLibrary.controllers.checkInAndOutCopy;

import model.copy.Copy;
import model.patron.Patron;
import textbookRentalLibrary.controllers.hold.DamageHoldController;

/**
 * This class handles the Check In Sessions for the TRL application
 * 
 * @author Ross Weinstein
 *
 */
public class CheckInController extends SessionController implements TRLSession {

	/********** CHECK IN SESSION **************************************/

	@Override
	public boolean startSession() {
		System.out.println("------------------BEGINNING CHECK IN SESSION------------------");

		Patron thePatron = super.queryDB().locatePatronInDB();

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
				endSession = true;

			} else {

				super.showCopiesOutToPatron(thePatron);
				this.checkInTextbookCopy(thePatron);
				endSession = !super.userInput().askBinaryQuestion("\nCheck in another book? (y/n)", "y", "n");
			}
		}
		this.printExitSessionMessage(thePatron);
	}

	private void checkInTextbookCopy(Patron thePatron) {
		Copy theCopy = super.queryDB().locateCopyInDB();

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
		this.copyIsDamaged(theCopy);
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
			System.out.println("Patron currently has no copies checked out");
		} else {
			super.showCopiesOutToPatron(thePatron);
		}
	}

	/********** COPY IS DAMAGED HELPER **************************************/

	private void copyIsDamaged(Copy theCopy) {

		boolean isBookDamaged = super.userInput().askBinaryQuestion("Is Copy Damaged? (y/n)", "y", "n");

		if (isBookDamaged) {
			
			int fineAmount = super.userInput().askForInteger("Fine Amount: ");
			
			DamageHoldController damage = new DamageHoldController();
			damage.markingHold(theCopy.getLastPersonToCheckOut(), theCopy, fineAmount);
		}
	}
}
