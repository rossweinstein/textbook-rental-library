package textbookRentalLibraryTest.controllersTest.hold;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import database.FakeDB;
import model.copy.Copy;
import model.patron.Patron;
import textbookRentalLibrary.controllers.DatabaseController;
import textbookRentalLibrary.controllers.hold.DamageHoldController;
import textbookRentalLibrary.controllers.hold.UnshelvedHoldController;

public class UnshelvedHoldControllerTest {
	
	private List<Patron> patrons;
	private List<Copy> copies;
	private DatabaseController db;
	private UnshelvedHoldController unshelved;

	@Before
	public void setUp() {
		this.patrons = FakeDB.getAllPatrons();
		this.copies = FakeDB.getAllCopies();
		this.db = new DatabaseController();
		this.unshelved = new UnshelvedHoldController();
	}


	@Test
	public void cannotMarkUnshelvedHold() {

		Patron ross = this.patrons.get(1);
		Copy textbook = this.copies.get(0);

		ross.checkCopyOut(textbook);

		assertFalse(this.unshelved.markingHold(ross, textbook, 10));

		ross.checkCopyIn(textbook);
		textbook.setLastPersonToCheckOut(null);
	}

	@Test
	public void canMarkUnshelvedHold() {

		Patron ross = this.patrons.get(1);
		Copy textbook = this.copies.get(0);

		ross.checkCopyOut(textbook);
		ross.checkCopyIn(textbook);

		assertTrue(this.unshelved.markingHold(ross, textbook, 10));

		ross.resolvedHold(ross.getAllHolds().get(0));
		textbook.setLastPersonToCheckOut(null);

	}
	
	@Test
	public void returnsCorrectNumberOfUnshelvedHolds() {
		
		Patron ross = this.patrons.get(1);
		Copy textbook = this.copies.get(0);

		ross.checkCopyOut(textbook);
		ross.checkCopyIn(textbook);
		
		Patron mowlid = this.patrons.get(2);
		Copy textbook2 = this.copies.get(1);

		mowlid.checkCopyOut(textbook2);
		mowlid.checkCopyIn(textbook2);
		
		Patron neera = this.patrons.get(3);
		Copy textbook3 = this.copies.get(2);

		neera.checkCopyOut(textbook3);
		neera.checkCopyIn(textbook3);

		DamageHoldController damage = new DamageHoldController();
		damage.markingHold(ross, textbook, 10);
		damage.markingHold(mowlid, textbook2, 10);
		this.unshelved.markingHold(neera, textbook3, 10);
		
		assertTrue(this.db.getAllPatronsWithUnshelvedHolds().size() == 1);
		assertTrue(this.db.getAllPatronsWithHolds().size() == 3);

		ross.resolvedHold(ross.getAllHolds().get(0));
		textbook.setLastPersonToCheckOut(null);
		
		mowlid.resolvedHold(mowlid.getAllHolds().get(0));
		textbook2.setLastPersonToCheckOut(null);
		
		neera.resolvedHold(neera.getAllHolds().get(0));
		textbook3.setLastPersonToCheckOut(null);	
	}
}
