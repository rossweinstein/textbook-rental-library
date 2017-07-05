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
		this.localAddress = null;
		this.permanentAddress = null;
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
}
