package textbookRentalLibrary.manager;

import java.util.List;
import java.util.stream.Collectors;

import fakeDatabase.DBConnect;
import patron.Patron;

/**
 * This class controls all the functions that a manager can do: See all records,
 * see all records with holds, mark holds, generate overdue notices
 * 
 * @author Ross Weinstein
 *
 */
public class ManagerialFunctionsController {

	private boolean holdsApplied;
	private DBConnect db;

	public ManagerialFunctionsController() {
		this.holdsApplied = false;
		this.db = new DBConnect();
	}
	
	/**
	 * Prints to the console all Patrons
	 */
	public void displayAllPatrons() {
		
		if (this.db.getAllPatrons().size() == 0) {
			System.out.println("There are no patrons in the system");
		} else {
			for (int i = 0; i < this.db.getAllPatrons().size(); i++) {
				System.out.println((i + 1) + ": " + this.db.getAllPatrons().get(i));
			}
		}
		System.out.println();
	}

	/**
	 * Prints to the console all Patrons with unreturned books
	 */
	public void displayPatronsWithBooksUnreturned() {

		List<Patron> patronsWithUnreturnedBooks = this.findPatronsWithUnreturnedCopies(this.db.getAllPatrons());

		if (patronsWithUnreturnedBooks == null || patronsWithUnreturnedBooks.size() == 0) {
			System.out.println("There are no patrons with unreturned books");

		} else {

			for (int i = 0; i < patronsWithUnreturnedBooks.size(); i++) {
				System.out.println((i + 1) + ": " + patronsWithUnreturnedBooks.get(i));
			}
		}

		System.out.println();
	}

	/**
	 * Searches FakeDB and returns only Patrons with unreturned copies
	 * 
	 * 
	 * @param thePatrons
	 *            List the list of Patrons to check who has unreturned copies
	 * @return List the Patrons who have unreturned copies
	 */
	public List<Patron> findPatronsWithUnreturnedCopies(List<Patron> thePatrons) {
		return thePatrons.stream().filter(patron -> patron.copiesCurrentlyCheckedOut() > 0)
				.collect(Collectors.toList());
	}

	/**
	 * Goes through all Patron who have unreturned copies and mark their accounts with a hold
	 */
	public void applyHolds() {
		this.findPatronsWithUnreturnedCopies(this.db.getAllPatrons()).stream()
				.forEach(patron -> patron.putHoldOnRecord());
		this.holdsApplied = true;
		System.out.println("Holds marked...\n");
	}

	/**
	 * Goes through all Patrons who have holds and their record and generates overdue notices
	 */
	public void generateOverdueNotices() {

		if (this.holdsApplied) {
			System.out.println("Overdue notices generated...\n");
		} else {
			System.out.println("Holds have not been applied yet. Must apply holds first.");
		}
	}
}
