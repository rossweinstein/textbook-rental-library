package patron;
// SEIS 635 TP-1 : Mowlid Abdillahi | Neera Chaudhary | Ross Weinstein
import java.util.ArrayList;
import java.util.List;

import fakeDatabase.FakeDB;

public class Patron {

	private String name;
	private String patronID;
	private ArrayList<Copy> copiesOut;
	private Hold holds;

	public Patron(String id, String name) {
		this.patronID = id;
		this.name = name;
		this.copiesOut = new ArrayList<>();
		this.holds = new Hold();
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
		return !this.holds.hasNoHolds();
	}
	
	public List<Copy> getUnreturnedBooked() {
		return this.holds.getHolds();
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
		return "Name: " + this.name + "\nID: " + this.patronID + "\nCopies Out: " + this.showBookList();
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

	public boolean checkCopyOut(Copy c) {

		// check if the copy is available before checking it out
		if (c.getOutTo() == null) {
			c.setOutTo(this);
			return this.copiesOut.add(c);
		} else {
			return false;
		}
	}

	public boolean checkCopyIn(Copy c) {

		// make sure they have the book before they can return it
		if (this.equals(c.getOutTo())) {
			c.setOutTo(null);
			return this.copiesOut.remove(c);
		} else {
			return false;
		}
	}
	
	public int copiesCurrentlyCheckedOut() {
		return this.copiesOut.size();
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
