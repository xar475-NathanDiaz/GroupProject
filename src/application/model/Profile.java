package application.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Profile{
	String name;
	String id;
	double gpa;
	ArrayList<Schedule> schedules;

	public static String loadFile;

	private Profile(){
		this.schedules = new ArrayList<>();
	}

	public Profile(String name, String id, double gpa){
		this();
		this.name = name;
		this.id = id;
		this.gpa = gpa;
	}

	/**
	 * reads the
	 * @return Arraylist of Strings containing all the schedules that the profile references
	 */
	public static ArrayList<String> getSchedulesFromFile(){
		ArrayList<String> schedules = new ArrayList<>();
		try {
			FileReader reader = new FileReader(loadFile);
			BufferedReader buff = new BufferedReader(reader);
			//since all schedules are stored on line 3, skip the first 3 lines, limit the read to 1, and get that as a string
			String schedulesLine = buff.lines().skip(3).limit(1).collect(Collectors.toList()).get(0);
			buff.close();

			//break the line down into individual schedule names
			Scanner scheduleScan = new Scanner(schedulesLine);
			scheduleScan.useDelimiter(",");
			while(scheduleScan.hasNext()){
				schedules.add(scheduleScan.next());
			}
			scheduleScan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return schedules;
	}
}