package textbookRentalLibrary.menus.managerMenus;

import java.util.Arrays;
import java.util.List;

import textbookRentalLibrary.controllers.BuyBookController;
import textbookRentalLibrary.menus.CommandLineMenu;
import textbookRentalLibrary.menus.MenuBuilder;

/**
 * This class creates a command line menu which lists and calls all the
 * functions a manager can do
 * 
 * @author Ross Weinstein
 *
 */
public class ManagerialMenuMain extends ManagerMenu {

	public ManagerialMenuMain() {
		super();
	}

	private MenuBuilder managerMainMenu() {
		super.buildMenu().setMenuTitle("\n-----MANAGER FUNCTIONS MENU-----");

		List<String> options = Arrays.asList("View Records", "Add Hold", "Resolve Hold", "Buy Books", "Exit Manager Menu");
		super.buildMenu().setMenuItems(options);

		return super.buildMenu();
	}

	@Override
	public void displayMenu() {
		super.printMenuToConsole(this.managerMainMenu());
	}

	@Override
	protected boolean continueMakingSelections() {

		int selection = super.userInput().askForSelection(this.managerMainMenu().getMenuItems());

		if (selection == 1) {
			CommandLineMenu recordsMenu = new ManagerViewRecordsMenu();
			recordsMenu.displayMenu();

		} else if (selection == 2) {
			CommandLineMenu holdMenu = new ManagerAddHoldMenu();
			holdMenu.displayMenu();

		} else if (selection == 3) {
			this.resolvePatronHolds();
			
		} else if (selection == 4) {
			this.buyTextbook();
			
		} else {
			return false;
		}
		return true;
	}
	
	/********** SELECTIONS **************************************/

	private void buyTextbook() {
		BuyBookController buyBook = new BuyBookController();
		buyBook.startProcess();
		System.out.println();
	}

	private void resolvePatronHolds() {
		super.managerFunc().resolvePatronHold();
		System.out.println();
	}
}
