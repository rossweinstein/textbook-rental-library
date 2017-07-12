package modelTest.patronTest;
// SEIS 635 TP-1 : Mowlid Abdillahi | Neera Chaudhary | Ross Weinstein

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.copy.Copy;
import model.patron.Patron;
import model.patron.PatronType;
import model.patron.hold.HoldType;
import model.patron.patronInfo.Address;
import model.patron.patronInfo.ContactInfo;

public class PatronTest {

	private Patron firstPatron;
	private Patron secondPatron;
	private Copy bookOne;
	private Copy bookTwo;

	@Before
	public void setUp() {
		this.firstPatron = this.setUpPatronOne();
		this.secondPatron = this.setUpPatronTwo();
		this.bookOne = new Copy("123", "Book One");
		this.bookTwo = new Copy("456", "Book Two");
	}

	private Patron setUpPatronOne() {

		ContactInfo rossContact = new ContactInfo();
		rossContact.setFirstName("Ross");
		rossContact.setLastName("Weinstein");
		rossContact.setPhoneNumber("3038516529");

		Address localAddress = new Address();
		localAddress.setAddressLineOne("9513 Market Street");
		localAddress.setCity("St. Paul");
		localAddress.setState("MN");
		localAddress.setZipCode("55115");
		
		rossContact.setLocalAddress(localAddress);

		Address permanentAddress = new Address();
		permanentAddress.setAddressLineOne("9513 Market Street");
		permanentAddress.setAddressLineTwo("Unit 203");
		permanentAddress.setCity("St. Paul");
		permanentAddress.setState("MN");
		permanentAddress.setZipCode("55115");
		
		rossContact.setPermanentAddress(permanentAddress);

		return new Patron("P2", rossContact, PatronType.STUDENT);

	}
	
	private Patron setUpPatronTwo() {
		ContactInfo ericContact = new ContactInfo();
		ericContact.setFirstName("Eric");
		ericContact.setLastName("Level");
		ericContact.setPhoneNumber("8673254837");

		Address localAddress = new Address();
		localAddress.setAddressLineOne("3324 Lake Street");
		localAddress.setCity("Minneapolis");
		localAddress.setState("MN");
		localAddress.setZipCode("55418");

		ericContact.setPermanentAsLocalAddress();

		return new Patron("P1", ericContact, PatronType.FACULTY);
	}
	
	@Test
	public void getCorrectNameAndID() {
		String equalString = "ID: P2 | Name: Ross Weinstein";
		assertTrue(this.firstPatron.showPatronIDAndName().equals(equalString));
	}

	@Test
	public void getPatronContactInfo() {
		this.firstPatron.getContactInfo();
	}

	@Test
	public void PatronStartsWithEmptyLibrary() {
		assertTrue(this.firstPatron.getCopiesOut().isEmpty());
	}

	@Test
	public void PatronHasOneBook() {
		this.firstPatron.checkCopyOut(bookOne);
		assertTrue(this.firstPatron.getCopiesOut().size() == 1);
	}

	@Test
	public void PatronHasReturnedALlBooks() {
		this.firstPatron.checkCopyOut(bookOne);
		this.firstPatron.checkCopyOut(bookTwo);
		this.firstPatron.checkCopyIn(bookOne);
		this.firstPatron.checkCopyIn(bookTwo);
		assertTrue(this.firstPatron.getCopiesOut().isEmpty());
	}

	@Test
	public void PatronCannotCheckOutABookThatIsAlreadyCheckedOut() {
		this.firstPatron.checkCopyOut(bookOne);
		assertFalse(secondPatron.checkCopyOut(bookOne));
	}

	@Test
	public void CannotReturnACopyTheyDoNotHave() {
		assertFalse(this.firstPatron.checkCopyIn(bookOne));
	}

	@Test
	public void PatronHasRightAmountOfBooks() {
		this.firstPatron.checkCopyOut(bookOne);
		this.firstPatron.checkCopyOut(bookTwo);
		assertTrue(this.firstPatron.copiesCurrentlyCheckedOut() == 2);
	}

	@Test
	public void PatronOneEqualsPatronOne() {
		assertTrue(this.firstPatron.equals(this.firstPatron));
	}

	@Test
	public void PatronOneDoesNotEqualPatronTwo() {
		assertFalse(this.firstPatron.equals(this.secondPatron));
	}

	@Test
	public void TwoObjectsAreDifferent() {
		assertFalse(this.firstPatron.equals(this.bookOne));
	}

	@Test
	public void PatronDoesNotEqualNull() {
		Patron nilPatron = null;
		assertFalse(this.firstPatron.equals(nilPatron));
	}

	@Test
	public void TwoIdenticalButDifferentObjectsAreEqual() {
		Patron samePatron = this.setUpPatronOne();
		assertTrue(this.firstPatron.equals(samePatron));
	}

	@Test
	public void patronDoesNotHaveHoldsOnRecord() {
		assertTrue(this.firstPatron.hasNoHoldsOnRecord());
	}

	@Test
	public void patronDoesHaveHoldsOnRecord() {

		this.firstPatron.checkCopyOut(bookOne);
		this.firstPatron.placeHoldOnRecord(HoldType.OVERDUE, 50, bookOne);
		assertFalse(this.firstPatron.hasNoHoldsOnRecord());
	}

	@Test
	public void patronResolvesHolds() {
		this.firstPatron.checkCopyOut(bookOne);
		this.firstPatron.checkCopyOut(bookTwo);

		this.firstPatron.placeHoldOnRecord(HoldType.OVERDUE, 50, bookOne);
		this.firstPatron.placeHoldOnRecord(HoldType.OVERDUE, 50, bookTwo);

		this.firstPatron.resolvedHold(this.firstPatron.getAllHolds().get(0));
		this.firstPatron.resolvedHold(this.firstPatron.getAllHolds().get(0));

		assertTrue(this.firstPatron.copiesCurrentlyCheckedOut() == 0);
		assertTrue(this.firstPatron.hasNoHoldsOnRecord());
	}

	@Test
	public void patronCannotPlaceHoldTwice() {

		this.firstPatron.checkCopyOut(this.bookOne);
		this.firstPatron.checkCopyIn(this.bookOne);

		this.firstPatron.placeHoldOnRecord(HoldType.DAMAGED, 5, this.bookOne);

		assertFalse(this.firstPatron.placeHoldOnRecord(HoldType.DAMAGED, 5, this.bookOne));
	}

	@Test
	public void resetPatronName() {
		this.firstPatron.getContactInfo().setFirstName("Newt");
		assertTrue(this.firstPatron.getContactInfo().getFirstName().equals("Newt"));
	}

	@Test
	public void resetPatronID() {
		assertTrue(this.firstPatron.getPatronID().equals("P2"));
	}
}
