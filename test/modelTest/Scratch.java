package modelTest;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import database.FakeDB;
import model.Manager;
import model.copy.Copy;
import model.patron.Patron;
import model.patron.hold.CopyDamagedHold;
import model.patron.hold.Hold;

public class Scratch {

	public static void main(String[] args) {
		
		Manager manage = new Manager();
		
		Patron ross = FakeDB.getPatron("P2");
		Copy textbook = FakeDB.getCopy("C1");

		ross.checkCopyOut(textbook);
		ross.checkCopyIn(textbook);
		
		Patron mowlid = FakeDB.getPatron("P3");
		Copy textbook2 = FakeDB.getCopy("C2");

		mowlid.checkCopyOut(textbook2);
		mowlid.checkCopyIn(textbook2);
		
		Patron neera = FakeDB.getPatron("P4");
		Copy textbook3 = FakeDB.getCopy("C3");

		neera.checkCopyOut(textbook3);
		neera.checkCopyIn(textbook3);

		manage.markDamageHold(ross, textbook, 10);
		manage.markDamageHold(mowlid, textbook2, 10);
		manage.markUnshelevedHold(neera, textbook3, 10);
		
		List<Patron> damageList = getSpecificHold("DAMAGED");
		
		System.out.println(damageList.size());

	}
	
	private static List<Patron> getSpecificHold(String holdType) {

		List<Patron> matchingPatrons = new ArrayList<>();
		String regex = ".*\\b" + holdType + "\\b.*";
		Pattern regexPattern = Pattern.compile(regex);

		for (Patron eachPatron : FakeDB.getAllPatrons()) {
			
			System.out.println("CHECKING PATRON: " + eachPatron.toString());

			for (Hold eachHold : eachPatron.getAllHolds()) {
				
				System.out.println("CHECKING COPY: " + eachHold.getHoldMessage());
				
				Matcher match = regexPattern.matcher(eachHold.getHoldMessage());

				if (match.find()) {
					
					System.out.println("-------MATCH FOUND----------");

					if (!matchingPatrons.contains(eachPatron)) {
						
						System.out.println("------------------ADDING PATRON TO LIST------------------------------");
						matchingPatrons.add(eachPatron);
					}
				}
			}
		}
		return matchingPatrons;
	}

}
