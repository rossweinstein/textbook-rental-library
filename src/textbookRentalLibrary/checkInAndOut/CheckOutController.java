package textbookRentalLibrary.checkInAndOut;

import fakeDatabase.DBConnect;
import patron.Copy;
import patron.Patron;

public class CheckOutController extends SessionVerification implements TRLSession {

	private DBConnect db;

	public CheckOutController() {
		super();
		this.db = new DBConnect();
	}

	public boolean startSession() {

		System.out.println("------------------BEGINNING CHECKOUT SESSION------------------");

		Patron thePatron = this.db.locatePatronInDB();

		if (!super.patronCanBeValidated(thePatron)) {
			return false;
		}

		if (!this.patronHasNoHoldsOnRecord(thePatron)) {
			return false;
		}

		this.checkOutCopies(thePatron);
		return true;
	}
	
	/******************* HOLD METHODS ********************************/

	private boolean patronHasNoHoldsOnRecord(Patron thePatron) {

		// check holds on record
		if (thePatron.hasHoldsOnRecord()) {

			this.holdAlertMessage(thePatron);

			// must resolve holds to continue
			if (!this.isAbleToResolveHolds()) {
				return false;
			} else {
				thePatron.resolvedHolds();
			}
		}
		return true;
	}
	
	private void holdAlertMessage(Patron patron) {
		System.out.println("\nALERT: Patron has hold on record");
		System.out.println(patron.getName() + " has yet to return and pay the fee for the following titles: ");
		System.out.println(patron.getCopiesOut());

	}

	private boolean isAbleToResolveHolds() {
		return this.input.askBinaryQuestion("\nIs patron able to resolve holds? (y/n)", "y", "n");
	}
	
	/******************* CHECKOUT METHODS ********************************/

	private void checkOutCopies(Patron thePatron) {

		boolean endSession = false;
		while (!endSession) {

			this.checkoutTextbookCopy(thePatron);
			endSession = !super.input.askBinaryQuestion("\nCheckout another book? (y/n)", "y", "n");
		}
	}

	private void checkoutTextbookCopy(Patron thePatron) {

		Copy theCopy = this.db.locateCopyInDB();

		if (theCopy != null) {

			boolean bookIsAvailable = thePatron.checkCopyOut(theCopy);

			if (!bookIsAvailable) {
				this.displayBookAlreadyCheckedOutMessage(theCopy);
			}
		}
	}

	private void displayBookAlreadyCheckedOutMessage(Copy theCopy) {
		System.out.println("\nALERT:" + " Cannot checkout " + theCopy.getTitle() + " [copyID:" + theCopy.getCopyID()
				+ "] because that copyID is already associated with Patron " + theCopy.getOutTo().getPatronID()
				+ ". \nCheck the copyID number. If the copyID was entered correctly, contact a manager.");
	}
}
