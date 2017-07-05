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
	}
	
	@Test
	public void setInvalidState() {
		assertFalse(this.address.validState("123"));
	}
	
	@Test
	public void cannotSetFakeStateMustBeInUSA() {
		assertFalse(this.address.validState("MX"));
	}
	
	@Test
	public void setCity() {
		this.address.setCity("Minneapolis");
	}
	
	@Test
	public void cannotSetInvalidCity() {
		assertFalse(this.address.validCity("Min3ahde3"));
	}

}
