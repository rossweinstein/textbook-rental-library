package modelTest.patronTest.holdTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import database.FakeDB;
import model.patron.hold.Hold;
import model.patron.hold.HoldFactory;
import model.patron.hold.HoldType;
import model.patron.hold.MiscHold;

public class HoldTest {

	private List<Hold> patronHolds;

	@Before
	public void setUp() {
		this.patronHolds = this.createHolds();
		
	}

	private List<Hold> createHolds() {
		List<Hold>theHolds = new ArrayList<>();
		theHolds.add(HoldFactory.createHold(HoldType.DAMAGED, 10, FakeDB.getCopy("C1")));
		theHolds.add(HoldFactory.createHold(HoldType.DAMAGED, 10, FakeDB.getCopy("C2")));
		theHolds.add(HoldFactory.createHold(HoldType.OVERDUE, 10, FakeDB.getCopy("C3")));
		theHolds.add(HoldFactory.createHold(HoldType.OVERDUE, 10, FakeDB.getCopy("C4")));
		theHolds.add(HoldFactory.createHold(HoldType.UNSHELEVED, 10, FakeDB.getCopy("C5")));
		theHolds.add(HoldFactory.createHold(HoldType.UNSHELEVED, 10, FakeDB.getCopy("C6")));
		theHolds.add(new MiscHold("backpack", "history"));
		theHolds.add(new MiscHold("notebook", "computer lab"));
		theHolds.add(HoldFactory.createHold(HoldType.LOST, 10, FakeDB.getCopy("C7")));
		theHolds.add(HoldFactory.createHold(HoldType.LOST, 10, FakeDB.getCopy("C8")));
		return theHolds;
	}

	@Test
	public void overdueTest1() {
		assertFalse(this.patronHolds.get(3).equals(null));
	}
	
	@Test
	public void overdueTest2() {
		assertTrue(this.patronHolds.get(3).equals(this.patronHolds.get(3)));
	}
	
	@Test
	public void overdueTest3() {
		assertFalse(this.patronHolds.get(3).equals(this.patronHolds.get(0)));
	}
	
	@Test
	public void overdueTest4() {
	
		Hold overdue = HoldFactory.createHold(HoldType.OVERDUE, 10, FakeDB.getCopy("C3"));
		
		assertTrue(this.patronHolds.get(2).equals(overdue));
	}
	
	@Test
	public void damageTest1() {
		assertFalse(this.patronHolds.get(0).equals(null));
	}
	
	@Test
	public void damageTest2() {
		assertTrue(this.patronHolds.get(0).equals(this.patronHolds.get(0)));
	}
	
	@Test
	public void damageTest3() {
		assertFalse(this.patronHolds.get(0).equals(this.patronHolds.get(7)));
	}
	
	@Test
	public void damageTest4() {
	
		Hold damage = HoldFactory.createHold(HoldType.DAMAGED, 10, FakeDB.getCopy("C2"));
		
		assertTrue(this.patronHolds.get(1).equals(damage));
	}
	
	@Test
	public void miscTest1() {
		assertFalse(this.patronHolds.get(7).equals(null));
	}
	
	@Test
	public void miscTest2() {
		assertTrue(this.patronHolds.get(7).equals(this.patronHolds.get(7)));
	}
	
	@Test
	public void micsTest3() {
		assertFalse(this.patronHolds.get(7).equals(this.patronHolds.get(4)));
	}
	
	@Test
	public void miscTest4() {
	
		MiscHold misc = new MiscHold("backpack", "history");
		
		assertTrue(this.patronHolds.get(6).equals(misc));
	}
	
	@Test
	public void unshelvedTest1() {
		assertFalse(this.patronHolds.get(4).equals(null));
	}
	
	@Test
	public void unshelvedTest2() {
		assertTrue(this.patronHolds.get(4).equals(this.patronHolds.get(4)));
	}
	
	@Test
	public void unshelvedTest3() {
		assertFalse(this.patronHolds.get(4).equals(this.patronHolds.get(1)));
	}
	
	@Test
	public void unshelvedTest4() {
	
		Hold misc = HoldFactory.createHold(HoldType.UNSHELEVED, 10, FakeDB.getCopy("C6"));
		
		assertTrue(this.patronHolds.get(5).equals(misc));
	}
	
	@Test
	public void lostTest1() {
		assertFalse(this.patronHolds.get(8).equals(null));
	}
	
	@Test
	public void lostTest2() {
		assertTrue(this.patronHolds.get(8).equals(this.patronHolds.get(8)));
	}
	
	@Test
	public void lostTest3() {
		assertFalse(this.patronHolds.get(8).equals(this.patronHolds.get(1)));
	}
	
	@Test
	public void lostTest4() {
	
		Hold lost = HoldFactory.createHold(HoldType.LOST, 10, FakeDB.getCopy("C8"));
		
		assertTrue(this.patronHolds.get(9).equals(lost));
	}


}