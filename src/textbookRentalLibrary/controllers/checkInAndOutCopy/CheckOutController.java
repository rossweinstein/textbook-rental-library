package textbookRentalLibrary.controllers.checkInAndOutCopy;

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

	@Override
	public boolean startSession() {

		System.out.println("------------------BEGINNING CHECKOUT SESSION------------------");

		Patron thePatron = super.queryDB().locatePatronInDB();

		if (super.patronCannotBeValidated(thePatron)) {
			return false;
		}

		if (patronCannotCheckOutCopiesDueToHolds(thePatron)) {
			this.printHoldAlertMessage(thePatron);
			return false;
		}

		this.checkOutCopies(thePatron);
		return true;
	}

	private boolean patronCannotCheckOutCopiesDueToHolds(Patron thePatron) {
		return !thePatron.hasNoHoldsOnRecord();
	}

	/******************* HOLD METHODS ********************************/

	private void printHoldAlertMessage(Patron patron) {

		System.out.println("\n---HOLD ALERT---");
		System.out.println("Hold Amount: " + patron.getAllHolds().size());

		for (Hold eachHold : patron.getAllHolds()) {
			System.out.println(eachHold.getHoldMessage());
		}
		
		System.out.println("\nHolds must be resolved at Manager Station before any textbooks may be checked out");

	}

	/******************* CHECKOUT METHODS ********************************/

	private void checkOutCopies(Patron thePatron) {

		boolean endSession = false;
		while (!endSession) {

			this.checkoutTextbookCopy(thePatron);
			endSession = !super.userInput().askBinaryQuestion("\nCheckout another book? (y/n)", "y", "n");
		}
		
		super.showCopiesOutToPatron(thePatron);
	}

	private void checkoutTextbookCopy(Patron thePatron) {

		Copy theCopy = super.queryDB().locateCopyInDB();

		if (theCopy != null) {

			boolean bookIsAvailable = thePatron.checkCopyOut(theCopy);

			if (!bookIsAvailable) {
				this.displayBookAlreadyCheckedOutMessage(theCopy);
			} else {
				this.displayBookJustCheckedOut(theCopy);
			}
		}
	}

	private void displayBookAlreadyCheckedOutMessage(Copy theCopy) {
		System.out.println("\nALERT:" + " Cannot checkout " + theCopy.getTitle() + " [copyID:" + theCopy.getCopyID()
				+ "] because that copyID is already associated with Patron " + theCopy.getOutTo().getPatronID()
				+ ". \nCheck the copyID number. If the copyID was entered correctly, contact a manager.");
	}
	
	private void displayBookJustCheckedOut(Copy theCopy) {
		System.out.println("\nCopy Just Checked Out:\n" + theCopy.toString());
	}
}
