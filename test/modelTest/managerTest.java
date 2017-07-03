package modelTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import database.FakeDB;
import model.Manager;
import model.copy.Copy;
import model.patron.Patron;

public class managerTest {

	private Manager manage;
	private List<Patron> patrons;
	private List<Copy> copies;

	@Before
	public void setUp() {
		this.manage = new Manager();
		this.patrons = FakeDB.getAllPatrons();
		this.copies = FakeDB.getAllCopies();
	}
	
	/************** GENERAL HOLDS TEST ******************************************************************/

	@Test
	public void doesGetAllPatrons() {
		assertTrue(this.manage.getAllPatronsInTRL().size() == 4);
	}

	@Test
	public void getsNoHoldsWhenThereAreNoHolds() {
		assertTrue(this.manage.getAllPatronsWithHolds().isEmpty());
	}

	@Test
	public void addedTwoHolds() {

		Patron ross = this.patrons.get(1);
		Copy textbook = this.copies.get(0);

		ross.checkCopyOut(textbook);
		ross.checkCopyIn(textbook);

		this.manage.markDamageHold(ross, textbook, 10);
		this.manage.markUnshelevedHold(ross, textbook, 5);
		
		assertTrue(this.manage.getHoldTotal() == 2);
		
		ross.resolvedHold(ross.getAllHolds().get(0));
		ross.resolvedHold(ross.getAllHolds().get(0));
	}

	@Test
	public void thereAreHoldsInGeneral() {

		Patron ross = this.patrons.get(1);
		Copy textbook = this.copies.get(0);

		ross.checkCopyOut(textbook);
		ross.checkCopyIn(textbook);

		this.manage.markDamageHold(ross, textbook, 10);

		assertFalse(this.manage.getAllPatronsWithHolds().isEmpty());

		ross.resolvedHold(ross.getAllHolds().get(0));
		textbook.setLastPersonToCheckOut(null);
	}
	
	/************** OVERDUE HOLDS TEST ******************************************************************/

	@Test
	public void seeHowManyPatronsHaveUnreturnedBooks() {

		Patron ross = this.patrons.get(1);
		Copy textbook = this.copies.get(0);

		ross.checkCopyOut(textbook);
		
		Patron mowlid = this.patrons.get(2);
		Copy textbook2 = this.copies.get(1);

		mowlid.checkCopyOut(textbook2);
		
		Patron neera = this.patrons.get(3);
		Copy textbook3 = this.copies.get(2);

		neera.checkCopyOut(textbook3);

		assertTrue(this.manage.getAllPatronsWithUnreturnedTextBooks().size() == 3);
	}
	
	@Test
	public void seeHowManyPatronsHaveOverdueHolds() {

		Patron ross = this.patrons.get(1);
		Copy textbook = this.copies.get(0);

		ross.checkCopyOut(textbook);
		
		Patron mowlid = this.patrons.get(2);
		Copy textbook2 = this.copies.get(1);

		mowlid.checkCopyOut(textbook2);
		
		Patron neera = this.patrons.get(3);
		Copy textbook3 = this.copies.get(2);

		neera.checkCopyOut(textbook3);

		assertTrue(this.manage.markOverdueHolds(10));
		assertTrue(this.manage.getAllPatronsWithOverdueHolds().size() == 3);
		assertTrue(this.manage.getAllPatronsWithHolds().size() == 3);
		
		ross.resolvedHold(ross.getAllHolds().get(0));
		textbook.setLastPersonToCheckOut(null);
		
		mowlid.resolvedHold(mowlid.getAllHolds().get(0));
		textbook2.setLastPersonToCheckOut(null);
		
		neera.resolvedHold(neera.getAllHolds().get(0));
		textbook3.setLastPersonToCheckOut(null);
	}
	
	
	/************** DAMAGE HOLDS TEST ******************************************************************/

	@Test
	public void cannotMarkDamageHold() {

		Patron ross = this.patrons.get(1);
		Copy textbook = this.copies.get(0);

		ross.checkCopyOut(textbook);

		assertFalse(this.manage.markDamageHold(ross, textbook, 10));

		ross.checkCopyIn(textbook);
		textbook.setLastPersonToCheckOut(null);
	}

	@Test
	public void canMarkDamageHold() {

		Patron ross = this.patrons.get(1);
		Copy textbook = this.copies.get(0);

		ross.checkCopyOut(textbook);
		ross.checkCopyIn(textbook);

		assertTrue(this.manage.markDamageHold(ross, textbook, 10));

		ross.resolvedHold(ross.getAllHolds().get(0));
		textbook.setLastPersonToCheckOut(null);

	}
	
	@Test
	public void returnsCorrectNumberOfDamageHolds() {
		
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

		this.manage.markDamageHold(ross, textbook, 10);
		this.manage.markDamageHold(mowlid, textbook2, 10);
		this.manage.markUnshelevedHold(neera, textbook3, 10);
		
		assertTrue(this.manage.getAllPatronsWithDamageHolds().size() == 2);
		assertTrue(this.manage.getAllPatronsWithHolds().size() == 3);

		ross.resolvedHold(ross.getAllHolds().get(0));
		textbook.setLastPersonToCheckOut(null);
		
		mowlid.resolvedHold(mowlid.getAllHolds().get(0));
		textbook2.setLastPersonToCheckOut(null);
		
		neera.resolvedHold(neera.getAllHolds().get(0));
		textbook3.setLastPersonToCheckOut(null);
		
	}
	
	/************** UNSHELVED HOLDS TEST ******************************************************************/


	@Test
	public void cannotMarkUnshelvedHold() {

		Patron ross = this.patrons.get(1);
		Copy textbook = this.copies.get(0);

		ross.checkCopyOut(textbook);

		assertFalse(this.manage.markUnshelevedHold(ross, textbook, 10));

		ross.checkCopyIn(textbook);
		textbook.setLastPersonToCheckOut(null);
	}

	@Test
	public void canMarkUnshelvedHold() {

		Patron ross = this.patrons.get(1);
		Copy textbook = this.copies.get(0);

		ross.checkCopyOut(textbook);
		ross.checkCopyIn(textbook);

		assertTrue(this.manage.markUnshelevedHold(ross, textbook, 10));

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

		this.manage.markDamageHold(ross, textbook, 10);
		this.manage.markDamageHold(mowlid, textbook2, 10);
		this.manage.markUnshelevedHold(neera, textbook3, 10);
		
		assertTrue(this.manage.getAllPatronsWithUnshelvedHolds().size() == 1);
		assertTrue(this.manage.getAllPatronsWithHolds().size() == 3);

		ross.resolvedHold(ross.getAllHolds().get(0));
		textbook.setLastPersonToCheckOut(null);
		
		mowlid.resolvedHold(mowlid.getAllHolds().get(0));
		textbook2.setLastPersonToCheckOut(null);
		
		neera.resolvedHold(neera.getAllHolds().get(0));
		textbook3.setLastPersonToCheckOut(null);
		
	}
	
	/************** MISC HOLDS TEST ******************************************************************/

	@Test
	public void markMiscHold() {

		Patron ross = this.patrons.get(1);

		assertTrue(this.manage.markMiscHold(ross, "backpack", "History shelves"));

		ross.resolvedHold(ross.getAllHolds().get(0));
	}
	
	@Test
	public void returnsCorrectNumberOfMiscHolds() {
		
		Patron ross = this.patrons.get(1);
		this.manage.markMiscHold(ross, "backpack", "History shelves");

		Patron mowlid = this.patrons.get(2);
		this.manage.markMiscHold(mowlid, "laptop", "Fiction shelves");
		
		Patron neera = this.patrons.get(3);
		this.manage.markMiscHold(neera, "phone", "Computer Lab");

		assertTrue(this.manage.getAllPatronsWithMiscHolds().size() == 3);
		assertTrue(this.manage.getAllPatronsWithHolds().size() == 3);

		ross.resolvedHold(ross.getAllHolds().get(0));
		mowlid.resolvedHold(mowlid.getAllHolds().get(0));
		neera.resolvedHold(neera.getAllHolds().get(0));
	}
	
	/************** GENERATE HOLD NOTICES TEST ******************************************************************/

	@Test
	public void canGenerateHoldNotices() {
		Patron ross = this.patrons.get(1);

		this.manage.markMiscHold(ross, "backpack", "History shelves");
		
		assertTrue(this.manage.canGenerateHoldNotices());

		ross.resolvedHold(ross.getAllHolds().get(0));
	}

	@Test
	public void cannotGenerateHoldNotices() {
		assertFalse(this.manage.canGenerateHoldNotices());
	}
	
	
}
