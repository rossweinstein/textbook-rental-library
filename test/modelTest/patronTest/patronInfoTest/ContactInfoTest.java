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
	}
	
	@Test
	public void getFirstName() {
		this.contact.setFirstName("Ross");
		assertTrue(this.contact.getFirstName().equals("Ross"));
	}
	

	@Test
	public void getLastName() {
		this.contact.setLastName("Weinstein");
		assertTrue(this.contact.getLastName().equals("Weinstein"));
	}
	
	@Test
	public void getTelephoneNumber() {
		this.contact.setPhoneNumber("6121234567");
		assertTrue(this.contact.getPhoneNumber().equals("6121234567"));
	}
	
	@Test
	public void cannotGetInvalidTelephoneNumber() {
		this.contact.setPhoneNumber("61212347");
		assertTrue(this.contact.getPhoneNumber().equals(""));
	}
	
	@Test
	public void getTheirLocalAddress() {
		
		Address address = new Address();
		address.setAddressLineOne("123 Main Street");
		address.setAddressLineTwo("Apt 12C");
		address.setCity("Minneapolis");
		address.setState("MN");
		address.setZipCode("55410");
		
		this.contact.setLocalAddress(address);
		assertTrue(this.contact.getLocalAddress().equals(address));
	}
	
	@Test
	public void getTheirPermanentAddress() {
		
		Address addressTwo = new Address();
		addressTwo.setAddressLineOne("876 East Street");
		addressTwo.setCity("St. Paul");
		addressTwo.setState("MN");
		addressTwo.setZipCode("55116");
		
		this.contact.setPermanentAddress(addressTwo);
		assertTrue(this.contact.getPermanentAddress().equals(addressTwo));
	}
	
	@Test
	public void allContactInfoSet() {
		assertTrue(this.contact.allContactInfoSet());
	}
}
