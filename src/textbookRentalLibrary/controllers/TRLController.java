package textbookRentalLibrary.controllers;

import textbookRentalLibrary.userInput.InputHelper;

public abstract class TRLController {
	
	private InputHelper input;
	
	public TRLController() {
		this.input = new InputHelper();
	}
	
	protected InputHelper userInput() {
		return this.input;
	}

}
