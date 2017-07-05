package textbookRentalLibrary.menus.managerMenus;

import java.util.Arrays;
import java.util.List;

import textbookRentalLibrary.menus.MenuBuilder;

public class DamageHoldsMenu extends ManagerMenu {

	public DamageHoldsMenu() {
		super();
	}

	private MenuBuilder damageHolds() {
		super.buildMenu().setMenuTitle("-----MANAGER: DAMAGE HOLDS MENU-----");

		List<String> options = Arrays.asList("Display All Damage Holds", "Mark All Damage Holds",
				"Exit Damage Holds Menu");
		super.buildMenu().setMenuItems(options);

		return super.buildMenu();
	}

	@Override
	public void displayMenu() {
		super.printMenuToConsole(this.damageHolds());
	}

	@Override
	protected boolean continueMakingSelections() {

		int selection = super.userInput().askForSelection(this.damageHolds().getMenuItems());

		if (selection == 1) {
			this.displayAllPatronsWithDamageHolds();

		} else if (selection == 2) {
			this.markAllDamageHold();

		} else {
			return false;
		}
		return true;
	}

	private void displayAllPatronsWithDamageHolds() {
		System.out.println();
		this.managerFunc().displayPatronsWithDamageHolds();
		System.out.println();
	}
	
	private void markAllDamageHold() {
		System.out.println();
		this.managerFunc().markDamageHold();
		System.out.println();
	}

}
