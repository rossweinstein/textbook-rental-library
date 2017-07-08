package textbookRentalLibrary.controllers.hold;

import textbookRentalLibrary.controllers.DatabaseSearch;
import textbookRentalLibrary.userInput.InputHelper;

public abstract class HoldController {
	
	private InputHelper input;
	private DatabaseSearch db;
	
	public HoldController() {
		this.input = new InputHelper();
		this.db = new DatabaseSearch();
	}

	protected InputHelper getInput() {
		return input;
	}
	
	protected DatabaseSearch getDB() {
		return this.db;
	}
}
