package textbookRentalLibrary.checkInAndOut;

import fakeDatabase.DBConnect;
import model.copy.Copy;
import model.patron.Patron;
import model.patron.hold.Hold;

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
		
		return !thePatron.hasNoHoldsOnRecord() ? true : this.handleHolds(thePatron);
	}
	
	private boolean handleHolds(Patron patron) {
		this.printHoldAlertMessage(patron);
		return this.dealWithEachHold(patron);
	}
	
	private boolean dealWithEachHold(Patron patron) {
		
		for (Hold eachHold : patron.getAllHolds()) {
			
			eachHold.getHoldMessage();
			
			if(this.isAbleToResolveHolds()) {
				patron.resolvedHold(eachHold);
			}	
		}
		return patron.getAllHolds().size() == 0;
		
	}

	private void printHoldAlertMessage(Patron patron) {
		
		System.out.println("---HOLD ALERT---");
		System.out.println("Hold Amount: " + patron.getAllHolds().size());
		
		for (Hold eachHold : patron.getAllHolds()) {
			System.out.println(eachHold.getHoldMessage());
		}

	}

	/**
	 * Prompts the rental worker to see if the Patron is able to resolve their
	 * holds
	 * 
	 * @return boolean true if Patron can resolve holds; false otherwise
	 */
	private boolean isAbleToResolveHolds() {
		return super.input.askBinaryQuestion("\nIs patron able to resolve this hold? (y/n)", "y", "n");
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
