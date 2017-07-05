package modelTest.patronTest.patronInfoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.patron.patronInfo.Address;

public class AddressTest {

	
	private Address address;
	
	@Before
	public void createNewAddressClass() {
		this.address = new Address();
	}
	
	@Test
	public void setAddressLineOne() {
		this.address.setAddressLineOne("123 Main St.");
	}
	
	@Test
	public void setAddressLineTwo() {
		this.address.setAddressLineTwo("Apt. 18B");
	}
	
	@Test
	public void setState() {
		this.address.setState("MN");
		assertTrue(this.address.getState().equals("MN"));
	}
	
	@Test
	public void setInvalidState() {
		assertFalse(this.address.validState("123"));
		assertTrue(this.address.getState().equals(""));
	}
	
	@Test
	public void cannotSetFakeStateMustBeInUSA() {
		assertFalse(this.address.validState("MX"));
		assertTrue(this.address.getState().equals(""));
	}
	
	@Test
	public void setCity() {
		this.address.setCity("Minneapolis");
		assertTrue(this.address.getCity().equals("Minneapolis"));
	}
	
	@Test
	public void cannotSetInvalidCity() {
		assertFalse(this.address.validCity("Min3ahde3"));
		assertTrue(this.address.getCity().equals(""));
	}
	
	@Test
	public void setZipCode() {
		this.address.setZipCode("12345");
		assertTrue(this.address.getZipCode().equals("12345"));
	}
	
	@Test
	public void cannotSetInvalidZipCode() {
		assertFalse(this.address.validZipCode("123"));
		assertTrue(this.address.getZipCode().equals(""));
	}
	
	@Test
	public void getFormattedAddress() {
		this.address.getFormattedAddress();
	}

}
