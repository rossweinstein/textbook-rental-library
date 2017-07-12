package textbookRentalLibraryTest.controllersTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import database.FakeDB;
import model.copy.Copy;
import textbookRentalLibrary.controllers.BuyBookController;

public class BuyBookControllerTest {
	
	private BuyBookController buyBook;
	
	@Before
	public void setUp() {
		this.buyBook = new BuyBookController();
	}

	@Test
	public void bookRemovedFromDB() {
		int sizeOfDB = FakeDB.getAllCopies().size();
		Copy theCopy = FakeDB.getCopy("C1");
		this.buyBook.removeBookFromDB(theCopy);
		assertTrue(FakeDB.getAllCopies().size() == sizeOfDB -1);
		FakeDB.getCopiesDB().put("C1", theCopy);
	}

}
