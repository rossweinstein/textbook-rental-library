package model.patron.hold;

import model.copy.Copy;

public class HoldFactory {
	
	public static Hold createHold(HoldType type, int fineAmount, Copy copy) {
		
		Hold createdHold = null;
		
		if (type == HoldType.OVERDUE) {
			createdHold = new OverdueHold(fineAmount, copy);
		} else if (type == HoldType.DAMAGED) {
			createdHold = new CopyDamagedHold(fineAmount, copy);
		} else if (type == HoldType.UNSHELEVED) {
			createdHold = new UnshelevedCopyHold(fineAmount, copy);
		} 
		
		return createdHold;
	}
}
