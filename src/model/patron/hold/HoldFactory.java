package model.patron.hold;

import model.copy.Copy;

public class HoldFactory {
	
	public static Hold createHold(HoldType type, int fineAmount, Copy copy) {
		
		Hold createdHold = null;

		if (type == HoldType.OVERDUE) {
			createdHold = new OverdueHold(fineAmount, copy);
			
		} else if (type == HoldType.DAMAGED) {
			createdHold = new CopyDamagedHold(fineAmount, copy);

		} else if (type == HoldType.LOST) {
			createdHold = new LostHold(fineAmount, copy);
			
		} else {
			createdHold = new UnshelvedCopyHold(fineAmount, copy);
		}

		return createdHold;
	}
}
