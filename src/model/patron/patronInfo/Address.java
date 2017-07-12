package model.patron.patronInfo;

import java.util.Arrays;

/**
 * A simple class to hold all the relevant information for an Address. At this
 * point, this class can only validate addresses in the US.  This class was created with TDD.
 * 
 * @author Ross Weinstein
 *
 */

public class Address {

	private String addressLineOne;
	private String addressLineTwo;
	private String city;
	private String state;
	private String zipCode;

	public Address() {
		this.addressLineOne = "";
		this.addressLineTwo = "";
		this.city = "";
		this.state = "";
		this.zipCode = "";
	}

	@Override
	public String toString() {
		return this.getFormattedAddress();
	}

	@Override
	public boolean equals(Object obj) {
		// self check
		if (this == obj) {
			return true;
		}

		// null check and type check
		if (obj == null || !(obj instanceof Address)) {
			return false;
		}

		// cast and comparisons
		Address otherAddress = (Address) obj;
		return this.addressLineOne.equals(otherAddress.addressLineOne)
				&& this.addressLineTwo.equals(otherAddress.addressLineTwo) && this.city.equals(otherAddress.city)
				&& this.state.equals(otherAddress.state) && this.zipCode.equals(otherAddress.zipCode);

	}

	public boolean isEmpty() {
		return this.addressLineOne.isEmpty() && this.addressLineTwo.isEmpty() && this.city.isEmpty()
				&& this.state.isEmpty() && this.zipCode.isEmpty();
	}

	public String getAddressLineOne() {
		return this.addressLineOne;
	}

	public void setAddressLineOne(String lineOne) {
		this.addressLineOne = lineOne;
	}

	public String getAddressLineTwo() {
		return this.addressLineTwo;
	}

	public void setAddressLineTwo(String lineTwo) {
		this.addressLineTwo = lineTwo;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {

		if (this.validState(state)) {
			this.state = state;
		}
	}

	private boolean validState(String state) {
		return Arrays.stream(USStates.values()).anyMatch(usStates -> usStates.toString().equals(state));
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		if (this.validCity(city)) {
			this.city = city;
		}
	}

	private boolean validCity(String city) {
		return city.matches("[A-Za-z\\. ]+");
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zip) {
		if (this.validZipCode(zip)) {
			this.zipCode = zip;
		}
	}

	private boolean validZipCode(String zip) {
		return zip.length() == 5 || zip.length() == 9 && zip.chars().allMatch(Character::isDigit);
	}

	private String getFormattedAddress() {
		return this.addressLineOne + "\n" + this.lineTwoIfExists() + this.city + ", " + this.state + " " + this.zipCode;
	}

	private String lineTwoIfExists() {
		if (this.addressLineTwo.equals("")) {
			return "";
		} else {
			return this.addressLineTwo + "\n";
		}
	}
}
