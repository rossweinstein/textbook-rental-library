package textbookRentalLibraryTest.menusTest;

import static org.junit.Assert.*;

import org.junit.Test;

import textbookRentalLibrary.menus.TextbookRentalLibraryMainMenu;
import textbookRentalLibrary.menus.managerMenus.DamageHoldsMenu;
import textbookRentalLibrary.menus.managerMenus.MiscHoldsMenu;
import textbookRentalLibrary.menus.managerMenus.OverdueHoldsMenu;
import textbookRentalLibrary.menus.managerMenus.UnshelvedHoldsMenu;

public class TRLMenuTest {

	@Test
	public void createMainMenu() {
		TextbookRentalLibraryMainMenu trlMain = new TextbookRentalLibraryMainMenu();
		assertTrue(trlMain instanceof TextbookRentalLibraryMainMenu);
	}
	
	@Test
	public void displayDamageHoldsMenu() {
		DamageHoldsMenu menu = new DamageHoldsMenu();
		assertTrue(menu instanceof DamageHoldsMenu);
	}
	
	@Test
	public void displayOverdueHoldsMenu() {
		OverdueHoldsMenu menu = new OverdueHoldsMenu();
		assertTrue(menu instanceof OverdueHoldsMenu);
	}
	
	@Test
	public void displayUnshelvedHoldsMenu() {
		UnshelvedHoldsMenu menu = new UnshelvedHoldsMenu();
		assertTrue(menu instanceof UnshelvedHoldsMenu);
	}
	
	@Test
	public void displayMiscHoldsMenu() {
		MiscHoldsMenu menu = new MiscHoldsMenu();
		assertTrue(menu instanceof MiscHoldsMenu);
	}

}
