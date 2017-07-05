package textbookRentalLibrary.menus.managerMenus;

import java.util.Arrays;
import java.util.List;

import textbookRentalLibrary.menus.MenuBuilder;

public class MiscHoldsMenu extends ManagerMenu {
	
	public MiscHoldsMenu() {
		super();
	}

	private MenuBuilder miscHolds() {
		super.buildMenu().setMenuTitle("-----MANAGER: MISCELLANEOUS HOLDS MENU-----");

		List<String> options = Arrays.asList("Display All Misc. Holds", "Mark All Misc. Holds",
				"Exit Misc. Holds Menu");
		super.buildMenu().setMenuItems(options);

		return super.buildMenu();
	}

	@Override
	public void displayMenu() {
		super.printMenuToConsole(this.miscHolds());
	}

	@Override
	protected boolean continueMakingSelections() {

		int selection = super.userInput().askForSelection(this.miscHolds().getMenuItems());

		if (selection == 1) {
			this.displayAllPatronsWithMiscHolds();

		} else if (selection == 2) {
			this.markAllMiscHolds();

		} else {
			return false;
		}
		return true;
	}

	private void displayAllPatronsWithMiscHolds() {
		System.out.println();
		this.managerFunc().displayPatronsWithMiscHolds();
		System.out.println();
	}
	
	private void markAllMiscHolds() {
		System.out.println();
		this.managerFunc().markMiscHold();
		System.out.println();
	}

}
