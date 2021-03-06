package model.patron.hold;

import model.copy.Copy;

/**
 * Holds prevent Patrons from checking out new textbooks.  
 * 
 * @author Ross Weinstein
 *
 */

public abstract class Hold {

	private int fine;
	private Copy copy;

	public Hold(int fine, Copy copy) {
		this.fine = fine;
		this.copy = copy;
	}
	
	public abstract String getHoldMessage();
	
	public Copy getHoldCopy() {
		return this.copy;
	}

	protected String holdDescription(String holdType) {
		return "Type: " + holdType + " COPY HOLD\nFine: $" + this.getFineAmount();
	}

	public int getFineAmount() {
		return this.fine;
	}
}
