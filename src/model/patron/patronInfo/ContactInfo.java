package model.patron.patronInfo;

public class ContactInfo {
	
	private String firstName;
	private String lastName;
	
	public ContactInfo() {
		this.firstName = "";
		this.lastName = "";
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

	
}
