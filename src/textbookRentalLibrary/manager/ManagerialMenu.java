package textbookRentalLibrary.manager;

import helpers.InputHelper;
import helpers.MenuBuilder;

public class ManagerialMenu {

	private InputHelper input;
	private MenuBuilder menu;
	private ManagerialFunctionsController manage;

	public ManagerialMenu() {
		this.input = new InputHelper();
		this.menu = new MenuBuilder("Manager Functions", "Display Patrons With Overdue Notices", "Mark Holds",
				"Generate Overdue Notices", "Exit");
		this.manage = new ManagerialFunctionsController();
	}

	public void managerMenu() {

		boolean runApp = true;
		while (runApp) {

			System.out.println(this.menu.displayMenuWithoutBanner());
			runApp = getSelection(runApp);
		}
	}

	private boolean getSelection(boolean runApp) {

		int selection = this.input.askForSelection(this.menu.getMenuItems());

		if (selection == 1) {
			System.out.println();
			this.manage.displayPatronsWithBooksUnreturned();
		} else if (selection == 2) {
			System.out.println();
			this.manage.applyHolds();
		} else if (selection == 3) {
			System.out.println();
			this.manage.generateOverdueNotices();
		} else {
			runApp = false;
		}
		return runApp;
	}

}
