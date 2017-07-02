package textbookRentalLibrary.menus;

import java.util.Arrays;
import java.util.List;

import textbookRentalLibrary.controllers.checkInAndOutCopy.CheckInController;
import textbookRentalLibrary.controllers.checkInAndOutCopy.CheckOutController;
import textbookRentalLibrary.controllers.checkInAndOutCopy.TRLSession;
import textbookRentalLibrary.menus.managerMenus.ManagerialMenuMain;

/**
 * This class creates a command line menu which lists and calls all the
 * functions for the TRL application
 * 
 * @author Ross Weinstein
 *
 */
public class TextbookRentalLibraryMainMenu extends TRLMenu implements CommandLineMenu {

	private TRLSession checkOut;
	private TRLSession checkIn;
	private CommandLineMenu managerFunc;

	public TextbookRentalLibraryMainMenu() {
		super();
		this.checkOut = new CheckOutController();
		this.checkIn = new CheckInController();
		this.managerFunc = new ManagerialMenuMain();
	}
	
	private MenuBuilder mainMenu() {
		
		super.buildMenu().setMenuTitle("Textbook Rental Library");
		
		List<String> options = Arrays.asList("Check-Out Book", "Check-In Book",
				"Manager Functions", "Exit");
		super.buildMenu().setMenuItles(options);
		
		return super.buildMenu();
	}

	@Override
	public void displayMenu() {

		boolean runApp = true;
		while (runApp) {

			System.out.println(this.mainMenu().displayMenuWithoutBanner());
			runApp = getSelection(runApp);
		}
		System.out.println("\nEnd of Program...");
	}

	private boolean getSelection(boolean runApp) {

		int selection = super.userInput().askForSelection(this.mainMenu().getMenuItems());

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
			this.managerFunc.displayMenu();
			System.out.println();
		} else {
			runApp = false;
		}
		return runApp;
	}

}
