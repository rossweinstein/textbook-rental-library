package textbookRentalLibrary;

import fakeDatabase.FakeDB;
import helpers.InputHelper;
import patron.Copy;
import patron.Patron;

public class CheckoutController {

	private InputHelper input;

	public CheckoutController() {
		this.input = new InputHelper();
	}

	private boolean validatePatronInfo(Patron patron) {
		System.out.println("\n" + patron.toString());

		if (!this.patronInfoVerified()) {

			if (this.input.askBinaryQuestion("\nIs this the wrong patron or do they have a mispelled name? (wrong/name)",
					"wrong", "name")) {
				return false;
			} else {
				this.updatePatronName(patron);
			}
		}

		return true;
	}

	private boolean updatePatronName(Patron patron) {
		String correctName = this.input.askForString("\nWhat is the correct spelling of your name?");
		patron.setName(correctName);
		
		System.out.println("\n" + patron.toString());
		
		return true;
	}

	private boolean patronInfoVerified() {
		return this.input.askBinaryQuestion("\nIs this information correct? (y/n)", "y", "n");
	}

	public boolean beginCheckoutSession() {
		
		System.out.println("----------BEGINNING CHECKOUT SESSION----------");

		Patron thePatron = this.locatePatronInDB();

		// if we cannot find the Patron, end session
		if (thePatron == null) {
			return false;
		}

		// if we are unable to validate Patron info, end session
		if (!this.validatePatronInfo(thePatron)) {
			return false;
		}

		// check holds on record
		if (thePatron.hasHoldsOnRecord()) {

			this.holdAlertMessage(thePatron);

			// must resolve holds to continue
			if (!this.isAbleResolveHolds()) {
				return false;
			}

		}

		// begin checking out copies if everything is validated
		boolean endSession = false;
		while (!endSession) {

			this.checkoutTextbookCopy(thePatron);
			endSession = !this.input.askBinaryQuestion("\nCheckout another book? (y/n)", "y", "n");
		}
		
		System.out.println("\n" + thePatron.toString());

		return true;
	}

	private void holdAlertMessage(Patron patron) {
		System.out.println("\nALERT: Patron has hold on record");
		System.out.println(patron.getName() + " has yet to return and pay the fee for the following titles: ");
		System.out.println(patron.getUnreturnedBooked());

	}

	private boolean isAbleResolveHolds() {
		return this.input.askBinaryQuestion("\nIs patron able to resolve holds? (y/n)", "y", "n");
	}

	private void checkoutTextbookCopy(Patron thePatron) {

		Copy theCopy = this.locateCopyInDB();

		if (theCopy != null) {
			thePatron.checkCopyOut(theCopy);
		}
	}

	private Patron locatePatronInDB() {

		Patron thePatron = null;

		boolean locatingPatron = false;
		while (!locatingPatron) {

			String patronID = this.input.askForString("\nEnter Patron ID number: ");
			Patron possiblePatron = FakeDB.getPatron(patronID);

			if (possiblePatron != null) {
				thePatron = possiblePatron;
				locatingPatron = true;
			} else {

				if (!this.enterAnotherIDNumber()) {
					return null;
				}
			}
		}
		return thePatron;
	}

	private Copy locateCopyInDB() {

		Copy theCopy = null;

		boolean locatingCopy = false;
		while (!locatingCopy) {

			String copyID = this.input.askForString("\nEnter Copy ID number: ");
			Copy possibleCopy = FakeDB.getCopy(copyID);

			if (possibleCopy != null) {
				theCopy = possibleCopy;
				locatingCopy = true;
			} else {

				if (!this.enterAnotherIDNumber()) {
					return null;
				}
			}
		}
		return theCopy;
	}

	private boolean enterAnotherIDNumber() {
		return this.input.askBinaryQuestion("\nID NOT FOUND: Would you like to enter a differnt ID number? (y/n)", "y", "n");
	}
	
	public static void main (String[] args) {
		CheckoutController cc = new CheckoutController();
		boolean successfulCheckout = cc.beginCheckoutSession();
		
		System.out.println(successfulCheckout);
	}

}
