package textbookRentalLibrary.controllers;


import database.FakeDB;
import model.copy.Copy;
import model.patron.Patron;
import textbookRentalLibrary.userInput.InputHelper;

/**
 * 
 * This class just makes dealing with the FakeDB a little easier. It has the
 * methods to deal with when someone searches for a patron or copy that does not
 * exist and is able to return all the patrons of copies as a List.
 * 
 * @author Ross Weinstein
 *
 */

public class DatabaseSearch {

	private InputHelper input;

	public DatabaseSearch() {
		this.input = new InputHelper();
	}

	/**
	 * Prompts the user to enter a patronID and searches the FakeDB for that
	 * number. If the patronID is not found, the user will be informed and then
	 * asked if they want to enter a new patronID or quit.
	 * 
	 * @return Patron the Patron who matches the patronID or null if the patron
	 *         cannot be found
	 */
	public Patron locatePatronInDB() {

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
	
	/**
	 * Prompts the user to enter a copyID and searches the FakeDB for that
	 * number. If the copyID is not found, the user will be informed and then
	 * asked if they want to enter a new copyID or quit.
	 * 
	 * @return Copy the Copy who matches the copyID or null if the patron
	 *         cannot be found
	 */
	public Copy locateCopyInDB() {

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
		return this.input.askBinaryQuestion("\nID NOT FOUND: Would you like to enter a differnt ID number? (y/n)", "y",
				"n");
	}

}
