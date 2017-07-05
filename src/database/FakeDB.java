package database;
// SEIS 635 TP-1 : Mowlid Abdillahi | Neera Chaudhary | Ross Weinstein

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.copy.Copy;
import model.patron.Patron;

public class FakeDB {
	private static Map<String, Patron> patronStore;
	private static Map<String, Copy> copyStore;

	static // the following runs once when class is loaded: "static initializer"
	{
		patronStore = new HashMap<String, Patron>();
		copyStore = new HashMap<String, Copy>();

		patronStore.put("P1", new Patron("P1", "Eric"));
		patronStore.put("P2", new Patron("P2", "Ross"));
		patronStore.put("P3", new Patron("P3", "Mowlid"));
		patronStore.put("P4", new Patron("P4", "Neera"));
		
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
}
