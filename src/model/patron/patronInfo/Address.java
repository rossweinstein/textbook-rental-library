package model.patron.patronInfo;

import java.util.Arrays;

public class Address {

	private String addressLineOne;
	private String addressLineTwo;
	private String city;
	private String state;

	public Address() {
		this.addressLineOne = "";
		this.addressLineTwo = "";
		this.city = "";
		this.state = "";
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
		return Arrays.stream(USStates.values()).anyMatch(usStates -> usStates.equals(state));
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
}
