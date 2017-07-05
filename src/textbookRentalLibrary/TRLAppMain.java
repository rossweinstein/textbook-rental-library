package textbookRentalLibrary;

import textbookRentalLibrary.menus.TextbookRentalLibraryMainMenu;

/**
 * Main entry into the Textbook Rental Library (TRL) where a user can check in,
 * check out, and do manager functions
 * 
 * @author Mowlid Abdillahi | Neera Chaudhary | Ross Weinstein
 *
 */
public class TRLAppMain {

	public static void main(String[] args) {
		
		TextbookRentalLibraryMainMenu trl = new TextbookRentalLibraryMainMenu();
		trl.displayMenu();
	}

}
