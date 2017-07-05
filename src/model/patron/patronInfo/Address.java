package model.patron.patronInfo;

public class Address {
	
	private String addressLineOne;
	private String addressLineTwo;
	private String state;
	
	public Address() {
		this.addressLineOne = "";
		this.addressLineTwo = "";
		
	}

	public void setAddressLineOne(String lineOne) {
		this.addressLineOne = lineOne;
	}
	
	public void setAddressLineTwo(String lineTwo) {
		this.addressLineOne = lineTwo;
	}
	
	public void setState(String state) {
		this.state = state;
	}
}
