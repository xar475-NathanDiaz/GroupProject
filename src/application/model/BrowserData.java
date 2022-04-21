package application.model;

import java.io.File;
import java.util.ArrayList;

public class BrowserData {
	/**
	 * this is a int that represents the previous menu for the back button
	 * 0=Main
	 * 1=Schedule
	 * 2=Profile
	 */
	public static int prevMenu;

	/**
	 * this is an int that represents what type of items the menu should display and open
	 * 0=Schedules
	 * 1=Profiles
	 */
	public static int menuType;

	/**
	 * this method finds all files in the data directory with the corresponding item type
	 * then the method puts the names of the files it finds into an arraylist
	 * it appends an item titled "New" to enable the user to create items
	 * then it returns the arraylist
	 */
	public static ArrayList<String> initItemList() {
		ArrayList<String> fileNames = new ArrayList<>();
		File dataDir = new File("data");
		File[] files = dataDir.listFiles();
		for (File file : files) {
			fileNames.add(file.getName());
		}
		fileNames.add("New");
		return fileNames;
	}
}
