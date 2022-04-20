package application.model;

import java.util.ArrayList;

class Profile{
	String name;
	String id;
	double gpa;
	ArrayList<Schedule> schedules;

	private Profile(){
		this.schedules = new ArrayList<>();
	}

	public Profile(String name, String id, double gpa){
		this();
		this.name = name;
		this.id = id;
		this.gpa = gpa;
	}
}