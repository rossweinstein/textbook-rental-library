package textbookRentalLibrary.manager;

import java.util.List;
import java.util.stream.Collectors;

import fakeDatabase.DBConnect;
import patron.Patron;

public class ManagerialFunctionsController {

	private boolean holdsApplied;
	private DBConnect db;

	public ManagerialFunctionsController() {
		this.holdsApplied = false;
		this.db = new DBConnect();
	}

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
			this.overdueNoticesGenerated();
		} else {
			System.out.println("Holds have not been applied yet. Must apply holds first.");
		}
	}

	private void overdueNoticesGenerated() {
		System.out.println("Overdue notices generated...\n");
	}

}
