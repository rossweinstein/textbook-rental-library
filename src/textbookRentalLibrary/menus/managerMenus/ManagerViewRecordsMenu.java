package textbookRentalLibrary.menus.managerMenus;

import java.util.Arrays;
import java.util.List;

import textbookRentalLibrary.menus.MenuBuilder;

public class ManagerViewRecordsMenu extends ManagerMenu {
	
	public ManagerViewRecordsMenu() {
		super();
	}

	private MenuBuilder miscHolds() {
		super.buildMenu().setMenuTitle("-----MANAGER: VIEW PATRON RECORDS MENU-----");

		List<String> options = Arrays.asList("Display All Patron", "Display All Patrons With Holds",
				this.menuItemFor("Unreturned Books"), this.menuItemFor("Overdue Holds"),
				this.menuItemFor("Damaged Holds"), this.menuItemFor("Unshelved Holds"),
				this.menuItemFor("Lost Holds"), this.menuItemFor("Misc. Holds"), "Exit View Patron Records Menu");
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
			this.displayAllPatrons();

		} else if (selection == 2) {
			this.displayAllPatronsWithHolds();
			
		} else if (selection == 3) {
			this.displayPatronsWithUnreturnedBooks();
			
		} else if (selection == 4) {
			this.displayPatronsWithOverdueHolds();
			
		} else if (selection == 5) {
			this.displayPatronsWithDamageHolds();
			
		} else if (selection == 6) {
			this.displayPatronsWithUnshelvedHolds();
			
		} else if (selection == 7) {
			displayPatronsWithLostHolds();
			
		} else if (selection == 8) {
			this.displayPatronsWithMiscHolds();

		} else {
			return false;
		}
		return true;
	}
	
	private void displayAllPatrons() {
		System.out.println();
		super.managerFunc().displayAllPatrons();
		System.out.println();
	}
	
	private void displayAllPatronsWithHolds() {
		System.out.println();
		super.managerFunc().displayAllPatronsWithHolds();
		System.out.println();
	}

	private void displayPatronsWithUnreturnedBooks() {
		System.out.println();
		super.managerFunc().displayPatronsWithUnreturnedTextbooks();
		System.out.println();
	}

	private void displayPatronsWithOverdueHolds() {
		System.out.println();
		super.managerFunc().displayPatronsWithOverdueHolds();
		System.out.println();
	}

	private void displayPatronsWithDamageHolds() {
		System.out.println();
		super.managerFunc().displayPatronsWithDamageHolds();
		System.out.println();
	}

	private void displayPatronsWithUnshelvedHolds() {
		System.out.println();
		super.managerFunc().displayPatronsWithUnshelvedHolds();
		System.out.println();
	}

	private void displayPatronsWithLostHolds() {
		System.out.println();
		super.managerFunc().displayPatronsWithLostHolds();
		System.out.println();
	}

	private void displayPatronsWithMiscHolds() {
		System.out.println();
		super.managerFunc().displayPatronsWithMiscHolds();
		System.out.println();
	}

	private String menuItemFor(String holdMessage) {
		return "Display All Patrons With " + holdMessage;
	}
}
