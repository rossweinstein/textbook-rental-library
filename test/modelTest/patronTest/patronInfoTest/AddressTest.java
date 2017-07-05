package modelTest.patronTest.patronInfoTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import database.FakeDB;
import model.patron.patronInfo.Address;

public class AddressTest {

	private Address address;
	private Address addressTwo;
	
	@Before
	public void createNewAddressClass() {
		this.address = new Address();
		this.address.setAddressLineOne("123 Main Street");
		this.address.setAddressLineTwo("Apt 12C");
		this.address.setCity("Minneapolis");
		this.address.setState("MN");
		this.address.setZipCode("55410");
		
		this.addressTwo = new Address();
		this.addressTwo.setAddressLineOne("876 East Street");
		this.addressTwo.setCity("St. Paul");
		this.addressTwo.setState("MN");
		this.addressTwo.setZipCode("55116");
	}
	
	@Test
	public void setAddressLineOne() {
		this.address.setAddressLineOne("123 Main St.");
		assertTrue(this.address.getAddressLineOne().equals("123 Main St."));
	}
	
	@Test
	public void setAddressLineTwo() {
		this.address.setAddressLineTwo("Apt. 18B");
		assertTrue(this.address.getAddressLineTwo().equals("Apt. 18B"));
	}
	
	@Test
	public void setState() {
		this.address.setState("MN");
		assertTrue(this.address.getState().equals("MN"));
	}
	
	@Test
	public void setInvalidState() {
		this.address.setState("123");
		assertTrue(this.address.getState().equals("MN"));
	}
	
	@Test
	public void cannotSetFakeStateMustBeInUSA() {
		this.address.setState("MX");
		assertTrue(this.address.getState().equals("MN"));
	}
	
	@Test
	public void setCity() {
		this.address.setCity("Minneapolis");
		assertTrue(this.address.getCity().equals("Minneapolis"));
	}
	
	@Test
	public void cannotSetInvalidCity() {
		this.address.setCity("Min3ahde3");
		assertTrue(this.address.getCity().equals("Minneapolis"));
	}
	
	@Test
	public void setZipCode() {
		this.address.setZipCode("12345");
		assertTrue(this.address.getZipCode().equals("12345"));
	}
	
	@Test
	public void cannotSetInvalidZipCode() {
		this.address.setZipCode("123");
		assertTrue(this.address.getZipCode().equals("55410"));
	}
	
	@Test
	public void correctlyFormattedAddress() {
		String compareAddress = "123 Main Street" + "\nApt 12C" + "\nMinneapolis, MN 55410";
		assertEquals(compareAddress, this.address.toString());
	}
	
	@Test
	public void correctlyFormattedAddress2() {
		String compareAddress = "876 East Street" + "\nSt. Paul, MN 55116";
		assertEquals(compareAddress, this.addressTwo.toString());
	}
	
	@Test
	public void correctCompare() {
		
		Address compareAddress = new Address();
		compareAddress.setAddressLineOne("123 Main Street");
		compareAddress.setAddressLineTwo("Apt 12C");
		compareAddress.setCity("Minneapolis");
		compareAddress.setState("MN");
		compareAddress.setZipCode("55410");
		
		assertTrue(this.address.equals(compareAddress));
	}
	
	@Test
	public void correctCompare2() {
		
		assertTrue(this.address.equals(this.address));
	}
	
	@Test
	public void falseCompare() {
		assertFalse(this.address.equals(addressTwo));
	}
	
	@Test
	public void falseCompare2() {
		assertFalse(this.address.equals(null));
	}
	
	@Test
	public void falseCompare3() {
		assertFalse(this.address.equals(FakeDB.getCopy("C1")));
	}
}
