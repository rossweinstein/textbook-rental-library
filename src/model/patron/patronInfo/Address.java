package model.patron.patronInfo;

import java.util.Arrays;

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
		this.addressLineOne = lineTwo;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {

		if (this.validState(state)) {
			this.state = state;
		} else {
			this.state = "";
		}
	}

	public boolean validState(String state) {
		return Arrays.stream(USStates.values()).anyMatch(usStates -> usStates.toString().equals(state));
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		if (this.validCity(city)) {
			this.city = city;
		} else {
			this.city = "";
		}
	}

	public boolean validCity(String city) {
		return city.chars().allMatch(Character::isLetter);
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zip) {
		if (this.validZipCode(zip)) {
			this.zipCode = zip;
		} else {
			this.zipCode = "";
		}
	}

	public boolean validZipCode(String zip) {
		return zip.length() == 5 || zip.length() == 9 && zip.chars().allMatch(Character::isDigit);
	}

	public String getFormattedAddress() {

		return this.addressLineOne + "\n" + this.lineTwoIfExists() + this.city + ", " + this.state + " " + this.zipCode;
	}

	private String lineTwoIfExists() {
		if (this.addressLineTwo.isEmpty()) {
			return "";
		} else {
			return this.addressLineTwo + "\n";
		}
	}
}
