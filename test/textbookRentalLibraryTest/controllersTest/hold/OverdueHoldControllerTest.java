package textbookRentalLibraryTest.controllersTest.hold;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import database.FakeDB;
import model.copy.Copy;
import model.patron.Patron;
import textbookRentalLibrary.controllers.DatabaseController;
import textbookRentalLibrary.controllers.hold.OverdueHoldController;

public class OverdueHoldControllerTest {
	
	private List<Patron> patrons;
	private List<Copy> copies;
	private DatabaseController db;
	private OverdueHoldController overdue;

	@Before
	public void setUp() {
		this.patrons = FakeDB.getAllPatrons();
		this.copies = FakeDB.getAllCopies();
		this.db = new DatabaseController();
		this.overdue = new OverdueHoldController();
	}

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

		assertTrue(this.db.getAllPatronsWithUnreturnedTextBooks().size() == 3);
		
		ross.checkCopyIn(textbook);
		mowlid.checkCopyIn(textbook2);
		neera.checkCopyIn(textbook3);
	}
	
	@Test
	public void seeHowManyPatronsHaveOverdueHolds() {

		Patron ross = this.patrons.get(1);
		Copy textbook = this.copies.get(0);
		ross.checkCopyOut(textbook);
		textbook.setDueDate(LocalDateTime.now().minusDays(1));

		
		Patron mowlid = this.patrons.get(2);
		Copy textbook2 = this.copies.get(1);
		mowlid.checkCopyOut(textbook2);
		textbook2.setDueDate(LocalDateTime.now().minusDays(1));

		
		Patron neera = this.patrons.get(3);
		Copy textbook3 = this.copies.get(2);
		neera.checkCopyOut(textbook3);
		textbook3.setDueDate(LocalDateTime.now().minusDays(1));

		assertTrue(this.overdue.successfulHoldMark(10));
		assertTrue(this.db.getAllPatronsWithOverdueHolds().size() == 3);
		assertTrue(this.db.getAllPatronsWithHolds().size() == 3);
		
		ross.resolvedHold(ross.getAllHolds().get(0));
		textbook.setLastPersonToCheckOut(null);
		
		mowlid.resolvedHold(mowlid.getAllHolds().get(0));
		textbook2.setLastPersonToCheckOut(null);
		
		neera.resolvedHold(neera.getAllHolds().get(0));
		textbook3.setLastPersonToCheckOut(null);
	}
	
	@Test
	public void onlyStudentsGetOverdueHolds() {
		
		Patron eric = this.patrons.get(0);
		Copy textbook = this.copies.get(0);
		eric.checkCopyOut(textbook);
		textbook.setDueDate(LocalDateTime.now().minusDays(10));
		
		Patron ross = this.patrons.get(1);
		Copy textbook2 = this.copies.get(1);
		ross.checkCopyOut(textbook2);
		textbook2.setDueDate(LocalDateTime.now().minusDays(10));
		
		assertTrue(this.overdue.successfulHoldMark(10));
		assertTrue(this.db.getAllPatronsWithOverdueHolds().size() == 1);
		assertTrue(this.db.getAllPatronsWithHolds().size() == 1);
		assertTrue(this.db.getAllPatronsWithUnreturnedTextBooks().size() == 2);
		
		eric.checkCopyIn(textbook);
		textbook.setLastPersonToCheckOut(null);
		
		ross.checkCopyIn(textbook2);
		ross.resolvedHold(ross.getAllHolds().get(0));
		textbook2.setLastPersonToCheckOut(null);
	}
}
