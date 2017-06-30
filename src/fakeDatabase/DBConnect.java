package fakeDatabase;

import java.util.List;

import helpers.InputHelper;
import patron.Copy;
import patron.Patron;

public class DBConnect {
	
	private InputHelper input;
	
	public DBConnect() {
		this.input = new InputHelper();
	}
	
	public List<Patron> getAllPatrons() {
		return FakeDB.getAllPatrons();
	}
	
	public List<Copy> getAllCopies() {
		return FakeDB.getAllCopies();
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
