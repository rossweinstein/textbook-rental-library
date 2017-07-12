package textbookRentalLibrary.controllers.hold;

import java.util.List;
import java.util.stream.Collectors;

import model.copy.Copy;
import model.patron.Patron;
import model.patron.hold.HoldType;

/**
 * This controller interacts with our OverdueHold class to mark any unreturned
 * copies who are past their due dates as holds.
 * 
 * @author Ross Weinstein
 *
 */

public class OverdueHoldController extends PlaceHoldController {

	@Override
	public void displayHolds() {
		this.displayPatronsWith(" with overdue holds ", super.queryDB().getAllPatronsWithOverdueHolds());
	}

	@Override
	public boolean markHold() {
		return super.confirmHoldForType("Overdue") ? this.markOverdueHolds() : false;
	}

	private boolean markOverdueHolds() {
		int fineAmount = super.enterFineAmout();
		return this.successfulHoldMark(fineAmount);
	}

	public boolean successfulHoldMark(int fineAmount) {

		int holdTally = super.queryDB().getHoldTotal();
		List<Patron> patronsWithUnreturnedBooks = super.queryDB().getAllPatronsWithUnreturnedTextBooks();

		for (Patron eachPatron : patronsWithUnreturnedBooks) {

			List<Copy> overdueCopies = this.findOverdueCopies(eachPatron);
			holdTally += this.markingHolds(eachPatron, overdueCopies, fineAmount);
		}
		return this.holdsUpdatedCorrectly(holdTally);
	}

	private int markingHolds(Patron offendingPatron, List<Copy> overdueCopies, int fineAmount) {

		int newHoldsMarked = 0;

		for (Copy eachCopy : overdueCopies) {

			boolean addedNewHold = offendingPatron.placeHoldOnRecord(HoldType.OVERDUE, fineAmount, eachCopy);

			if (addedNewHold) {
				newHoldsMarked++;
			}
		}
		return newHoldsMarked;
	}

	private List<Copy> findOverdueCopies(Patron patronWithUnreturnedBooks) {
		return patronWithUnreturnedBooks.getCopiesOut().stream().filter(overdueCopy -> overdueCopy.isOverdue())
				.collect(Collectors.toList());
	}
}
