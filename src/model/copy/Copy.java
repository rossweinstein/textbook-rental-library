package model.copy;

import model.patron.Patron;

/**
 * The Copy class has all the basic functionality for the textbooks that can be
 * check in and out in our TRL application.  
 * 
 * @author Ross Weinstein
 *
 */

public class Copy {

	private String copyID;
	private String title;
	private Patron outTo;
	private Patron lastPersonToCheckOut;

	public Copy(String copyID, String title) {
		this.copyID = copyID;
		this.title = title;
		this.outTo = null;
		this.lastPersonToCheckOut = null;
	}

	/***** GETTERS / SETTERS *******************************/

	public String getCopyID() {
		return copyID;
	}

	public String getTitle() {
		return title;
	}

	public Patron getOutTo() {
		return outTo;
	}

	public void setOutTo(Patron outTo) {
		this.outTo = outTo;
	}

	public void holdReturned() {
		this.outTo = null;
	}
	
	public Patron getLastPersonToCheckOut() {
		return this.lastPersonToCheckOut;
	}
	
	public void setLastPersonToCheckOut(Patron patron) {
		this.lastPersonToCheckOut = patron;
	}

	/***** OVERRIDES ********************************************/

	@Override
	public boolean equals(Object o) {

		// self check
		if (this == o) {
			return true;
		}

		// null check and type check
		if (o == null || !(o instanceof Copy)) {
			return false;
		}
		// cast and comparisons
		Copy otherCopy = (Copy) o;
		return this.copyID.equals(otherCopy.copyID) && this.title.equals(otherCopy.title);
	}

	@Override
	public int hashCode() {
		int prime = 29;
		int result = 1;
		result = prime * result + ((this.copyID == null) ? 0 : this.copyID.hashCode());
		result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Title: " + this.title + " [ID: " + this.copyID +"]";
	}

	/**
	 * Checks to see if the copy is currently checked and prints the name and ID
	 * of the patron who has it; otherwise it says it's available
	 * 
	 * @return String the Patron and PatronID or a message saying that the copy
	 *         is currently available
	 */
	public String checkedOutBy() {
		if (this.outTo == null) {
			return "Copy is Currently Available";
		} else {
			return this.outTo.toString();
		}
	}
}
