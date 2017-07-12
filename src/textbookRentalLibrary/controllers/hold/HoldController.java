package textbookRentalLibrary.controllers.hold;

import model.Manager;
import textbookRentalLibrary.controllers.DatabaseController;
import textbookRentalLibrary.userInput.InputHelper;

public abstract class HoldController {
	
	private InputHelper input;
	private DatabaseController db;
	private Manager manage;
	
	public HoldController() {
		this.input = new InputHelper();
		this.db = new DatabaseController();
		this.manage = new Manager();
	}

	protected InputHelper getInput() {
		return input;
	}
	
	protected DatabaseController getDB() {
		return this.db;
	}
	
	protected Manager getManage() {
		return this.manage;
	}
}
