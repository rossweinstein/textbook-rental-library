package textbookRentalLibrary.menus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
public class TextbookRentalLibraryMainMenu extends TRLMenu {

	private TRLSession checkOut;
	private TRLSession checkIn;
	private ManagerialMenuMain managerFunc;

	public TextbookRentalLibraryMainMenu() {
		super();
		this.checkOut = new CheckOutController();
		this.checkIn = new CheckInController();
		this.managerFunc = new ManagerialMenuMain();
	}

	private MenuBuilder mainMenu() {

		super.buildMenu().setMenuTitle("Textbook Rental Library");

		List<String> options = Arrays.asList("Start Check-Out Session", "Start Check-In Session", "Manager Functions",
				"Help", "Quit Program");
		super.buildMenu().setMenuItems(options);

		return super.buildMenu();
	}

	@Override
	public void displayMenu() {
		super.printMenuToConsole(this.mainMenu());
	}

	@Override
	protected boolean continueMakingSelections() {

		int selection = super.userInput().askForSelection(this.mainMenu().getMenuItems());

		if (selection == 1) {
			this.startCheckOutSession();

		} else if (selection == 2) {
			this.startCheckInSession();

		} else if (selection == 3) {
			this.managerFunctions();

		} else if (selection == 4) {
			this.helpMenu();

		} else {
			return false;

		}
		return true;
	}

	private void startCheckOutSession() {
		System.out.println();
		this.checkOut.startSession();
		System.out.println();
	}

	private void startCheckInSession() {
		System.out.println();
		this.checkIn.startSession();
		System.out.println();
	}

	private void managerFunctions() {
		System.out.println();
		this.managerFunc.displayMenu();
		System.out.println();
	}

	private void helpMenu() {
		System.out.println();
		
		try {
			BufferedReader in = new BufferedReader(new FileReader("HOWTO.txt"));
			String line;

			line = in.readLine();

			while (line != null) {
				System.out.println(line);
				line = in.readLine();
			}
			in.close();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		System.out.println();
	}
}
