package model.patron;

import java.util.ArrayList;
import java.util.List;

import model.copy.Copy;
import model.patron.hold.Hold;
import model.patron.hold.HoldFactory;
import model.patron.hold.HoldType;
import model.patron.hold.MiscHold;

/**
 * 
 * The Patron is the individual who can check in and out books, as well as have
 * holds put on their record if the fail to return a copy on time.
 * 
 * @author Ross Weinstein
 *
 */

public class Patron {

	private String name;
	private String patronID;
	private ArrayList<Copy> copiesOut;
	private List<Hold> currentHolds;

	public Patron(String id, String name) {
		this.patronID = id;
		this.name = name;
		this.copiesOut = new ArrayList<>();
		this.currentHolds = new ArrayList<>();
	}

	/***** GETTERS / SETTERS *******************************/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPatronID() {
		return patronID;
	}

	public ArrayList<Copy> getCopiesOut() {
		return copiesOut;
	}

	public boolean hasNoHoldsOnRecord() {
		return this.currentHolds.size() == 0;
	}

	public List<Hold> getAllHolds() {
		return this.currentHolds;
	}

	/***** OVERRIDES ********************************************/

	@Override
	public boolean equals(Object o) {

		// self check
		if (this == o) {
			return true;
		}

		// null check and type check
		if (o == null || !(o instanceof Patron)) {
			return false;
		}

		// cast and comparisons
		Patron otherPatron = (Patron) o;
		return this.patronID.equals(otherPatron.patronID) && this.name.equals(otherPatron.name);
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((this.patronID == null) ? 0 : this.patronID.hashCode());
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Name: " + this.name + " [ID: " + this.patronID + "]";
	}

	// checks to see if the Patron has any books currently checked out; If they
	// do, it'll list all of them with title and ID number in a comma separated
	// list; if no books are checked out, it'll state no book are currently
	// checked out
	public String showBookList() {

		if (this.copiesOut.isEmpty()) {
			return "No Books Currently Checked Out";
		}

		String theCopies = "";

		for (int i = 0; i < this.copiesOut.size(); i++) {

			if (i < this.copiesOut.size() - 1) {
				theCopies += this.copiesOut.get(i).getTitle() + " [ID: " + this.copiesOut.get(i).getCopyID() + "], ";
			} else {
				theCopies += this.copiesOut.get(i).getTitle() + " [ID: " + this.copiesOut.get(i).getCopyID() + "]";
			}
		}
		return theCopies;
	}

	/***** CHECK IN AND OUT COPY METHODS ************************/

	/**
	 * Allows for a Patron to check out a particular Copy by first checking if
	 * that Copy is available. If it is available, Patron is able to check it
	 * out.
	 * 
	 * @param c
	 *            the Copy the Patron wants to check out
	 * @return boolean true if the copyID entered is available to be checked
	 *         out; false otherwise
	 */
	public boolean checkCopyOut(Copy c) {

		// check if the copy is available before checking it out
		if (c.getOutTo() == null) {
			c.setOutTo(this);
			return this.copiesOut.add(c);
		} else {
			return false;
		}
	}

	/**
	 * Allows for a Patron to check in a particular Copy by first checking if
	 * that Copy is associated with the Patron. If it is associated with the
	 * Patron, the Patron is able to check that Copy back in.
	 * 
	 * @param c
	 *            the Copy the Patron wants to check in
	 * @return boolean true if the copyID entered is associated with the Patron
	 *         and can be checked in; false otherwise
	 */
	public boolean checkCopyIn(Copy c) {

		// make sure they have the book before they can return it
		if (this.copiesOut.contains(c)) {
			c.setOutTo(null);
			c.setLastPersonToCheckOut(this);
			return this.copiesOut.remove(c);
		} else {
			return false;
		}
	}

	/**
	 * The number of Copy objects currently checked out by the Patron
	 * 
	 * @return int the number of Copy objects checked out
	 */
	public int copiesCurrentlyCheckedOut() {
		return this.copiesOut.size();
	}

	/***** HOLD METHODS ************************/

	public boolean placeHoldOnRecord(HoldType type, int fineAmount, Copy copy) {

		Hold copyHold = HoldFactory.createHold(type, fineAmount, copy);

		if (!this.currentHolds.contains(copyHold)) {
			return this.currentHolds.add(copyHold);
		}
		
		return false;
	}

	public void placeLostAndFoundHold(String item, String location) {
		this.currentHolds.add(new MiscHold(item, location));
	}

	/**
	 * Patron has resolved their holds and can check out book again. Books are
	 * returned, or bought, and fine is paid.
	 */
	public void resolvedHold(Hold holdCopy) {

		if (this.copiesOut.contains(holdCopy.getHoldCopy())) {
			this.copiesOut.remove(holdCopy.getHoldCopy());
		}

		this.currentHolds.remove(holdCopy);
	}
}
