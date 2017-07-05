package textbookRentalLibrary.menus.managerMenus;

import java.util.Arrays;
import java.util.List;

import textbookRentalLibrary.menus.MenuBuilder;

public class UnshelvedHoldsMenu extends ManagerialMenuMain {
	
	public UnshelvedHoldsMenu() {
		super();
	}

	private MenuBuilder unshelvedHolds() {
		super.buildMenu().setMenuTitle("-----MANAGER: UNSHELVED HOLDS MENU-----");

		List<String> options = Arrays.asList("Display All Unshelved Holds", "Mark New Unshelved Hold",
				"Exit Unshelved Holds Menu");
		super.buildMenu().setMenuItems(options);

		return super.buildMenu();
	}

	@Override
	public void displayMenu() {
		super.printMenuToConsole(this.unshelvedHolds());
	}

	@Override
	protected boolean continueMakingSelections() {

		int selection = super.userInput().askForSelection(this.unshelvedHolds().getMenuItems());

		if (selection == 1) {
			this.displayAllPatronsWithUnshelvedHolds();

		} else if (selection == 2) {
			this.markAllUnshelvedHolds();

		} else {
			return false;
		}
		return true;
	}

	private void displayAllPatronsWithUnshelvedHolds() {
		System.out.println();
		this.managerFunc().displayPatronsWithUnshelvedHolds();
		System.out.println();
	}
	
	private void markAllUnshelvedHolds() {
		System.out.println();
		this.managerFunc().markUnshelvedHold();
		System.out.println();
	}

}
