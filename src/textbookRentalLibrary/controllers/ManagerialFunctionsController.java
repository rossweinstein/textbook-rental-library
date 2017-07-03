package textbookRentalLibrary.controllers;

import java.util.List;
import java.util.stream.Collectors;

import database.FakeDB;
import model.copy.Copy;
import model.patron.Patron;
import model.patron.hold.HoldType;

/**
 * This class controls all the functions that a manager can do: See all records,
 * see all records with holds, mark holds, generate overdue notices
 * 
 * @author Ross Weinstein
 *
 */
public class ManagerialFunctionsController {

	private boolean holdsApplied;

	public ManagerialFunctionsController() {
		this.holdsApplied = false;
	}
	
	public void displayAllPatrons() {
		
		if (FakeDB.getAllPatrons().size() == 0) {
			System.out.println("There are no patrons in the system");
		} else {
			this.printDesiredPatrons(FakeDB.getAllPatrons());
		}
		System.out.println();
	}

	public void displayPatronsWithUnreturnedTextbooks() {

		List<Patron> patronsWithUnreturnedBooks = this.findPatronsWithUnreturnedCopies(FakeDB.getAllPatrons());

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

	public void applyOverdueHolds(int fineAmount) {
		
		for (Patron offendingPatron : this.findPatronsWithUnreturnedCopies(FakeDB.getAllPatrons())) {
			
			List<Copy> unreturnedCopies = offendingPatron.getCopiesOut();
			
			for (Copy eachCopy : unreturnedCopies) {
				
				
				offendingPatron.placeHoldOnRecord(HoldType.OVERDUE, fineAmount, eachCopy);
			}
		}
		
		
		this.holdsApplied = true;
		System.out.println("Holds marked...\n");
	}
	
	public void applyMiscHold(String item, String location, Patron patron) {
		patron.placeLostAndFoundHold(item, location);
	}
	
	public void applyUnshelvedHold(int fineAmount, Patron patron, Copy copy) {
		patron.placeHoldOnRecord(HoldType.UNSHELEVED, fineAmount, copy);
	}

	public void generateHoldNotices() {

		if (this.holdsApplied) {
			System.out.println("Overdue notices generated...\n");
		} else {
			System.out.println("Holds have not been applied yet. Must apply holds first.");
		}
	}
}
