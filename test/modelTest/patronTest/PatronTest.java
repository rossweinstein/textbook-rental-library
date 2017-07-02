package modelTest.patronTest;
// SEIS 635 TP-1 : Mowlid Abdillahi | Neera Chaudhary | Ross Weinstein

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.copy.Copy;
import model.patron.Patron;
import model.patron.hold.HoldType;

public class PatronTest {
	
	private Patron firstPatron;
	private Patron secondPatron;
	private Copy bookOne;
	private Copy bookTwo;

	@Before
	public void setUp() {
		this.firstPatron = new Patron("1", "Linus");
		this.secondPatron = new Patron("2", "Newt");
		this.bookOne = new Copy("123", "Book One");
		this.bookTwo = new Copy("456", "Book Two");
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
		Patron samePatron = new Patron("1", "Linus");
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
}
