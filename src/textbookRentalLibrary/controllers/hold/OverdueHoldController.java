package textbookRentalLibrary.controllers.hold;

public class OverdueHoldController extends PlaceHoldController {
	
	public OverdueHoldController() {
		super();
	}

	@Override
	public void displayHolds() {
		this.displayPatronsWith(" with overdue holds ", super.getManage().getAllPatronsWithOverdueHolds());
		
	}

	@Override
	public boolean markHold() {
		return super.confirmHoldForType("Overdue") ? this.markOverdueHolds() : false;
	}
	
	private boolean markOverdueHolds() {
		int fineAmount = super.enterFineAmout();
		return super.getManage().markOverdueHolds(fineAmount);
	}
}
