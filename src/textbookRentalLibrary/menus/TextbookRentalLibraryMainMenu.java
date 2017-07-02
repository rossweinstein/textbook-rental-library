package textbookRentalLibrary.menus;

import textbookRentalLibrary.controllers.checkInAndOutCopy.CheckInController;
import textbookRentalLibrary.controllers.checkInAndOutCopy.CheckOutController;
import textbookRentalLibrary.controllers.checkInAndOutCopy.TRLSession;
import textbookRentalLibrary.userInput.InputHelper;

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

	public void startApp() {

		boolean runApp = true;
		while (runApp) {

			System.out.println(this.mainMenu.displayMenuWithoutBanner());
			runApp = getSelection(runApp);
		}
		System.out.println("\nEnd of Program...");
	}

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