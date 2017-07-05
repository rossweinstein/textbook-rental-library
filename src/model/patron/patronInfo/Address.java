package model.patron.patronInfo;

import java.util.Arrays;

public class Address {
	
	private String addressLineOne;
	private String addressLineTwo;
	private String state;
	
	public Address() {
		this.addressLineOne = "";
		this.addressLineTwo = "";
		this.state = "";
	}

	public void setAddressLineOne(String lineOne) {
		this.addressLineOne = lineOne;
	}
	
	public void setAddressLineTwo(String lineTwo) {
		this.addressLineOne = lineTwo;
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
}
