package textbookRentalLibrary.controllers;


import java.util.List;
import java.util.stream.Collectors;

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

public class DatabaseController {

	private InputHelper input;

	public DatabaseController() {
		this.input = new InputHelper();
	}
	
	public List<Copy> getAllCopiesInTRL() {
		return FakeDB.getAllCopies();
	}
	
	public List<Patron> getAllPatronsInTRL() {
		return FakeDB.getAllPatrons();
	}
	
	public List<Patron> getAllPatronsWithUnreturnedTextBooks() {
		return this.getAllPatronsInTRL().stream().filter(patron -> patron.copiesCurrentlyCheckedOut() > 0)
				.collect(Collectors.toList());
	}

	public List<Patron> getAllPatronsWithHolds() {
		return this.getAllPatronsInTRL().stream().filter(patron -> !patron.hasNoHoldsOnRecord())
				.collect(Collectors.toList());
	}


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
