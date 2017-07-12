package textbookRentalLibrary.controllers.hold;

import java.util.List;
import java.util.stream.Collectors;

import model.patron.Patron;
import model.patron.hold.Hold;
import textbookRentalLibrary.menus.MenuBuilder;

public class ResolveHoldController extends HoldController {

	public ResolveHoldController() {
		super();

	}

	public void resolvePatronHold() {

		Patron offendingPatron = super.queryDB().locatePatronInDB();

		boolean canResolveMoreHolds = true;
		while (canResolveMoreHolds) {

			if (offendingPatron == null || offendingPatron.hasNoHoldsOnRecord()) {
				System.out.println("Patron has no holds on record");
				canResolveMoreHolds = false;

			} else {

				MenuBuilder resolveMenu = this.buildResolveHoldMenu(offendingPatron);
				System.out.println(resolveMenu.displayMenuWithoutBanner());

				int selection = super.getInput().askForSelection(resolveMenu.getMenuItems());

				if (selectsValidHold(resolveMenu, selection)) {

					if (resolveHoldConfirmation()) {
						super.getManage().resolvedHold(offendingPatron, offendingPatron.getAllHolds().get(selection - 1));
					}

				} else {

					canResolveMoreHolds = false;
				}
			}
		}
	}

	private boolean resolveHoldConfirmation() {
		return super.getInput().askBinaryQuestion("Hold can be resolved? (y/n)", "y", "n");
	}

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
