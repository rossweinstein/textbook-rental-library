package textbookRentalLibrary.checkInAndOut;

import fakeDatabase.DBConnect;
import patron.Copy;
import patron.Patron;

/**
 * This class handles the Check Out Sessions for the TRL application
 * 
 * @author Ross Weinstein
 *
 */
public class CheckOutController extends SessionController implements TRLSession {

	private DBConnect db;

	public CheckOutController() {
		super();
		this.db = new DBConnect();
	}

	@Override
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

			this.printHoldAlertMessage(thePatron);

			// must resolve holds to continue
			if (!this.isAbleToResolveHolds()) {
				return false;
			} else {
				thePatron.resolvedHolds();
			}
		}
		return true;
	}

	private void printHoldAlertMessage(Patron patron) {
		System.out.println("\nALERT: Patron has hold on record");
		System.out.println(patron.getName() + " has yet to return and pay the fee for the following titles: ");
		System.out.println(patron.getCopiesOut());

	}

	/**
	 * Prompts the rental worker to see if the Patron is able to resolve their
	 * holds
	 * 
	 * @return boolean true if Patron can resolve holds; false otherwise
	 */
	private boolean isAbleToResolveHolds() {
		return super.input.askBinaryQuestion("\nIs patron able to resolve holds? (y/n)", "y", "n");
	}

	/******************* CHECKOUT METHODS ********************************/

	private void checkOutCopies(Patron thePatron) {

		boolean endSession = false;
		while (!endSession) {

			this.checkoutTextbookCopy(thePatron);
			endSession = !super.input.askBinaryQuestion("\nCheckout another book? (y/n)", "y", "n");
		}
	}

	/**
	 * Where the copy is actually checked out. It searches the database for the
	 * copy, see if it exists, checks if the copy is not currently checked out
	 * by another Patron, and then checks it out to the Patron
	 * 
	 * @param thePatron
	 *            Patron the Patron who wants to check out copies
	 */
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
