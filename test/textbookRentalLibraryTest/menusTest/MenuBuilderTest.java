package textbookRentalLibraryTest.menusTest;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import textbookRentalLibrary.menus.MenuBuilder;

public class MenuBuilderTest {

	private MenuBuilder menu;
	private MenuBuilder menu2;

	@Before
	public void setUp() {
		this.menu = new MenuBuilder("Menu", "Option1");
		this.menu2 = new MenuBuilder();
	}

	@Test
	public void cansetMenuTitle() {
		this.menu2.setMenuTitle("Menu2");
		assertTrue(this.menu2.getMenuTitle().equals("Menu2"));
	}

	@Test
	public void canSetMenuItems() {

		List<String> items = Arrays.asList("One", "Two");
		this.menu2.setMenuItems(items);
		assertTrue(this.menu2.getMenuItems().equals(items));
	}

	@Test
	public void testMenuWithoutBanner() {

		String menuWithoutBanner = this.menu.getMenuTitle() + "\n1: Option1\n";
		assertTrue(this.menu.displayMenuWithoutBanner().equals(menuWithoutBanner));
	}

	@Test
	public void testMenuWithBanner() {

		String topAndBottom = "************";
		String twoAndFour = "*          *";
		String title = "*   Menu   *";
		String option = "\n1: Option1\n";

		String wholeBanner = topAndBottom + "\n" + twoAndFour + "\n" + title + "\n" + twoAndFour + "\n" + topAndBottom
				+ "\n" + option;
		assertTrue(this.menu.displayMenuWithBanner().equals(wholeBanner));
	}
	
	@Test
	public void getCorrectItem() {
		assertTrue(this.menu.selectItem(1).equals("Option1"));
	}

}
