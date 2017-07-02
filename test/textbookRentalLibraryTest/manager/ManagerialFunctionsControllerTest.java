package textbookRentalLibraryTest.manager;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import database.FakeDB;
import model.patron.Patron;
import textbookRentalLibrary.controllers.ManagerialFunctionsController;

public class ManagerialFunctionsControllerTest {
	
	private ManagerialFunctionsController manage;
	
	@Before
	public void SetUp() {
		this.manage = new ManagerialFunctionsController();
	}

	@Test
	public void findPatronsWithUnreturnedCopies() {
		
		List<Patron> thePatrons = FakeDB.getAllPatrons();
		
		thePatrons.get(0).checkCopyOut(FakeDB.getCopy("C1"));
		
		assertTrue(this.manage.findPatronsWithUnreturnedCopies(thePatrons).size() == 1);
	}
}
