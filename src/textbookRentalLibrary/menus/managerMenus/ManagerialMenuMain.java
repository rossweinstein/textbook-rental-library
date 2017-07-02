package textbookRentalLibrary.menus.managerMenus;

import java.util.Arrays;
import java.util.List;

import textbookRentalLibrary.menus.CommandLineMenu;
import textbookRentalLibrary.menus.MenuBuilder;
import textbookRentalLibrary.menus.TRLMenu;


/**
 * This class creates a command line menu which lists and calls all the
 * functions a manager can do
 * 
 * @author Ross Weinstein
 *
 */
public class ManagerialMenuMain extends TRLMenu implements CommandLineMenu {

	private CommandLineMenu patronDisplayMenu;
	private CommandLineMenu holdsMenu;

	public ManagerialMenuMain() {
		super();
		this.patronDisplayMenu = new ManagerMenuDisplayPatrons();
		this.holdsMenu = new ManagerMenuHolds();
		
	}
	
	private MenuBuilder managerMainMenu() {
		super.buildMenu().setMenuTitle("Manager Functions");
		
		List<String> options = Arrays.asList("Display Patrons Menu", "Holds Menu", "Exit Manager Menu");
		super.buildMenu().setMenuItles(options);
		
		return super.buildMenu();
	}
	
	@Override
	public void displayMenu() {

		boolean stayOnMenu = true;
		while (stayOnMenu) {

			System.out.println(this.managerMainMenu().displayMenuWithoutBanner());
			stayOnMenu = continueMakingSelections();
		}
	}

	private boolean continueMakingSelections() {

		int selection = super.userInput().askForSelection(this.managerMainMenu().getMenuItems());

		if (selection == 1) {
			this.patronDisplayMenu.displayMenu();
		} else if (selection == 2) {
			this.holdsMenu.displayMenu();
		} else {
			return false;
		}
		return true;
	}
}
