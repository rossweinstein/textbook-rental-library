package textbookRentalLibrary.controllers.hold;

import java.util.List;
import java.util.stream.Collectors;

import model.patron.Patron;
import model.patron.hold.Hold;
import textbookRentalLibrary.menus.MenuBuilder;

/**
 * This controller is responsible for resolving previously placed holds.
 * 
 * @author Ross Weinstein
 *
 */

public class ResolveHoldController extends HoldController {

	public void resolvePatronHold() {

		Patron offendingPatron = super.queryDB().locatePatronInDB();

		boolean resolvingHolds = true;
		while (resolvingHolds) {

			resolvingHolds = patronFoundWithHolds(offendingPatron) ? attemptToReslveHold(offendingPatron)
					: printErrorMessage();
		}
	}
	
	/***** RESOLVE HOLDS HELPER METHODS *******************************/

	private boolean patronFoundWithHolds(Patron offendingPatron) {
		return offendingPatron != null && !offendingPatron.hasNoHoldsOnRecord();
	}

	private boolean printErrorMessage() {
		System.out.println("Patron either cannot be found or has no holds on record.");
		return false;
	}

	private boolean attemptToReslveHold(Patron offendingPatron) {

		MenuBuilder resolveMenu = this.buildResolveHoldMenu(offendingPatron);
		System.out.println(resolveMenu.displayMenuWithoutBanner());

		int selection = super.userInput().askForSelection(resolveMenu.getMenuItems());
		boolean validSelection = this.selectsValidHold(resolveMenu, selection);
		
		return validSelection ? this.tryToResolveHold(offendingPatron, selection) : false;
	}

	private boolean tryToResolveHold(Patron offendingPatron, int selection) {

		boolean canResolveHold = this.resolveHoldConfirmation();
		Hold holdInQuestion = offendingPatron.getAllHolds().get(selection - 1);
		return canResolveHold ? offendingPatron.resolvedHold(holdInQuestion) : false;
	}

	private boolean resolveHoldConfirmation() {
		return super.userInput().askBinaryQuestion("Hold can be resolved? (y/n)", "y", "n");
	}
	
	/***** RESOLVE HOLD MENU *******************************/

	private boolean selectsValidHold(MenuBuilder resolveMenu, int selection) {
		return selection >= 1 && selection <= resolveMenu.getMenuItems().size() - 1;
	}

	private MenuBuilder buildResolveHoldMenu(Patron offendingPatron) {

		MenuBuilder selectHoldMenu = new MenuBuilder();

		List<String> outstandingHolds = this.allToString(offendingPatron.getAllHolds());
		outstandingHolds.add("Exit");

		selectHoldMenu.setMenuTitle("Select Hold:");
		selectHoldMenu.setMenuItems(outstandingHolds);

		return selectHoldMenu;
	}

	private List<String> allToString(List<Hold> theHolds) {
		return theHolds.stream().map(eachHold -> "\n" + eachHold.getHoldMessage()).collect(Collectors.toList());
	}
}
