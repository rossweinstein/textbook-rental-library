package model.patron.patronInfo;

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

	@Override
	public String toString() {
		return "Name: " + this.firstName + " " + this.lastName + "\nTelephone Number: " + this.phoneNumber
				+ "\nLocal Address:\n" + this.localAddress.toString() + "\nPermanent Address:\n"
				+ this.permanentAddress.toString();
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

		if (!this.phoneNumber.isEmpty()) {

			formattedNumber = this.phoneNumber.substring(0, 3) + "." + this.phoneNumber.substring(3, 6) + "."
					+ this.phoneNumber.substring(6);
		}
		return formattedNumber;
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
		return !this.firstName.isEmpty() && !this.lastName.isEmpty() && !this.phoneNumber.isEmpty()
				&& this.localAddress != null && this.permanentAddress != null;
	}

	public void setPermanentAsLocalAddress() {
		this.setPermanentAddress(this.localAddress);
	}

	public void setLocalAsPermanentAddress() {
		this.setLocalAddress(this.permanentAddress);
	}
}
