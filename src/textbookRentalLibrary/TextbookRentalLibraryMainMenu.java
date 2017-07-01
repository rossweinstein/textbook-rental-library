package textbookRentalLibrary;

import helpers.InputHelper;
import helpers.MenuBuilder;
import textbookRentalLibrary.checkInAndOut.CheckInController;
import textbookRentalLibrary.checkInAndOut.CheckOutController;
import textbookRentalLibrary.checkInAndOut.TRLSession;
import textbookRentalLibrary.manager.ManagerialMenu;

/**
 * This class creates a command line menu which lists and calls all the
 * functions for the TRL application
 * 
 * @author Ross Weinstein
 *
 */
public class TextbookRentalLibraryMainMenu {

	private TRLSession checkOut;
	private TRLSession checkIn;
	private ManagerialMenu managerFunc;
	private InputHelper input;
	private MenuBuilder mainMenu;

	public TextbookRentalLibraryMainMenu() {
		this.checkOut = new CheckOutController();
		this.checkIn = new CheckInController();
		this.managerFunc = new ManagerialMenu();
		this.input = new InputHelper();
		this.mainMenu = new MenuBuilder("Textbook Rental Library", "Check-Out Book", "Check-In Book",
				"Manager Functions", "Exit");
	}

	/**
	 * Displays the TRL menu to the console
	 */
	public void startApp() {

		boolean runApp = true;
		while (runApp) {

			System.out.println(this.mainMenu.displayMenuWithoutBanner());
			runApp = getSelection(runApp);
		}
		System.out.println("\nEnd of Program...");
	}

	/**
	 * Gets a selection and runs that TRL function
	 * 
	 * @param runApp
	 *            boolean user still wants to run TRL functions
	 * @return boolean continue running TRL menu until user wants to exit
	 *         with a false
	 */
	private boolean getSelection(boolean runApp) {

		int selection = this.input.askForSelection(this.mainMenu.getMenuItems());

		if (selection == 1) {
			System.out.println();
			this.checkOut.startSession();
			System.out.println();
		} else if (selection == 2) {
			System.out.println();
			this.checkIn.startSession();
			System.out.println();
		} else if (selection == 3) {
			System.out.println();
			this.managerFunc.managerMenu();
			System.out.println();
		} else {
			runApp = false;
		}
		return runApp;
	}

}
