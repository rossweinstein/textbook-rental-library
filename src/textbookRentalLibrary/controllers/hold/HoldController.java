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
	
	protected DatabaseController queryDB() {
		return this.db;
	}
	
	protected Manager getManage() {
		return this.manage;
	}
	
	public int getHoldTotal() {
		return this.db.getAllPatronsWithHolds().stream().map(patron -> patron.getAllHolds().size()).mapToInt(i -> i).sum();
	}
	
	protected boolean holdsUpdatedCorrectly(int tally) {
		return this.getHoldTotal() == tally;
	}
}
