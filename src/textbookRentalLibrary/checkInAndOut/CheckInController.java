package textbookRentalLibrary.checkInAndOut;

import fakeDatabase.DBConnect;
import patron.Copy;
import patron.Patron;

public class CheckInController extends SessionVerification implements TRLSession {

	private DBConnect db;

	public CheckInController() {
		super();
		this.db = new DBConnect();
	}

	public boolean startSession() {
		System.out.println("------------------BEGINNING CHECK IN SESSION------------------");

		Patron thePatron = this.db.locatePatronInDB();

		if (!super.patronCanBeValidated(thePatron)) {
			return false;
		}

		if(this.patronHasCopiesOut(thePatron)) {
		this.checkInCopies(thePatron);
		} else {
			System.out.println("\n" + thePatron.getName() + " [ID:" + thePatron.getPatronID() +"]  does not have any copies currently checked out");
		}
		return true;
	}

	private void checkInCopies(Patron thePatron) {

		boolean endSession = false;
		while (!endSession) {

			this.checkInTextbookCopy(thePatron);
			endSession = !super.input.askBinaryQuestion("\nCheck in another book? (y/n)", "y", "n");
		}

	}

	private void checkInTextbookCopy(Patron thePatron) {
		Copy theCopy = this.db.locateCopyInDB();

		if (theCopy != null) {

			boolean patronCanReturnCopy = thePatron.checkCopyIn(theCopy);

			if (!patronCanReturnCopy) {
				this.displayCopyIsNotCheckedOutToPatronAlert(theCopy, thePatron);
			}
		}

	}
	
	private boolean patronHasCopiesOut(Patron thePatron) {
		return thePatron.copiesCurrentlyCheckedOut() > 0;
	}

	private void displayCopyIsNotCheckedOutToPatronAlert(Copy theCopy, Patron thePatron) {
		System.out.println("\nALERT:" + " Cannot check in " + theCopy.getTitle() + " [copyID:" + theCopy.getCopyID()
				+ "] because that copyID is not associated with Patron [ID:" + thePatron.getPatronID()
				+ "]. \nCheck the copyID number. If the copyID was entered correctly, contact a manager.");

	}
}
