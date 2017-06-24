package patron;
import fakeDatabase.FakeDB;

// SEIS 635 TP-1 : Mowlid Abdillahi | Neera Chaudhary | Ross Weinstein

public class Copy {

	private String copyID;
	private String title;
	private Patron outTo;

	public Copy(String copyID, String title) {
		this.copyID = copyID;
		this.title = title;
		this.outTo = null;
	}

	/***** GETTERS / SETTERS *******************************/

	public String getCopyID() {
		return copyID;
	}

	public void setCopyID(String copyID) {
		this.copyID = copyID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Patron getOutTo() {
		return outTo;
	}

	public void setOutTo(Patron outTo) {
		this.outTo = outTo;
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
		return "CopyID: " + this.copyID + "\nTitle: " + this.title + "\nChecked Out By: " + this.checkedOutBy();
	}

	// checks to see if the copy is currently checked and prints the name and ID
	// of the patron who has it; otherwise it says it's available
	private String checkedOutBy() {
		if (this.outTo == null) {
			return "Copy is Currently Available";
		} else {
			return this.outTo.getName() + " [ID: " + this.outTo.getPatronID() + "]";
		}
	}

	public static void main(String[] args) {

		Copy firstCopy = FakeDB.getCopy("C1");
		Copy secondCopy = FakeDB.getCopy("C2");
		Patron thePatron = FakeDB.getPatron("P1");

		System.out.println("Information For First Book--");
		System.out.println(firstCopy);
		System.out.println();
		System.out.println("Information For Second Book--");
		System.out.println(secondCopy);

		System.out.println("\n----TEST: COPY AND PATRONS----");
		thePatron.checkCopyOut(firstCopy);
		System.out.println("\nBook One Checked Out--");
		System.out.println(firstCopy);
		System.out.println("\nBook One Returned--");
		thePatron.checkCopyIn(firstCopy);
		System.out.println(firstCopy);

		System.out.println("\n----TEST: EQUALITY OF TWO BOOKS----");
		System.out.print("\nBook One Equals Book One: ");
		System.out.println(firstCopy.equals(firstCopy));
		System.out.print("Book One Equals Book Two: ");
		System.out.println(firstCopy.equals(secondCopy));
	}
}
