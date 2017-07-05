package modelTest.patronTest.patronInfoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import database.FakeDB;
import model.patron.patronInfo.Address;
import model.patron.patronInfo.ContactInfo;

public class ContactInfoTest {

	ContactInfo contact;

	@Before
	public void createNewContactInfo() {
		this.contact = this.basicContact();
	}
	
	@Test
	public void getAddressesBlankAddresses() {
		
		ContactInfo someContact = new ContactInfo();
		
		
		assertTrue(someContact.getAddresses().equals("This Patron Currently Has No Addresses On File"));
	}
	
	@Test
	public void getAddressWithOnlyOneAddress1() {
		
		ContactInfo someContact = new ContactInfo();
		
		Address addressTwo = new Address();
		addressTwo.setAddressLineOne("876 East Street");
		addressTwo.setCity("St. Paul");
		addressTwo.setState("MN");
		addressTwo.setZipCode("55116");
		
		someContact.setLocalAddress(addressTwo);
		
		String comparisonString = "Address:\n" + addressTwo.toString();

		assertTrue(someContact.getAddresses().equals(comparisonString));
	}
	
	@Test
	public void getAddressWithOnlyOneAddress2() {
		
		ContactInfo someContact = new ContactInfo();
		
		Address addressTwo = new Address();
		addressTwo.setAddressLineOne("876 East Street");
		addressTwo.setCity("St. Paul");
		addressTwo.setState("MN");
		addressTwo.setZipCode("55116");
		
		someContact.setLocalAddress(addressTwo);
		someContact.setPermanentAsLocalAddress();
		
		String comparisonString = "Address:\n" + addressTwo.toString();

		assertTrue(someContact.getAddresses().equals(comparisonString));
	}

	@Test
	public void getAddresses() {

		String formattedString = "Local Address:" + "\n" + this.contact.getLocalAddress().toString()
				+ "\n\nPermanent Address" + "\n" + this.contact.getPermanentAddress().toString();

		assertTrue(this.contact.getAddresses().equals(formattedString));
	}

	@Test
	public void printFormattedTelephone() {
		assertTrue(this.contact.getFormattedTelephoneNumber().equals("612.123.4567"));
	}

	@Test
	public void printFormattedTelephoneWithNoNumber() {
		ContactInfo badContact = new ContactInfo();
		assertTrue(badContact.getFormattedTelephoneNumber().equals(""));
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
	public void setPermanentAsLocalAddress() {

		assertNotEquals(this.contact.getPermanentAddress(), this.contact.getLocalAddress());

		this.contact.setPermanentAsLocalAddress();

		assertEquals(this.contact.getPermanentAddress(), this.contact.getLocalAddress());
	}

	@Test
	public void setLocalAsPermanentAddress() {

		assertNotEquals(this.contact.getPermanentAddress(), this.contact.getLocalAddress());

		this.contact.setLocalAsPermanentAddress();

		assertEquals(this.contact.getPermanentAddress(), this.contact.getLocalAddress());
	}

	@Test
	public void canCompareTwoContacts() {

		ContactInfo compareContact = this.basicContact();

		assertTrue(this.contact.equals(compareContact));

	}

	@Test
	public void canCompareTwoContactsFalse() {

		ContactInfo compareContact = this.basicContact();
		compareContact.setPermanentAddress(null);

		assertFalse(this.contact.equals(compareContact));

	}

	@Test
	public void canCompareTwoContacts2() {

		assertTrue(this.contact.equals(this.contact));

	}

	@Test
	public void canCompareTwoContactsFalse2() {

		assertFalse(this.contact.equals(null));

	}

	@Test
	public void canCompareTwoContactsFalse3() {

		assertFalse(this.contact.equals(FakeDB.getAllPatrons()));
	}

	@Test
	public void correctPrint() {

		ContactInfo printContact = this.basicContact();

		String formattedContact = "Name: " + printContact.getFirstName() + " " + printContact.getLastName()
				+ "\nTelephone Number: " + printContact.getFormattedTelephoneNumber() + "\nLocal Address:\n"
				+ printContact.getLocalAddress().toString() + "\nPermanent Address:\n"
				+ printContact.getPermanentAddress().toString();

		assertEquals(this.contact.toString(), formattedContact);

	}

	private ContactInfo basicContact() {
		ContactInfo basicContactInfo = new ContactInfo();

		basicContactInfo.setFirstName("Ross");
		basicContactInfo.setLastName("Weinstein");
		basicContactInfo.setPhoneNumber("6121234567");

		Address address = new Address();
		address.setAddressLineOne("123 Main Street");
		address.setAddressLineTwo("Apt 12C");
		address.setCity("Minneapolis");
		address.setState("MN");
		address.setZipCode("55410");

		basicContactInfo.setLocalAddress(address);

		Address addressTwo = new Address();
		addressTwo.setAddressLineOne("876 East Street");
		addressTwo.setCity("St. Paul");
		addressTwo.setState("MN");
		addressTwo.setZipCode("55116");

		basicContactInfo.setPermanentAddress(addressTwo);
		return basicContactInfo;
	}
}
