package fakeDatabaseTest;

import org.junit.Before;
import org.junit.Test;

import textbookRentalLibrary.controllers.DatabaseController;

public class DBConnectTest {
	
	private DatabaseController db;
	
	@Before
	public void SetUp() {
		this.db = new DatabaseController();
	}
	
	@Test
	public void gotAllThePatrons() {
		assert(this.db.getAllPatrons().size() == 2);
	}
	
	@Test
	public void gotAllTheCopies() {
		assert(this.db.getAllCopies().size() == 2);
	}
}
