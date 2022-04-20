package application.model;

import java.util.ArrayList;

class Schedule{
	String name;
	ArrayList<Plan> scheduledPlans;
	int classHours;

	private Schedule(){
		scheduledPlans = new ArrayList<>();
	}

	public Schedule(String name, int hours){
		this();
		this.name = name;
		this.classHours = hours;

	}
}