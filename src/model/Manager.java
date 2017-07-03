package model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import database.FakeDB;
import model.copy.Copy;
import model.patron.Patron;
import model.patron.hold.Hold;
import model.patron.hold.HoldType;

public class Manager {

	public Manager() {
	}

	public List<Patron> getAllPatronsInTRL() {
		return FakeDB.getAllPatrons();
	}

	public List<Patron> getAllPatronsWithHolds() {
		return this.getAllPatronsInTRL().stream().filter(patron -> !patron.hasNoHoldsOnRecord())
				.collect(Collectors.toList());
	}

	public int getHoldTotal() {
		return this.getAllPatronsWithHolds().stream().map(patron -> patron.getAllHolds().size()).mapToInt(i -> i).sum();
	}

	public boolean canGenerateHoldNotices() {
		return this.getAllPatronsWithHolds().size() > 0;
	}

	public List<Patron> getAllPatronsWithUnreturnedTextBooks() {
		return this.getAllPatronsInTRL().stream().filter(patron -> patron.copiesCurrentlyCheckedOut() > 0)
				.collect(Collectors.toList());
	}

	public List<Patron> getAllPatronsWithOverdueHolds() {
		return this.getSpecificHold("OVERDUE");
	}

	public boolean markOverdueHolds(int fineAmount) {

		int holdTally = this.getHoldTotal();

		for (Patron offendingPatron : this.getAllPatronsWithUnreturnedTextBooks()) {

			List<Copy> unreturnedCopies = offendingPatron.getCopiesOut();

			for (Copy eachCopy : unreturnedCopies) {

				boolean addedNewHold = offendingPatron.placeHoldOnRecord(HoldType.OVERDUE, fineAmount, eachCopy);

				if (addedNewHold) {
					holdTally++;
				}
			}
		}

		return this.holdsUpdatedCorrectly(holdTally);
	}

	private boolean holdsUpdatedCorrectly(int tally) {
		return this.getHoldTotal() == tally;
	}

	public List<Patron> getAllPatronsWithUnshelvedHolds() {
		return this.getSpecificHold("UNSHELVED");
	}

	public boolean markUnshelevedHold(Patron offendingPatron, Copy unshelvedCopy, int fineAmount) {

		int holdTally = this.getHoldTotal();

		if (!this.patronLastToCheckOutCopy(offendingPatron, unshelvedCopy)) {
			return false;
		}

		offendingPatron.placeHoldOnRecord(HoldType.UNSHELEVED, fineAmount, unshelvedCopy);

		return this.holdsUpdatedCorrectly(++holdTally);
	}

	public List<Patron> getAllPatronsWithDamageHolds() {
		return this.getSpecificHold("DAMAGED");
	}
	
	private boolean patronLastToCheckOutCopy(Patron patron, Copy copy) {
		return patron.equals(copy.getLastPersonToCheckOut());
	}

	public boolean markDamageHold(Patron offendingPatron, Copy damagedCopy, int fineAmount) {
		
		int holdTally = this.getHoldTotal();

		if (!this.patronLastToCheckOutCopy(offendingPatron, damagedCopy)) {
			return false;
		}

		offendingPatron.placeHoldOnRecord(HoldType.DAMAGED, fineAmount, damagedCopy);

		return this.holdsUpdatedCorrectly(++holdTally);
	}

	public List<Patron> getAllPatronsWithMiscHolds() {
		return this.getSpecificHold("found");
	}

	public boolean markMiscHold(Patron thePatron, String item, String location) {
		
		int holdTally = this.getHoldTotal();
		thePatron.placeLostAndFoundHold(item, location);
		return this.holdsUpdatedCorrectly(++holdTally);
	}

	private List<Patron> getSpecificHold(String holdType) {

		List<Patron> matchingPatrons = new ArrayList<>();
		String regex = ".*\\b" + holdType + "\\b.*";
		Pattern regexPattern = Pattern.compile(regex);

		for (Patron eachPatron : this.getAllPatronsInTRL()) {

			for (Hold eachHold : eachPatron.getAllHolds()) {
				
				Matcher match = regexPattern.matcher(eachHold.getHoldMessage());

				if (match.find()) {

					if (!matchingPatrons.contains(eachPatron)) {
						matchingPatrons.add(eachPatron);
					}
				}
			}
		}
		return matchingPatrons;
	}
}
