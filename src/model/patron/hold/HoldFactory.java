package model.patron.hold;

import model.copy.Copy;

/**
 * This factory class aids in the creation of Overdue, Damaged, Lost, and
 * Unshelved holds.
 * 
 * @author Ross Weinstein
 * 
 */

public class HoldFactory {

	public static Hold createHold(HoldType type, int fineAmount, Copy copy) {

		Hold createdHold = null;

		if (type == HoldType.OVERDUE) {
			createdHold = new OverdueHold(fineAmount, copy);

		} else if (type == HoldType.DAMAGED) {
			createdHold = new DamagedHold(fineAmount, copy);

		} else if (type == HoldType.LOST) {
			createdHold = new LostHold(fineAmount, copy);

		} else {
			createdHold = new UnshelvedHold(fineAmount, copy);
		}

		return createdHold;
	}
}
