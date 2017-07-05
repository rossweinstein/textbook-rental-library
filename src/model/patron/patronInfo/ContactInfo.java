package model.patron.patronInfo;

public class ContactInfo {
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	
	public ContactInfo() {
		this.firstName = "";
		this.lastName = "";
		this.phoneNumber = "";
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
		this.phoneNumber = phoneNumber;
	}

	
}
