package textbookRentalLibraryTest.controllersTest.checkInAndOutCopyTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import database.FakeDB;
import textbookRentalLibrary.controllers.checkInAndOutCopy.SessionController;

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
