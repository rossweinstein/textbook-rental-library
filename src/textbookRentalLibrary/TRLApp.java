package textbookRentalLibrary;

/**
 * Main entry into the Textbook Rental Library (TRL) where a user can check in,
 * check out, and do manager functions
 * 
 * @author Ross Weinstein
 *
 */
public class TRLApp {

	public static void main(String[] args) {
		TextbookRentalLibraryMainMenu trl = new TextbookRentalLibraryMainMenu();
		trl.startApp();
	}

}
