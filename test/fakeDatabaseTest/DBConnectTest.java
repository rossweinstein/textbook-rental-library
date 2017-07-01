package fakeDatabaseTest;

import org.junit.Before;
import org.junit.Test;

import fakeDatabase.DBConnect;

public class DBConnectTest {
	
	private DBConnect db;
	
	@Before
	public void SetUp() {
		this.db = new DBConnect();
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
