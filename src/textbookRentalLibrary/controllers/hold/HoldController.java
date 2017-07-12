package textbookRentalLibrary.controllers.hold;

import model.Manager;
import textbookRentalLibrary.controllers.DatabaseSearch;
import textbookRentalLibrary.userInput.InputHelper;

public abstract class HoldController {
	
	private InputHelper input;
	private DatabaseSearch db;
	private Manager manage;
	
	public HoldController() {
		this.input = new InputHelper();
		this.db = new DatabaseSearch();
		this.manage = new Manager();
	}

	protected InputHelper getInput() {
		return input;
	}
	
	protected DatabaseSearch getDB() {
		return this.db;
	}
	
	protected Manager getManage() {
		return this.manage;
	}
}
