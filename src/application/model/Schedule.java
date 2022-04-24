package application.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Schedule{
	public static String loadFile;
	String name;
	ArrayList<Plan> scheduledPlans;

	/**
	 * loads a new Schedule object from the file indicated by loadFile
	 */
	public Schedule(){
		scheduledPlans = new ArrayList<>();
		this.name = trimPathFromName(loadFile);
		if(loadFile==null||loadFile.isEmpty()){
			return;
		}
		initPlanList(loadFile);
	}

	/**
	 * Method used in the schedule constructor to trim the extra characters that make up the relative pathname from the schedule name
	 * Inside the current pathname, the schedule name is surrounded by "data/" and ".txt"
	 * @param currPath the current string which schedule name is a substring of
	 * @return The desired schedule name
	 */
	private String trimPathFromName(String currPath){
		//return if currPath is empty
		if(currPath.isEmpty()){
			return "";
		}
		//first, take off the "data/" from the path
		String trimedPath = currPath.split("/",2)[1];
		//next, take off the ".txt" from the path
		trimedPath = trimedPath.split("\\.")[0];
		return trimedPath;
	}

	/**
	 * populate's this schedule's planList with plans from the schedule file indicated by fileName
	 * @param fileName path to a schedule file
	 */
	private void initPlanList(String fileName){
		FileReader in;
		try {
			in = new FileReader(fileName);
			BufferedReader reader = new BufferedReader(in);
			//skip first line since the header is uneeded
			reader.readLine();
			String buff = "";
			while((buff = reader.readLine())!=null){
				Plan currLinePlan = new Plan();
				Scanner lineParser = new Scanner(buff);
				lineParser.useDelimiter(",");
				//enter the plan type
				currLinePlan.setPlanFromString(lineParser.next());
				//enter the plan name
				currLinePlan.setName(lineParser.next());
				//enter the plan days
				currLinePlan.setDaysFromString(lineParser.next());
				//enter the plan time
				currLinePlan.setTime(lineParser.nextInt());
				lineParser.close();
				scheduledPlans.add(currLinePlan);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a plan to this schedule without calling this schdule's plan list first
	 * Also updates the schedule's file
	 * @param newPlan plan object to be added to this schedule
	 */
	public void addPlan(Plan newPlan){
		this.scheduledPlans.add(newPlan);
		updateSchedule();
	}

	/**
	 * @return this schedule's name
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Will filter plan times to the ones that match the day inputed in the function parameter
	 * @param dayInitial parameter that represents a requested day by its assigned initial 
	 * @return ArrayList of all the plan times are included by the day parameter
	 */
	public ArrayList<String> listPlanTimesByDay(char dayInitial){
		ArrayList<String> planTimes = new ArrayList<>();
		for(Plan plan:scheduledPlans){
			//test if the day is in the plan's String
			if(plan.getDaysString().indexOf(dayInitial)!=-1){
				//if true, add to planNames
				planTimes.add(plan.getTimeString());
			}
		}
		return planTimes;
	}

	/**
	 * Will filter plan names to the ones that match the day inputed in the function parameter
	 * @param dayInitial parameter that represents a requested day by its assigned initial 
	 * @return ArrayList of all the plan names are included by the day parameter
	 */
	public ArrayList<String> listPlanNamesByDay(char dayInitial){
		ArrayList<String> planNames = new ArrayList<>();
		for(Plan plan:scheduledPlans){
			//test if the day is in the plan's String
			if(plan.getDaysString().indexOf(dayInitial)!=-1){
				//if true, add to planNames
				planNames.add(plan.getName());
			}
		}
		return planNames;
	}

	/**
	 * overwrites the current schedule file with this schedule's current contents
	 */
	public void updateSchedule(){
		//return if loadFile is empty
		if(loadFile.isEmpty()){
			return;
		}
		try {
			FileWriter out = new FileWriter(loadFile);
			BufferedWriter writer = new BufferedWriter(out);
			writer.append("schedule\n");
			for (Plan plan : scheduledPlans) {
				writer.append(plan.toString());
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}