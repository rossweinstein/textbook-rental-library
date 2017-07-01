package textbookRentalLibrary.managerFunctions;

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
	
	public void displayAllPatrons() {
		
		if (this.db.getAllPatrons().size() == 0) {
			System.out.println("There are no patrons in the system");
		} else {
			this.printDesiredPatrons(this.db.getAllPatrons());
		}
		System.out.println();
	}

	public void displayPatronsWithBooksUnreturned() {

		List<Patron> patronsWithUnreturnedBooks = this.findPatronsWithUnreturnedCopies(this.db.getAllPatrons());

		if (patronsWithUnreturnedBooks == null || patronsWithUnreturnedBooks.size() == 0) {
			System.out.println("There are no patrons with unreturned books");

		} else {

			this.printDesiredPatrons(patronsWithUnreturnedBooks);
		}

		System.out.println();
	}
	
	private void printDesiredPatrons(List<Patron> thePatrons) {
		for (int i = 0; i < thePatrons.size(); i++) {
			System.out.println((i + 1) + ": " + thePatrons.get(i));
		}
	}


	public List<Patron> findPatronsWithUnreturnedCopies(List<Patron> thePatrons) {
		return thePatrons.stream().filter(patron -> patron.copiesCurrentlyCheckedOut() > 0)
				.collect(Collectors.toList());
	}

	public void applyHolds() {
		this.findPatronsWithUnreturnedCopies(this.db.getAllPatrons()).stream()
				.forEach(patron -> patron.putHoldOnRecord());
		this.holdsApplied = true;
		System.out.println("Holds marked...\n");
	}

	public void generateOverdueNotices() {

		if (this.holdsApplied) {
			System.out.println("Overdue notices generated...\n");
		} else {
			System.out.println("Holds have not been applied yet. Must apply holds first.");
		}
	}
}
