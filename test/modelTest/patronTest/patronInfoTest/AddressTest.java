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

}
