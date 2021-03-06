package model.copy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	private LocalDateTime dueDate;

	public Copy(String copyID, String title) {
		this.copyID = copyID;
		this.title = title;
		this.outTo = null;
		this.lastPersonToCheckOut = null;
		this.dueDate = null;
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
	
	public LocalDateTime getDueDate() {
		return this.dueDate;
	}
	
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
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
	public String toString() {
		return "ID: " + this.copyID + " | Title: " + this.title + " | Due Date: " + this.showDueDate();
	}
	
	/***** DOES ANYONE HAVE COPY CHECKED OUT? *******************/

	public String checkedOutBy() {
		if (this.copyIsCurrentlyAvailable()) {
			return "Copy is Currently Available";
		} else {
			return this.outTo.getPatronID();
		}
	}

	private boolean copyIsCurrentlyAvailable() {
		return this.outTo == null;
	}
	
	/***** DUE DATE / OVERDUE METHODS ***************************/
	
	private String showDueDate() {
		if (this.copyIsNotCurrentlyCheckedOut()) {
			return "N/A";
		} else {
			return this.dueDate.format(DateTimeFormatter.ISO_DATE);
		}
	}

	private boolean copyIsNotCurrentlyCheckedOut() {
		return this.dueDate == null;
	}
	
	public void checkedOut() {
		this.dueDate = LocalDateTime.now().plusDays(100);
	}
	
	public void checkedIn() {
		this.dueDate = null;
	}
	
	public boolean isOverdue() {
		return  copyIsNotCurrentlyCheckedOut() ? false : !this.dueDate.isAfter(LocalDateTime.now());
	}
}
