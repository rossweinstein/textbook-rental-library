package textbookRentalLibrary.menus;

import java.util.Arrays;

import model.patron.Patron;
import model.patron.patronInfo.Address;

/**
 * This menu allows a user to update any part of their information except for
 * type (Student, Faculty, Staff), that is set when they are added to the library.
 * 
 * @author Ross Weinstein
 *
 */

public class PatronInfoUpdateMenu extends TRLMenu {

	private Patron patronToModify;

	public PatronInfoUpdateMenu(Patron patron) {
		super();
		this.patronToModify = patron;
	}

	private MenuBuilder patronInfoMenu() {

		super.buildMenu().setMenuTitle("\nUpdate Patron Information");
		super.buildMenu().setMenuItems(Arrays.asList("First Name", "Last Name", "Phone Number", "Local Address",
				"Permanent Address", "Finished"));
		return super.buildMenu();
	}

	@Override
	public void displayMenu() {
		super.printMenuToConsole(this.patronInfoMenu());
	}

	@Override
	protected boolean continueMakingSelections() {

		int selection = super.userInput().askForSelection(this.patronInfoMenu().getMenuItems());

		if (selection == 1) {
			this.updatePatronsFirstName();

		} else if (selection == 2) {
			this.updatePatronsLastName();

		} else if (selection == 3) {
			this.updatePatronsPhoneNumber();

		} else if (selection == 4) {
			this.updatePatronsLocalAddress();

		} else if (selection == 5) {
			this.updatePatronsPermanentAddress();

		} else {

			System.out.println(this.patronToModify.toString());

			return super.userInput().askBinaryQuestion("Correct? (y/n)", "n", "y");
		}
		return true;
	}

	private void updatePatronsFirstName() {
		String correctName = super.userInput().askForString("\nWhat is the correct spelling of your first name?");
		patronToModify.getContactInfo().setFirstName(correctName);
	}

	private void updatePatronsLastName() {
		String correctName = super.userInput().askForString("\nWhat is the correct spelling of your last name?");
		patronToModify.getContactInfo().setFirstName(correctName);
	}

	private void updatePatronsPhoneNumber() {
		String correctNumber = super.userInput().askForString("\nWhat is your new phone number?");
		patronToModify.getContactInfo().setFirstName(correctNumber);
	}

	private void updatePatronsLocalAddress() {

		this.patronToModify.getContactInfo().setLocalAddress(this.updateAddress());

	}

	private void updatePatronsPermanentAddress() {

		this.patronToModify.getContactInfo().setPermanentAddress(this.updateAddress());
	}

	private Address updateAddress() {

		Address updatedAddress = new Address();

		updatedAddress.setAddressLineOne(super.userInput().askForString("What is the street address: "));

		if (super.userInput().askBinaryQuestion("Apt or unit number? (y/n)", "y", "n")) {
			updatedAddress.setAddressLineTwo(super.userInput().askForString("Apt or unit number?"));
		}

		updatedAddress.setCity(super.userInput().askForString("City: "));
		updatedAddress.setState(super.userInput().askForString("State (2 letter code): "));
		updatedAddress.setZipCode(super.userInput().askForString("Zip Code: "));
		return updatedAddress;
	}
}
