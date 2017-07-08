package textbookRentalLibrary.controllers.hold;

import java.util.List;
import java.util.stream.Collectors;

import model.patron.Patron;
import textbookRentalLibrary.menus.MenuBuilder;

public class ResolveHoldController extends HoldController {
	
	public ResolveHoldController(){
		super();
		
	}
	public void resolvePatronHold() {

		Patron offendingPatron = super.getDB().locatePatronInDB();

		if (offendingPatron == null || offendingPatron.getAllHolds().size() == 0) {
			System.out.println("Patron has no holds on record");
			
		} else {

			boolean canResolveMoreHolds = true;
			while (canResolveMoreHolds) {

				MenuBuilder resolveMenu = this.buildResolveHoldMenu(offendingPatron);
				resolveMenu.displayMenuWithoutBanner();

				int selection = super.getInput().askForSelection(resolveMenu.getMenuItems());

				if (selectsValidHold(resolveMenu, selection)) {

					if (resolveHoldConfirmation()) {
						offendingPatron.resolvedHold(offendingPatron.getAllHolds().get(selection - 1));
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

		List<String> outstandingHolds = offendingPatron.getAllHolds().stream().map(hold -> hold.toString())
				.collect(Collectors.toList());
		outstandingHolds.add("Exit");

		selectHoldMenu.setMenuTitle("Select Hold:");
		selectHoldMenu.setMenuItems(outstandingHolds);
		
		return selectHoldMenu;
	}


}
