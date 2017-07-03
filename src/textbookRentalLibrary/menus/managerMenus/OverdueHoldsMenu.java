package textbookRentalLibrary.menus.managerMenus;

import java.util.Arrays;
import java.util.List;

import textbookRentalLibrary.menus.MenuBuilder;

public class OverdueHoldsMenu extends ManagerialMenuMain {

	public OverdueHoldsMenu() {
		super();
	}

	private MenuBuilder overdueHolds() {
		super.buildMenu().setMenuTitle("-----MANAGER: OVERDUE HOLDS MENU-----");

		List<String> options = Arrays.asList("Display All Overdue Holds", "Mark All Overdue Holds",
				"Exit Overdue Holds Menu");
		super.buildMenu().setMenuItles(options);

		return super.buildMenu();
	}

	@Override
	public void displayMenu() {
		super.printMenuToConsole(this.overdueHolds());
	}

	@Override
	protected boolean continueMakingSelections() {

		int selection = super.userInput().askForSelection(this.overdueHolds().getMenuItems());

		if (selection == 1) {
			this.displayAllPatronsWithOverdueHolds();

		} else if (selection == 2) {
			this.markAllOverdueHolds();

		} else {
			return false;
		}
		return true;
	}

	private void displayAllPatronsWithOverdueHolds() {
		System.out.println();
		this.managerFunc().displayPatronsWithUnreturnedTextbooks();
		System.out.println();
	}
	
	private void markAllOverdueHolds() {
		System.out.println();
		this.managerFunc().applyOverdueHolds(5);
		System.out.println();
	}
}
