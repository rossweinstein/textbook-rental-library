package textbookRentalLibrary.menus;

import java.util.*;

/**
 * This class allows for the easy creation of Command Line menus. It allows for
 * the creation of a menu with or without a menu.
 * 
 * @author Ross Weinstein
 *
 */
public class MenuBuilder {

	private String menuTitle;
	private List<String> menuItems;
	
	public MenuBuilder() {
		this.menuTitle = "";
		this.menuItems = null;
	}

	/**
	 * Constructor asks for the menu title and all possible options as Strings
	 */
	public MenuBuilder(String title, String... options) {

		// give the menu a title
		this.menuTitle = title;

		// since a variable number of menu options are allowed
		// count how many options were given and organize them in an array
		this.menuItems = new ArrayList<>();

		for (String eachOption : options) {
			this.menuItems.add(eachOption);
		}
	}
	
	public void setMenuTitle(String title) {
		this.menuTitle = title;
	}
	
	public void setMenuItems(List<String> options) {
		this.menuItems = options;
	}

	/** Returns the menu title and all options in order */
	public String displayMenuWithoutBanner() {
		return this.menuTitle + "\n" + this.getOrderedListOfMenuItems();
	}

	/**
	 * Returns the menu title inside a border of '*' and all options in order
	 */
	public String displayMenuWithBanner() {
		return this.banner(menuTitle, 1) + "\n" + this.getOrderedListOfMenuItems();
	}

	/** Returns the menu title */
	public String getMenuTitle() {
		return this.menuTitle;
	}

	/** Returns each option within the menu */
	public List<String> getMenuItems() {
		return this.menuItems;
	}

	/** Returns each option within the menu as an ordered list */
	public String getOrderedListOfMenuItems() {

		// create a blank string
		String list = "";

		// we want our list to start at 1
		int position = 1;

		// loop through all the menuItems and order them into a list
		for (String eachItem : menuItems) {
			list += position + ": " + eachItem + "\n";
			position++;
		}
		return list;
	}

	/** Returns the selected menu item */
	public String selectItem(int item) {

		return this.menuItems.get(item - 1);
	}

	/**
	 * Creates a banner of '*' around a given String. Padding determines the
	 * spacing within the border.
	 */
	private String banner(String title, int paddingAmount) {
		
		String banner = "";

		int totalNumberOfRows = paddingAmount * 2 + 3;
		int totalNumberOfCols = title.length() * 2 + paddingAmount * 2 + 2;

		for (int currentRow = 0; currentRow != totalNumberOfRows; ++currentRow) {

			int currentCol = 0;

			while (currentCol != totalNumberOfCols) {

				if (currentRow == paddingAmount + 1 && currentCol == paddingAmount + 1 + title.length() / 2) {
					banner += title;
					currentCol += title.length();
				} else {
					if (currentRow == 0 || currentRow == totalNumberOfRows - 1 || currentCol == 0
							|| currentCol == totalNumberOfCols - 1)
						banner += "*";
					else
						banner += " ";
					++currentCol;
				}
			}
			banner += "\n";
		}
		return banner;
	}
}
