package textbookRentalLibraryTest.checkInAndOut;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fakeDatabase.FakeDB;
import textbookRentalLibrary.checkInAndOut.SessionController;

public class SessionVerificationTest {
	
	private SessionController verify;
	
	@Before
	public void SetUp() {
		this.verify = new SessionController();
	}
	
	@Test
	public void validID() {
		assertTrue(this.verify.patronFoundInDB(FakeDB.getPatron("P1")));
	}
	
	@Test
	public void notValidID() {
		assertFalse(this.verify.patronFoundInDB(null));
	}

}
