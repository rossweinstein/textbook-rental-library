package textbookRentalLibraryTest.checkInAndOut;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fakeDatabase.FakeDB;
import textbookRentalLibrary.checkInAndOut.SessionVerification;

public class SessionVerificationTest {
	
	private SessionVerification verify;
	
	@Before
	public void SetUp() {
		this.verify = new SessionVerification();
	}
	
	@Test
	public void validID() {
		assertTrue(this.verify.patronIDIsValid(FakeDB.getPatron("P1")));
	}
	
	@Test
	public void notValidID() {
		assertFalse(this.verify.patronIDIsValid(null));
	}

}
