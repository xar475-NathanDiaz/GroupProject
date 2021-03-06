package application.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class BrowserData {
	/**
	 * this is a int that represents the previous menu for the back button
	 * 0=Main
	 * 1=Schedule
	 */
	public static int prevMenu;

	/**
	 * this method finds all files in the data directory with the corresponding item type
	 * then the method puts the names of the files it finds into an arraylist
	 * it appends an item titled "New" to enable the user to create items
	 * then it returns the arraylist
	 */
	public static ArrayList<String> initItemList() {
		ArrayList<String> fileNames = new ArrayList<>();
		try {
			File dataDir = new File("data");
			File[] files = dataDir.listFiles();
			for (File file : files) {
				//create a reader for the specific file the loop is on
				FileReader reader = new FileReader(file);
				BufferedReader buff = new BufferedReader(reader);
				//get the header and only the header
				String headerLine = buff.readLine();
				//close reader as soon as possible
				buff.close();
				//check that the file header is schedule
				if(headerLine.equals("schedule")){
					fileNames.add(file.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//add New to all lists
		fileNames.add("New");
		return fileNames;
	}
}
