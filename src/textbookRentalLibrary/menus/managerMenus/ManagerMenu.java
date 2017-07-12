package textbookRentalLibrary.menus.managerMenus;

import textbookRentalLibrary.controllers.ManagerController;
import textbookRentalLibrary.menus.TRLMenu;

/**
 * 
 * @author Ross Weinstein
 *
 */

public abstract class ManagerMenu extends TRLMenu {
	
	private ManagerController manage;
	
	public ManagerMenu() {
		super();
		this.manage = new ManagerController();
	}
	
	protected ManagerController managerFunc() {
		return this.manage;
	}
}
