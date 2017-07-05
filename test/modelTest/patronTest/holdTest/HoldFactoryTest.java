package modelTest.patronTest.holdTest;

import static org.junit.Assert.*;

import org.junit.Test;

import database.FakeDB;
import model.copy.Copy;
import model.patron.hold.CopyDamagedHold;
import model.patron.hold.Hold;
import model.patron.hold.HoldFactory;
import model.patron.hold.HoldType;
import model.patron.hold.LostHold;
import model.patron.hold.OverdueHold;
import model.patron.hold.UnshelvedCopyHold;

public class HoldFactoryTest {

	@Test
	public void createsOverdueHold() {
		
		Copy overdueCopy = FakeDB.getCopy("C1");
		
		Hold overdue = HoldFactory.createHold(HoldType.OVERDUE, 10, overdueCopy);
		
		assertTrue(overdue instanceof OverdueHold);
		assertTrue(overdue.getFineAmount() == 10);
		assertTrue(overdue.getHoldCopy().equals(overdueCopy));
	}
	
	@Test
	public void createsUnshelvedHold() {
		
		Copy unshelvedCopy = FakeDB.getCopy("C2");
		
		Hold unshelved = HoldFactory.createHold(HoldType.UNSHELEVED, 10, unshelvedCopy);
		
		assertTrue(unshelved instanceof UnshelvedCopyHold);
		assertTrue(unshelved.getFineAmount() == 10);
		assertTrue(unshelved.getHoldCopy().equals(unshelvedCopy));
	}
	
	@Test
	public void createsDamagHold() {
		
		Copy damagedCopy = FakeDB.getCopy("C3");
		
		Hold damage = HoldFactory.createHold(HoldType.DAMAGED, 10, damagedCopy);
		
		assertTrue(damage instanceof CopyDamagedHold);
		assertTrue(damage.getFineAmount() == 10);
		assertTrue(damage.getHoldCopy().equals(damagedCopy));
	}
	
	@Test
	public void createsLostHold() {
		
		Copy lostCopy = FakeDB.getCopy("C4");
		
		Hold lost = HoldFactory.createHold(HoldType.LOST, 10, lostCopy);
		
		assertTrue(lost instanceof LostHold);
		assertTrue(lost.getFineAmount() == 10);
		assertTrue(lost.getHoldCopy().equals(lostCopy));
	}

}
