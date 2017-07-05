package modelTest.patronTest.patronInfoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.patron.patronInfo.Address;
import model.patron.patronInfo.ContactInfo;

public class ContactInfoTest {

	ContactInfo contact;
	
	@Before
	public void createNewContactInfo() {
		this.contact = new ContactInfo();
		
		this.contact.setFirstName("Ross");
		this.contact.setLastName("Weinstein");
		this.contact.setPhoneNumber("6121234567");
		
		Address address = new Address();
		address.setAddressLineOne("123 Main Street");
		address.setAddressLineTwo("Apt 12C");
		address.setCity("Minneapolis");
		address.setState("MN");
		address.setZipCode("55410");
		
		this.contact.setLocalAddress(address);
		
		Address addressTwo = new Address();
		addressTwo.setAddressLineOne("876 East Street");
		addressTwo.setCity("St. Paul");
		addressTwo.setState("MN");
		addressTwo.setZipCode("55116");
		
		this.contact.setPermanentAddress(addressTwo);
	}
	
	@Test
	public void getFirstName() {
		assertTrue(this.contact.getFirstName().equals("Ross"));
	}
	

	@Test
	public void getLastName() {
		assertTrue(this.contact.getLastName().equals("Weinstein"));
	}
	
	@Test
	public void getTelephoneNumber() {
		assertTrue(this.contact.getPhoneNumber().equals("6121234567"));
	}
	
	@Test
	public void cannotGetInvalidTelephoneNumber() {
		this.contact.setPhoneNumber("1234");
		assertTrue(this.contact.getPhoneNumber().equals("6121234567"));
	}
	
	@Test
	public void getTheirLocalAddress() {
		
		Address address = new Address();
		address.setAddressLineOne("123 Main Street");
		address.setAddressLineTwo("Apt 12C");
		address.setCity("Minneapolis");
		address.setState("MN");
		address.setZipCode("55410");
		
		assertTrue(this.contact.getLocalAddress().equals(address));
	}
	
	@Test
	public void getTheirPermanentAddress() {
		
		Address addressTwo = new Address();
		addressTwo.setAddressLineOne("876 East Street");
		addressTwo.setCity("St. Paul");
		addressTwo.setState("MN");
		addressTwo.setZipCode("55116");
		
		assertTrue(this.contact.getPermanentAddress().equals(addressTwo));
	}
	
	@Test
	public void allContactInfoSet() {
		assertTrue(this.contact.allContactInfoSet());
	}
	
	@Test
	public void AllContactNotSet() {
		this.contact.setPermanentAddress(null);
		assertFalse(this.contact.allContactInfoSet());
	}
	
	@Test
	public void setLocalSameAsPermanentAddress() {
		
		assertNotEquals(this.contact.getPermanentAddress(), this.contact.getLocalAddress());
		
		this.contact.setPermanentAsLocalAddress();
		
		assertEquals(this.contact.getPermanentAddress(), this.contact.getLocalAddress());
	}
}
