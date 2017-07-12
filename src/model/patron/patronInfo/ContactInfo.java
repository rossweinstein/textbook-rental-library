package model.patron.patronInfo;

/**
 * Holds all the relevant information for the TRL to be able to contact a
 * Patron.  This class was created with TDD.
 * 
 * @author Ross Weinstein
 *
 */

public class ContactInfo {

	private String firstName;
	private String lastName;
	private String phoneNumber;
	private Address localAddress;
	private Address permanentAddress;

	public ContactInfo() {
		this.firstName = "";
		this.lastName = "";
		this.phoneNumber = "";
		this.localAddress = new Address();
		this.permanentAddress = new Address();
	}
	
	/***** GETTERS / SETTERS *******************************/

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getFormattedTelephoneNumber() {

		String formattedNumber = "";

		if (this.phoneNumberIsListed()) {

			formattedNumber = this.phoneNumber.substring(0, 3) + "." + this.phoneNumber.substring(3, 6) + "."
					+ this.phoneNumber.substring(6);
		}
		return formattedNumber;
	}

	private boolean phoneNumberIsListed() {
		return !this.phoneNumber.isEmpty();
	}

	public void setPhoneNumber(String phoneNumber) {
		if (this.validPhoneNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		}
	}
	
	private boolean validPhoneNumber(String number) {
		return number.length() == 10 && number.chars().allMatch(Character::isDigit);
	}
	
	public Address getLocalAddress() {
		return localAddress;
	}

	public void setLocalAddress(Address localAddress) {
		this.localAddress = localAddress;
	}

	public Address getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(Address permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public boolean allContactInfoSet() {
		return !this.firstName.isEmpty() && !this.lastName.isEmpty() && phoneNumberIsListed()
				&& this.localAddress != null && this.permanentAddress != null;
	}

	
	/***** OVERRIDE METHODS *******************************/
	
	@Override
	public String toString() {
		return "Name: " + this.firstName + " " + this.lastName + "\nTelephone Number: "
				+ this.getFormattedTelephoneNumber() + "\nLocal Address:\n" + this.localAddress.toString()
				+ "\nPermanent Address:\n" + this.permanentAddress.toString();
	}

	@Override
	public boolean equals(Object obj) {
		// self check
		if (this == obj) {
			return true;
		}

		// null check and type check
		if (obj == null || !(obj instanceof ContactInfo)) {
			return false;
		}

		// cast and comparisons
		ContactInfo otherContact = (ContactInfo) obj;
		return this.firstName.equals(otherContact.firstName) && this.lastName.equals(otherContact.lastName)
				&& this.phoneNumber.equals(otherContact.phoneNumber)
				&& this.localAddress.equals(otherContact.localAddress)
				&& this.permanentAddress.equals(otherContact.permanentAddress);
	}

	
	/***** LOCAL/PERMANENT ADDRESS METHODS *******************************/
	
	public void setPermanentAsLocalAddress() {
		this.setPermanentAddress(this.localAddress);
	}

	public void setLocalAsPermanentAddress() {
		this.setLocalAddress(this.permanentAddress);
	}

	public String getAddresses() {
		return atLeastOneAddressProvided() ? retrieveAddress() : "This Patron Currently Has No Addresses On File";
	}
	
	/***** CONTACT ADDRESSES HELPER METHODS ******************************/
	
	private boolean atLeastOneAddressProvided() {
		return !this.localAddress.isNotFilledOut() || !this.permanentAddress.isNotFilledOut();
	}
	
	private String retrieveAddress() {
		return this.onlyOneAddressIsProvided() ? this.getListedAddress() : this.getBothAddresses();
	}
	
	private String getListedAddress() {
		Address address = !this.localAddress.isNotFilledOut() ? this.localAddress : this.permanentAddress;
		return "Address:\n" + address.toString();
	}
	
	private String getBothAddresses() {
		return "Local Address:\n" + this.localAddress.toString() + "\n\nPermanent Address" + "\n"
				+ this.permanentAddress.toString();
	}

	private boolean onlyOneAddressIsProvided() {
		return this.oneAddressIsBlank() || this.localAddressSameAsPermanentAddress();
	}

	private boolean localAddressSameAsPermanentAddress() {
		return this.localAddress.equals(this.permanentAddress);
	}

	private boolean oneAddressIsBlank() {
		return this.localAddress.isNotFilledOut() || this.permanentAddress.isNotFilledOut();
	}

}
