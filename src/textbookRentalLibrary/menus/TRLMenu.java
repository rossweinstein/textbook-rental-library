package textbookRentalLibrary.menus;

import textbookRentalLibrary.userInput.InputHelper;

/**
 * 
 * @author Ross Weinstein
 *
 */

public abstract class TRLMenu implements CommandLineMenu {
	
	private InputHelper input;
	private MenuBuilder menu;
	
	public TRLMenu() {
		this.input = new InputHelper();
		this.menu = new MenuBuilder();
	}
	
	protected InputHelper userInput() {
		return this.input;
	}
	
	protected MenuBuilder buildMenu() {
		return this.menu;
	}
	
	protected void printMenuToConsole(MenuBuilder theMenu) {
		boolean stayOnMenu = true;
		while (stayOnMenu) {

			System.out.println(theMenu.displayMenuWithoutBanner());
			stayOnMenu = continueMakingSelections();
		}
	}
	
	protected abstract boolean continueMakingSelections();
}
