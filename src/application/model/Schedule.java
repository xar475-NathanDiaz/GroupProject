package application.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Schedule{
	public static String loadFile;
	String name;
	ArrayList<Plan> scheduledPlans;

	public Schedule(){
		scheduledPlans = new ArrayList<>();
		initPlanList(loadFile);
	}

	public Schedule(String fileName, String name){
		this();
		this.name = name;
	}

	private void initPlanList(String fileName){
		FileReader in;
		try {
			in = new FileReader(fileName);
			BufferedReader reader = new BufferedReader(in);
			//skip the first line since that is the header known to be "schedule"
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

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public ArrayList<String> listPlanNames(){
		ArrayList<String> planNames = new ArrayList<>();
		for (Plan plan:scheduledPlans) {
			planNames.add(plan.name);
		}
		return planNames;
	}
}