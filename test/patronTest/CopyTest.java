package patronTest;
// SEIS 635 TP-1 : Mowlid Abdillahi | Neera Chaudhary | Ross Weinstein

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import patron.Copy;
import patron.Patron;

public class CopyTest {

	private Patron firstPatron;
	private Copy bookOne;
	private Copy bookTwo;

	@Before
	public void setUp() {
		this.firstPatron = new Patron("1", "Linus");
		this.bookOne = new Copy("123", "Book One");
		this.bookTwo = new Copy("456", "Book Two");
	}
	
	@Test
	public void CopyRegistersPatronName() {
		this.firstPatron.checkCopyOut(bookOne);
		assertEquals(this.bookOne.getOutTo().getName(), "Linus");	
	}
	
	@Test
	public void CopyHasBeenReturned() {
		this.firstPatron.checkCopyOut(bookOne);
		this.firstPatron.checkCopyIn(bookOne);
		assertNull(this.bookOne.getOutTo());
	}
	
	@Test
	public void TwoCopiesAreTheSame() {
		assertTrue(this.bookOne.equals(this.bookOne));
	}
	
	@Test
	public void TwoCopiesAreDifferent() {
		assertFalse(this.bookOne.equals(this.bookTwo));
	}
	
	@Test
	public void TwoObjectsAreDifferent() {
		assertFalse(this.bookOne.equals(firstPatron));
	}
	
	@Test
	public void CopyDoesNotEqualNull() {
		Copy nilCopy = null;
		assertFalse(this.bookOne.equals(nilCopy));
	}
	
	@Test
	public void TwoIdenticalButDifferentObjectsAreEqual() {
		Copy sameCopy = new Copy("123", "Book One");
		assertTrue(this.bookOne.equals(sameCopy));
	}
}
