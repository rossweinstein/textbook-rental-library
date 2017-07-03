package textbookRentalLibrary.menus.managerMenus;

import textbookRentalLibrary.controllers.ManagerialFunctionsController;
import textbookRentalLibrary.menus.TRLMenu;

public abstract class ManagerMenu extends TRLMenu {
	
	private ManagerialFunctionsController manage;
	
	public ManagerMenu() {
		super();
		this.manage = new ManagerialFunctionsController();
	}
	
	protected ManagerialFunctionsController managerFunc() {
		return this.manage;
	}
}
