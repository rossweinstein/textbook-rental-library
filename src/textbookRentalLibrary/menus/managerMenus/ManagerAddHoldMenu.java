package textbookRentalLibrary.menus.managerMenus;

import java.util.Arrays;
import java.util.List;

import textbookRentalLibrary.menus.MenuBuilder;

public class ManagerAddHoldMenu extends ManagerMenu {
	
	public ManagerAddHoldMenu() {
		super();
	}

	private MenuBuilder miscHolds() {
		super.buildMenu().setMenuTitle("-----MANAGER: ADD HOLDS MENU-----");

		List<String> options = Arrays.asList("Mark All Unreturned Textbooks As Overdue", "Mark Damage Hold",
				"Mark Unshelved Hold", "Mark Lost Hold", "Mark Misc Hold", "Exit Hold Menu");
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
			this.markAllOverdueHolds();

		} else if (selection == 2) {
			this.markDamageHold();
			
		} else if (selection == 3) {
			this.markUnshelvedHolds();
			
		} else if (selection == 4) {
			this.markLostHold();
			
		} else if (selection == 5) {
			this.markMiscHolds();

		} else {
			return false;
		}
		return true;
	}
	
	private void markAllOverdueHolds() {
		System.out.println();
		this.managerFunc().markOverdueHolds();
		System.out.println();
	}
	
	private void markDamageHold() {
		System.out.println();
		this.managerFunc().markDamageHold();
		System.out.println();
	}
	
	private void markUnshelvedHolds() {
		System.out.println();
		this.managerFunc().markUnshelvedHold();
		System.out.println();
	}
	
	private void markLostHold() {
		System.out.println();
		this.managerFunc().markLostHold();
		System.out.println();
	}
	
	private void markMiscHolds() {
		System.out.println();
		this.managerFunc().markMiscHold();
		System.out.println();
	}

}
