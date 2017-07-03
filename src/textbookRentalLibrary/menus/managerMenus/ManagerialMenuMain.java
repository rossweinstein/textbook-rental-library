package textbookRentalLibrary.menus.managerMenus;

import java.util.Arrays;
import java.util.List;

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
		super.buildMenu().setMenuTitle("-----MANAGER FUNCTIONS MENU-----");

		List<String> options = Arrays.asList("Display All Patrons", "Display All Holds", "Overdue Holds Menu",
				"Unshelved Holds Menu", "Damage Holds Menu", "Misc. Holds Menu", "Generate Hold Notices",
				"Exit Manager Menu");
		super.buildMenu().setMenuItles(options);

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
			this.displayAllPatrons();

		} else if (selection == 2) {
			this.displayAllHolds();

		} else if (selection == 3) {
			CommandLineMenu overdueHolds = new OverdueHoldsMenu();
			overdueHolds.displayMenu();

		} else if (selection == 4) {
			CommandLineMenu unshelvedHolds = new UnshelvedHoldsMenu();
			unshelvedHolds.displayMenu();

		} else if (selection == 5) {
			CommandLineMenu damageHolds = new DamageHoldsMenu();
			damageHolds.displayMenu();

		} else if (selection == 6) {
			CommandLineMenu miscHolds = new MiscHoldsMenu();
			miscHolds.displayMenu();

		} else if (selection == 7) {
			this.generateHoldNotices();

		} else {
			return false;
		}
		return true;
	}

	private void displayAllHolds() {
		System.out.println();
		this.managerFunc().displayPatronsWithUnreturnedTextbooks();
		System.out.println();
	}

	private void displayAllPatrons() {
		System.out.println();
		this.managerFunc().displayAllPatrons();
		System.out.println();
	}
	
	private void generateHoldNotices() {
		System.out.println();
		this.managerFunc().generateHoldNotices();
		System.out.println();
	}

}
