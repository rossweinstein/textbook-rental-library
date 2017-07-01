package patron;

import java.util.ArrayList;

import fakeDatabase.FakeDB;

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
	private boolean hasHolds;

	public Patron(String id, String name) {
		this.patronID = id;
		this.name = name;
		this.copiesOut = new ArrayList<>();
		this.hasHolds = false;
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

	public void setPatronID(String patronID) {
		this.patronID = patronID;
	}

	public ArrayList<Copy> getCopiesOut() {
		return copiesOut;
	}

	public void setCopiesOut(ArrayList<Copy> copiesOut) {
		this.copiesOut = copiesOut;
	}

	public boolean hasHoldsOnRecord() {
		return this.hasHolds;
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
		return "Name: " + this.name + " | ID: " + this.patronID + " | Copies Out: " + this.showBookList();
	}

	// checks to see if the Patron has any books currently checked out; If they
	// do, it'll list all of them with title and ID number in a comma separated
	// list; if no books are checked out, it'll state no book are currently
	// checked out
	private String showBookList() {

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
	 * Allows for a Patron to check out a particular Copy
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
	 * Allows for a Patron to check in a particular Copy
	 * 
	 * @param c
	 *            the Copy the Patron wants to check in
	 * @return boolean true if the copyID entered is associated with the Patron
	 *         and can be checked in; false otherwise
	 */
	public boolean checkCopyIn(Copy c) {

		// make sure they have the book before they can return it
		if (this.equals(c.getOutTo())) {
			c.setOutTo(null);
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

	/**
	 * Marks a hold on the Patron's record
	 */
	public void putHoldOnRecord() {
		this.hasHolds = true;
	}

	/**
	 * Patron has resolved their holds and can check out book again. Books are
	 * returned, or bought, and fine is paid.
	 */
	public void resolvedHolds() {
		this.returnAllBooks();
		this.hasHolds = false;
	}

	/**
	 * All Copy objects are removed from Patron's record
	 */
	private void returnAllBooks() {
		this.copiesOut.stream().forEach(copy -> copy.holdReturned());
		this.copiesOut = new ArrayList<>();
	}

	public static void main(String[] args) {

		Copy bookOne = FakeDB.getCopy("C1");
		Copy bookTwo = FakeDB.getCopy("C2");

		Patron firstPatron = FakeDB.getPatron("P1");
		Patron secondPatron = FakeDB.getPatron("P2");

		System.out.println("----TEST: CHECK IN AND OUT----");
		System.out.println("\nPatron With No Books Checked Out--");
		System.out.println(firstPatron);

		System.out.println("\nPatron With One Book Checked Out--");
		firstPatron.checkCopyOut(bookOne);
		System.out.println(firstPatron);

		System.out.println("\nPatron With Two Books Checked Out--");
		firstPatron.checkCopyOut(bookTwo);
		System.out.println(firstPatron);

		System.out.println("\nPatron After One Book Returned--");
		firstPatron.checkCopyIn(bookOne);
		System.out.println(firstPatron);

		System.out.println("\nPatron After Second Book Returned--");
		firstPatron.checkCopyIn(bookTwo);
		System.out.println(firstPatron);

		System.out.println("\n---TEST: CANNOT CHECK OUT A BOOK THAT IS ALREADY CHECKED OUT----");
		System.out.print("\nPatron Two Is Able To Check Out A Book Held By Patron One: ");
		firstPatron.checkCopyOut(bookOne);
		System.out.println(secondPatron.checkCopyOut(bookOne));

		System.out.println("\n----TEST: PATRON EQUALS----");
		System.out.print("\nPatron one equals Patron one: ");
		System.out.println(firstPatron.equals(firstPatron));
		System.out.print("Patron one equals Patron two: ");
		System.out.println(firstPatron.equals(secondPatron));
	}
}
