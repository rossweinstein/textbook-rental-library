package textbookRentalLibraryTest.controllersTest;

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

public class DatabaseControllerTest {
	
	private List<Patron> patrons;
	private List<Copy> copies;
	private DatabaseController db;
	private UnshelvedHoldController unshelved;
	private DamageHoldController damage;

	@Before
	public void setUp() {
		this.patrons = FakeDB.getAllPatrons();
		this.copies = FakeDB.getAllCopies();
		this.db = new DatabaseController();
		this.unshelved = new UnshelvedHoldController();
		this.damage = new DamageHoldController();
	}

	@Test
	public void doesGetAllPatrons() {
		assertTrue(this.db.getAllPatronsInTRL().size() == 4);
	}

	@Test
	public void getsNoHoldsWhenThereAreNoHolds() {
		assertTrue(this.db.getAllPatronsWithHolds().isEmpty());
	}

	@Test
	public void addedTwoHolds() {

		Patron ross = this.patrons.get(1);
		Copy textbook = this.copies.get(0);

		ross.checkCopyOut(textbook);
		ross.checkCopyIn(textbook);

		this.damage.markingHold(ross, textbook, 10);
		this.unshelved.markingHold(ross, textbook, 5);
		
		assertTrue(this.db.getHoldTotal() == 2);
		
		ross.resolvedHold(ross.getAllHolds().get(0));
		ross.resolvedHold(ross.getAllHolds().get(0));
		textbook.setLastPersonToCheckOut(null);
	}

	@Test
	public void thereAreHoldsInGeneral() {

		Patron ross = this.patrons.get(1);
		Copy textbook = this.copies.get(0);

		ross.checkCopyOut(textbook);
		ross.checkCopyIn(textbook);

		this.damage.markingHold(ross, textbook, 10);

		assertFalse(this.db.getAllPatronsWithHolds().isEmpty());

		ross.resolvedHold(ross.getAllHolds().get(0));
		textbook.setLastPersonToCheckOut(null);
	}
	
	@Test
	public void patronDoesHaveHoldsOnRecord() {

		Patron ross = this.patrons.get(1);
		Copy textbook = this.copies.get(0);
		
		ross.checkCopyOut(textbook);
		ross.checkCopyIn(textbook);
		this.damage.markingHold(ross, textbook, 10);
		
		assertFalse(ross.hasNoHoldsOnRecord());
		
		ross.resolvedHold(ross.getAllHolds().get(0));
		textbook.setLastPersonToCheckOut(null);
	}

	@Test
	public void patronResolvesHolds() {
		
		Patron ross = this.patrons.get(1);
		Copy textbook = this.copies.get(0);
		Copy textbook2 = this.copies.get(1);
		
		ross.checkCopyOut(textbook);
		ross.checkCopyOut(textbook2);

		ross.checkCopyIn(textbook);
		ross.checkCopyIn(textbook2);
		
		this.unshelved.markingHold(ross, textbook, 40);
		this.damage.markingHold(ross, textbook2, 10);

		ross.resolvedHold(ross.getAllHolds().get(0));
		ross.resolvedHold(ross.getAllHolds().get(0));

		assertTrue(ross.copiesCurrentlyCheckedOut() == 0);
		assertTrue(ross.hasNoHoldsOnRecord());
		
		textbook.setLastPersonToCheckOut(null);
		textbook2.setLastPersonToCheckOut(null);
	}

	@Test
	public void patronCannotPlaceHoldTwice() {
		
		Patron ross = this.patrons.get(1);
		Copy textbook = this.copies.get(0);

		ross.checkCopyOut(textbook);
		ross.checkCopyIn(textbook);

		assertTrue(this.damage.markingHold(ross, textbook, 10));
		assertFalse(this.damage.markingHold(ross, textbook, 10));
		ross.resolvedHold(ross.getAllHolds().get(0));
		textbook.setLastPersonToCheckOut(null);
	}

}
