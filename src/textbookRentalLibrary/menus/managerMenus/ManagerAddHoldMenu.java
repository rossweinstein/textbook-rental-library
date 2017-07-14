package textbookRentalLibrary.menus.managerMenus;

import java.util.Arrays;
import java.util.List;

import textbookRentalLibrary.menus.MenuBuilder;

/**
 * The Manager Holds Menu allows a user to mark any hold they wish based on
 * their selection.
 * 
 * @author Ross Weinstein
 *
 */
public class ManagerAddHoldMenu extends ManagerMenu {

	public ManagerAddHoldMenu() {
		super();
	}

	private MenuBuilder miscHolds() {
		super.buildMenu().setMenuTitle("\n-----MANAGER: ADD HOLDS MENU-----");

		List<String> options = Arrays.asList("Mark Overdue Holds", "Mark Damage Hold",
				"Mark Unshelved Hold", "Mark Lost Hold", "Mark Misc Hold", "Generate Hold Notices", "Exit Hold Menu");
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
			
		} else if (selection == 6) {
			this.generateHoldNotices();

		} else {
			return false;
		}
		return true;
	}

	/********** SELECTIONS **************************************/

	private void markAllOverdueHolds() {
		System.out.println();
		this.managerFunc().markOverdueHolds();
		
	}

	private void markDamageHold() {
		System.out.println();
		this.managerFunc().markDamageHold();
		
	}

	private void markUnshelvedHolds() {
		System.out.println();
		this.managerFunc().markUnshelvedHold();
		
	}

	private void markLostHold() {
		System.out.println();
		this.managerFunc().markLostHold();
		
	}

	private void markMiscHolds() {
		System.out.println();
		this.managerFunc().markMiscHold();
		
	}
	
	private void generateHoldNotices() {
		System.out.println();
		this.managerFunc().generateHoldNotices();
		
		
	}

}
