package textbookRentalLibrary.controllers;

import model.copy.Copy;

public class BuyBookController extends TRLController{

	private DatabaseController db;

	public BuyBookController() {
		super();
		this.db = new DatabaseController();
	}

	public void startProcess() {

		Copy theCopy = this.db.locateCopyInDB();

		if (theCopy != null) {

			boolean wantsToBuy = super.userInput().askBinaryQuestion("Patron wishes to buy book? (y/n)", "y", "n");

			if (wantsToBuy) {

				this.removeBookFromDB(theCopy);
				System.out.println(
						theCopy.getTitle() + " [ID: " + theCopy.getCopyID() + "] has been removed from database");

			}
		}
	}
	
	public void removeBookFromDB(Copy theCopy) {
		this.db.getCopiesDB().remove(theCopy.getCopyID());
	}
}
