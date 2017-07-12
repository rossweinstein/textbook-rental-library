package textbookRentalLibraryTest.controllersTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import database.FakeDB;
import model.patron.Patron;
import textbookRentalLibrary.controllers.ManagerController;
import textbookRentalLibrary.controllers.hold.MiscHoldController;

public class ManagerControllerTest {
	
	
	private List<Patron> patrons;
	private ManagerController manage;

	@Before
	public void setUp() {
		this.patrons = FakeDB.getAllPatrons();
		this.manage = new ManagerController();
	}
	

	@Test
	public void canGenerateHoldNotices() {
		Patron ross = this.patrons.get(1);

		MiscHoldController misc = new MiscHoldController();
		misc.placeLostAndFoundHold(ross, "backpack", "History shelves");
		
		assertTrue(this.manage.canGenerateHoldNotices());

		ross.resolvedHold(ross.getAllHolds().get(0));
	}

	@Test
	public void cannotGenerateHoldNotices() {
		assertFalse(this.manage.canGenerateHoldNotices());
	}

}
