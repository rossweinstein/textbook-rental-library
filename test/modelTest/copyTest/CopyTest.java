package modelTest.copyTest;
// SEIS 635 TP-1 : Mowlid Abdillahi | Neera Chaudhary | Ross Weinstein

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import model.copy.Copy;
import model.patron.Patron;
import model.patron.patronInfo.Address;
import model.patron.patronInfo.ContactInfo;

public class CopyTest {

	private Patron firstPatron;
	private Copy bookOne;
	private Copy bookTwo;

	@Before
	public void setUp() {
		this.firstPatron = this.setUpPatronOne();
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

		Address permanentAddress = new Address();
		permanentAddress.setAddressLineOne("9513 Market Street");
		permanentAddress.setAddressLineTwo("Unit 203");
		permanentAddress.setCity("St. Paul");
		permanentAddress.setState("MN");
		permanentAddress.setZipCode("55115");

		rossContact.setPermanentAddress(permanentAddress);

		return new Patron("P2", rossContact);

	}
	
	@Test
	public void getDueDate() {
		bookOne.checkedOut();
		LocalDateTime time = LocalDateTime.now().plusDays(100);
		String compareString = "ID: 123 | Title: Book One | Due Date: " + time.format(DateTimeFormatter.ISO_DATE);
		assertTrue(bookOne.toString().equals(compareString));
	}
	
	@Test
	public void copyIsNotOverdue() {
		bookOne.checkedOut();
		assertFalse(bookOne.isOverdue());
	}
	
	@Test
	public void copyIsOverdue() {
		bookOne.checkedOut();
		bookOne.setDueDate(LocalDateTime.now().minusDays(1));
		assertTrue(bookOne.isOverdue());
	}
	
	@Test
	public void uncheckedCopyCannotBeOverdue() {
		assertFalse(bookOne.isOverdue());
	}
	
	
	@Test
	public void CopyRegistersPatronName() {
		this.firstPatron.checkCopyOut(bookOne);
		assertEquals(this.bookOne.getOutTo().getContactInfo().getFirstName(), "Ross");	
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
	
	@Test
	public void holdCopyHasBeenReturned() {
		this.firstPatron.checkCopyOut(bookOne);
		this.firstPatron.checkCopyIn(bookOne);
		this.bookOne.holdReturned();
		boolean result = bookOne.getOutTo() == null;
		
		assertTrue(result);
	}
	
	@Test
	public void testGetOutTo() {
		this.firstPatron.checkCopyOut(bookOne);
		
		assertTrue(this.bookOne.getOutTo().equals(firstPatron));
	}
	
	@Test
	public void holdCopyIsNotCheckedOutByAnyone() {
		assertTrue(this.bookOne.checkedOutBy().equals("Copy is Currently Available"));
	}
	
	@Test
	public void holdCopyIsCheckedOutBySomeone() {
		
		this.firstPatron.checkCopyOut(this.bookOne);
		
		assertTrue(this.bookOne.checkedOutBy().equals(this.firstPatron.toString()));
	}
	
	@Test
	public void correctToString() {
		assertTrue(this.bookOne.toString().equals("ID: 123 | Title: Book One | Due Date: N/A"));
	}
	

	
	@Test
	public void correctHashCode() {
		
		int bookHash = this.hashCode(this.bookOne.getCopyID(), this.bookOne.getTitle());
		
		assertTrue(this.bookOne.hashCode() == bookHash);
	}
	
	private int hashCode(String copyID, String title) {
		int prime = 29;
		int result = 1;
		result = prime * result + ((copyID == null) ? 0 : copyID.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
}
