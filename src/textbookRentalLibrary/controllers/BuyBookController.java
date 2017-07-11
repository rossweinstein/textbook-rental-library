package textbookRentalLibrary.controllers;

import database.FakeDB;
import model.copy.Copy;
import textbookRentalLibrary.userInput.InputHelper;

public class BuyBookController {

	private InputHelper input;
	private DatabaseSearch db;

	public BuyBookController() {
		this.input = new InputHelper();
		this.db = new DatabaseSearch();
	}

	public void startProcess() {

		Copy theCopy = this.db.locateCopyInDB();

		if (theCopy != null) {

			boolean wantsToBuy = this.input.askBinaryQuestion("Patron wishes to buy book? (y/n)", "y", "n");

			if (wantsToBuy) {

				this.removeBookFromDB(theCopy);
				System.out.println(
						theCopy.getTitle() + " [ID: " + theCopy.getCopyID() + "] has been removed from database");

			}
		}
	}
	
	public void removeBookFromDB(Copy theCopy) {
		FakeDB.getCopiesDB().remove(theCopy.getCopyID());
	}
}
