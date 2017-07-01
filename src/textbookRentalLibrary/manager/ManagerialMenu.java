package textbookRentalLibrary.manager;

import helpers.InputHelper;
import helpers.MenuBuilder;

/**
 * This class creates a command line menu which lists and calls all the
 * functions a manager can do
 * 
 * @author Ross Weinstein
 *
 */
public class ManagerialMenu {

	private InputHelper input;
	private MenuBuilder menu;
	private ManagerialFunctionsController manage;

	public ManagerialMenu() {
		this.input = new InputHelper();
		this.menu = new MenuBuilder("Manager Functions", "Display All Patrons", "Display Patrons With Overdue Notices",
				"Mark Holds", "Generate Overdue Notices", "Exit");
		this.manage = new ManagerialFunctionsController();
	}

	/**
	 * Displays the manager menu to the console
	 */
	public void managerMenu() {

		boolean runApp = true;
		while (runApp) {

			System.out.println(this.menu.displayMenuWithoutBanner());
			runApp = getSelection(runApp);
		}
	}

	/**
	 * Gets a selection and runs that manager function
	 * 
	 * @param runApp
	 *            boolean user still wants to run manager functions
	 * @return boolean continue running manager menu until user wants to exit
	 *         with a false
	 */
	private boolean getSelection(boolean runApp) {

		int selection = this.input.askForSelection(this.menu.getMenuItems());

		if (selection == 1) {
			System.out.println();
			this.manage.displayAllPatrons();
		} else if (selection == 2) {
			System.out.println();
			this.manage.displayPatronsWithBooksUnreturned();
		} else if (selection == 3) {
			System.out.println();
			this.manage.applyHolds();
		} else if (selection == 4) {
			System.out.println();
			this.manage.generateOverdueNotices();
		} else {
			runApp = false;
		}
		return runApp;
	}
}
