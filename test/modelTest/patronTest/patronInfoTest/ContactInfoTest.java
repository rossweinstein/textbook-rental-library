package modelTest.patronTest.patronInfoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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

}
