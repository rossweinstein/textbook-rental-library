package database;
// SEIS 635 TP-1 : Mowlid Abdillahi | Neera Chaudhary | Ross Weinstein

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.copy.Copy;
import model.patron.Patron;
import model.patron.patronInfo.Address;
import model.patron.patronInfo.ContactInfo;

public class FakeDB {
	private static Map<String, Patron> patronStore;
	private static Map<String, Copy> copyStore;

	static // the following runs once when class is loaded: "static initializer"
	{
		patronStore = new HashMap<String, Patron>();
		copyStore = new HashMap<String, Copy>();

		patronStore.put("P1", createPatronEric());
		patronStore.put("P2", createPatronRoss());
		patronStore.put("P3", createPatronMowlid());
		patronStore.put("P4", createPatronNeera());

		copyStore.put("C1", new Copy("C1", "Fun with Objects"));
		copyStore.put("C2", new Copy("C2", "More Fun with Objects"));
		copyStore.put("C3", new Copy("C3", "Plenty Of Fun with Objects"));
		copyStore.put("C4", new Copy("C4", "No Fun with Objects"));
		copyStore.put("C5", new Copy("C5", "Some Fun with Objects"));
		copyStore.put("C6", new Copy("C6", "Occasional Fun with Objects"));
		copyStore.put("C7", new Copy("C7", "Questionable Fun with Objects"));
		copyStore.put("C8", new Copy("C8", "Tons of Fun with Objects"));
		copyStore.put("C9", new Copy("C9", "Forgotten Fun with Objects"));
	}

	public static List<Patron> getAllPatrons() {
		return patronStore.values().stream().collect(Collectors.toList());
	}

	public static List<Copy> getAllCopies() {
		return copyStore.values().stream().collect(Collectors.toList());
	}

	public static Patron getPatron(String patronID) {
		return patronStore.get(patronID);
	}

	public static Copy getCopy(String copyID) {
		return copyStore.get(copyID);
	}

	private static Patron createPatronEric() {

		ContactInfo ericContact = new ContactInfo();
		ericContact.setFirstName("Eric");
		ericContact.setLastName("Level");
		ericContact.setPhoneNumber("8673254837");

		Address localAddress = new Address();
		localAddress.setAddressLineOne("3324 Lake Street");
		localAddress.setCity("Minneapolis");
		localAddress.setState("MN");
		localAddress.setZipCode("55418");

		ericContact.setPermanentAsLocalAddress();

		return new Patron("P1", ericContact);
	}

	private static Patron createPatronRoss() {
		ContactInfo rossContact = new ContactInfo();
		rossContact.setFirstName("Ross");
		rossContact.setLastName("Weinstein");
		rossContact.setPhoneNumber("3038516529");

		Address localAddress = new Address();
		localAddress.setAddressLineOne("9513 Market Street");
		localAddress.setCity("St. Paul");
		localAddress.setState("MN");
		localAddress.setZipCode("55115");
		
		rossContact.setLocalAddress(localAddress);

		Address permanentAddress = new Address();
		permanentAddress.setAddressLineOne("9513 Market Street");
		permanentAddress.setAddressLineTwo("Unit 203");
		permanentAddress.setCity("St. Paul");
		permanentAddress.setState("MN");
		permanentAddress.setZipCode("55115");

		rossContact.setPermanentAddress(permanentAddress);

		return new Patron("P2", rossContact);
	}

	private static Patron createPatronMowlid() {
		ContactInfo mowlidContact = new ContactInfo();
		mowlidContact.setFirstName("Mowlid");
		mowlidContact.setLastName("Abdillahi");
		mowlidContact.setPhoneNumber("3126547851");

		Address localAddress = new Address();
		localAddress.setAddressLineOne("8421 Main Street");
		localAddress.setCity("Minneapolis");
		localAddress.setState("MN");
		localAddress.setZipCode("55410");
		
		mowlidContact.setLocalAddress(localAddress);

		Address permanentAddress = new Address();
		permanentAddress.setAddressLineOne("3252 63rd Ave N");
		permanentAddress.setAddressLineTwo("Apt 417");
		permanentAddress.setCity("St. Paul");
		permanentAddress.setState("MN");
		permanentAddress.setZipCode("55118");

		mowlidContact.setPermanentAddress(permanentAddress);

		return new Patron("P3", mowlidContact);
	}

	private static Patron createPatronNeera() {

		ContactInfo neeraContact = new ContactInfo();
		neeraContact.setFirstName("Neera");
		neeraContact.setLastName("Chaudhary");
		neeraContact.setPhoneNumber("6519952641");

		Address localAddress = new Address();
		localAddress.setAddressLineOne("1115 21st Ave S");
		localAddress.setCity("Richfield");
		localAddress.setState("MN");
		localAddress.setZipCode("55424");

		neeraContact.setPermanentAsLocalAddress();

		return new Patron("P4", neeraContact);
	}
}
