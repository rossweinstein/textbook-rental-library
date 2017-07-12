package textbookRentalLibraryTest.controllersTest.hold;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import database.FakeDB;
import model.patron.Patron;
import textbookRentalLibrary.controllers.DatabaseController;
import textbookRentalLibrary.controllers.hold.MiscHoldController;

public class MiscHoldControllerTest {
	
	private List<Patron> patrons;
	private DatabaseController db;
	private MiscHoldController misc;

	@Before
	public void setUp() {
		this.patrons = FakeDB.getAllPatrons();
		this.db = new DatabaseController();
		this.misc = new MiscHoldController();
	}

	@Test
	public void markMiscHold() {

		Patron ross = this.patrons.get(1);

		assertTrue(this.misc.placeLostAndFoundHold(ross, "backpack", "History shelves"));

		ross.resolvedHold(ross.getAllHolds().get(0));
	}
	
	@Test
	public void returnsCorrectNumberOfMiscHolds() {
		
		Patron ross = this.patrons.get(1);
		this.misc.placeLostAndFoundHold(ross, "backpack", "History shelves");

		Patron mowlid = this.patrons.get(2);
		this.misc.placeLostAndFoundHold(mowlid, "laptop", "Fiction shelves");
		
		Patron neera = this.patrons.get(3);
		this.misc.placeLostAndFoundHold(neera, "phone", "Computer Lab");

		assertTrue(this.db.getAllPatronsWithMiscHolds().size() == 3);
		assertTrue(this.db.getAllPatronsWithHolds().size() == 3);

		ross.resolvedHold(ross.getAllHolds().get(0));
		mowlid.resolvedHold(mowlid.getAllHolds().get(0));
		neera.resolvedHold(neera.getAllHolds().get(0));
	}

}
