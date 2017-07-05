package textbookRentalLibrary.menus;

import java.util.Arrays;

import model.patron.Patron;
import model.patron.patronInfo.Address;

public class PatronInfoUpdateMenu extends TRLMenu {
	
	private Patron patronToModify;
	
	public PatronInfoUpdateMenu(Patron patron) {
		super();
		this.patronToModify = patron;
	}
	
	private MenuBuilder patronInfoMenu() {
		
		super.buildMenu().setMenuTitle("Update Patron Information");
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
			return false;
		}
		return true;
	}
	
	private void updatePatronsFirstName() {
		String correctName = super.userInput().askForString("\nWhat is the correct spelling of your first name?");
		patronToModify.getContactInfo().setFirstName(correctName);
		System.out.println();
	}

	private void updatePatronsLastName() {
		String correctName = super.userInput().askForString("\nWhat is the correct spelling of your last name?");
		patronToModify.getContactInfo().setFirstName(correctName);
		System.out.println();
	}

	private void updatePatronsPhoneNumber() {
		String correctNumber = super.userInput().askForString("\nWhat is your new phone number?");
		patronToModify.getContactInfo().setFirstName(correctNumber);
		System.out.println();
	}

	private void updatePatronsLocalAddress() {
		
		Address correctedAddress = new Address();

		boolean correctAddress = false;
		while(!correctAddress) {
			
			correctedAddress = this.updateAddress();
			System.out.println(correctedAddress.toString());
			
			correctAddress = super.userInput().askBinaryQuestion("Correct? (y/n)", "y", "n");
		}
		
		this.patronToModify.getContactInfo().setLocalAddress(correctedAddress);
		
	}

	private void updatePatronsPermanentAddress() {

		Address correctedAddress = new Address();

		boolean correctAddress = false;
		while(!correctAddress) {
			
			correctedAddress = this.updateAddress();
			System.out.println(correctedAddress.toString());
			
			correctAddress = super.userInput().askBinaryQuestion("Correct? (y/n)", "y", "n");
		}
		
		this.patronToModify.getContactInfo().setPermanentAddress(correctedAddress);
	}
	
	private Address updateAddress() {
		
		Address updatedAddress = new Address();
		
		updatedAddress.setAddressLineOne(super.userInput().askForString("What is the street address?"));
		
		if (super.userInput().askBinaryQuestion("Apt or unit number? (y/n)", "y", "n")) {
			updatedAddress.setAddressLineTwo(super.userInput().askForString("Apt or unit number?"));
		}
		
		updatedAddress.setCity(super.userInput().askForString("City?"));
		updatedAddress.setState(super.userInput().askForString("State (2 letter abbrev.)?"));
		updatedAddress.setZipCode(super.userInput().askForString("Zip Code?"));
		return updatedAddress;
	}
}
