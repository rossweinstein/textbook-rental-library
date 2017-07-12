package model.patron;

import java.util.ArrayList;
import java.util.List;

import model.copy.Copy;
import model.patron.hold.Hold;
import model.patron.hold.HoldFactory;
import model.patron.hold.HoldType;
import model.patron.hold.MiscHold;
import model.patron.patronInfo.ContactInfo;

/**
 * The Patron is the individual who can check in and out books, as well as have
 * holds put on their record.
 * 
 * @author Ross Weinstein
 *
 */

public class Patron {

	private ContactInfo contactInfo;
	private String patronID;
	private PatronType type;
	private ArrayList<Copy> copiesOut;
	private List<Hold> currentHolds;

	public Patron(String id, ContactInfo contactInfo, PatronType status) {
		this.patronID = id;
		this.contactInfo = contactInfo;
		this.type = status;
		this.copiesOut = new ArrayList<>();
		this.currentHolds = new ArrayList<>();
	}

	/***** GETTERS / SETTERS *******************************/

	public ContactInfo getContactInfo() {
		return contactInfo;
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

	public PatronType getStatus() {
		return this.type;
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
		return this.patronID.equals(otherPatron.patronID) && this.contactInfo.equals(otherPatron.contactInfo)
				&& this.type.equals(otherPatron.type);
	}

	@Override
	public String toString() {
		return "ID: " + this.patronID + "\nType: " + this.type.toString() + "\nName: "
				+ this.contactInfo.getFirstName() + " " + this.contactInfo.getLastName() + "\nPhone Numeber: "
				+ this.contactInfo.getFormattedTelephoneNumber() + "\n\n" + this.contactInfo.getAddresses();
	}

	public String showPatronIDAndName() {
		return "ID: " + this.patronID + " | Name: " + this.contactInfo.getFirstName() + " "
				+ this.contactInfo.getLastName();
	}


	/***** CHECK IN AND OUT COPY METHODS ************************/

	public boolean checkCopyOut(Copy desiredCopy) {
		return copyIsAvailable(desiredCopy) ? patronChecksOutCopy(desiredCopy) : false;
	}

	private boolean patronChecksOutCopy(Copy c) {
		c.setOutTo(this);
		c.checkedOut();
		return this.copiesOut.add(c);
	}

	private boolean copyIsAvailable(Copy c) {
		return c.getOutTo() == null;
	}

	public boolean checkCopyIn(Copy returningCopy) {
		return patronHasCopyCheckedOut(returningCopy) ? this.patronChecksInCopy(returningCopy) : false;
	}

	private boolean patronChecksInCopy(Copy c) {
		c.setOutTo(null);
		c.setLastPersonToCheckOut(this);
		c.checkedIn();
		return this.copiesOut.remove(c);
	}

	private boolean patronHasCopyCheckedOut(Copy c) {
		return this.copiesOut.contains(c);
	}

	public int copiesCurrentlyCheckedOut() {
		return this.copiesOut.size();
	}

	/***** HOLD METHODS ************************/

	public boolean placeHoldOnRecord(HoldType type, int fineAmount, Copy copy) {

		Hold copyHold = HoldFactory.createHold(type, fineAmount, copy);

		if (this.holdNotAlreadyPlacedOnPatron(copyHold)) {
			return this.currentHolds.add(copyHold);
		}
		return false;
	}

	private boolean holdNotAlreadyPlacedOnPatron(Hold copyHold) {
		return !this.currentHolds.contains(copyHold);
	}

	public void placeLostAndFoundHold(String item, String location) {
		this.currentHolds.add(new MiscHold(item, location));
	}

	public boolean resolvedHold(Hold holdCopy) {

		if (this.holdInvolvedUnreturnedCopy(holdCopy)) {
			this.checkCopyIn(holdCopy.getHoldCopy());
		}
		return this.currentHolds.remove(holdCopy);
	}

	private boolean holdInvolvedUnreturnedCopy(Hold holdCopy) {
		return this.copiesOut.contains(holdCopy.getHoldCopy());
	}

}
