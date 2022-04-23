package application.model;

class Plan{
	/**
	 * Enum to specify what type of plan this is.
	 * The enum will be able to change how the plan is displayed
	 * and how it is scheduled like if it has multiple times or days
	 */
	enum planType{COURSE,STUDY,ASSIGN,EXAM,EXTRACURR};

	/**
	 * These constant are bitmasks to make reading code for parsing the days field easier
	 */
	private final static int SUN = 64;
	private final static int MON = 32;
	private final static int TUE = 16;
	private final static int WED = 8;
	private final static int THU = 4;
	private final static int FRI = 2;
	private final static int SAT = 1;

	String name;
	/**
	 * days is a integer that will represent which days the plan is taking place on
	 * since there are cases that some plans take place on multiple days, each binary digit will represent a day
	 * days will be 7 binary digits long, so sunday will be the 64s digit, monday will be the 32s digit, ... saturday is the 1s digit.
	 */
	int days;
	/**
	 * time will be an integer that represents the time the plan takes place
	 * time is in minutes from midnight
	 */
	int time;
	planType type;

	public Plan(String name, int days, int time, planType type){
		this.name = name;
		this.days = days;
		this.time = time;
		this.type = type;
	}

	/**
	 * Uses this object's days field to produce a string
	 * @return A String that represents the days this plan is taking place on
	 */
	public String getDaysString(){
		String dayString = "";
		if((this.days & SUN) != 0){
			dayString = dayString + "U";
		}
		if((this.days & MON) != 0){
			dayString = dayString + "M";
		}
		if((this.days & TUE) != 0){
			dayString = dayString + "T";
		}
		if((this.days & WED) != 0){
			dayString = dayString + "W";
		}
		if((this.days & THU) != 0){
			dayString = dayString + "R";
		}
		if((this.days & FRI) != 0){
			dayString = dayString + "F";
		}
		if((this.days & SAT) != 0){
			dayString = dayString + "S";
		}
		return dayString;
	}

	/**
	 * @param fileString A string representing days of the week that was read from a schedule file
	 * @return A int that represents days that will be stored in 
	 */
	public int getDaysFromFileString(String fileString){
		int dayNum = 0;
		for (char i : fileString.toCharArray()) {
			if(i=='U'){
				dayNum = dayNum|SUN;
			}
			if(i=='M'){
				dayNum = dayNum|MON;
			}
			if(i=='T'){
				dayNum = dayNum|TUE;
			}
			if(i=='W'){
				dayNum = dayNum|WED;
			}
			if(i=='R'){
				dayNum = dayNum|THU;
			}
			if(i=='F'){
				dayNum = dayNum|FRI;
			}
			if(i=='S'){
				dayNum = dayNum|SAT;
			}
		}
		return dayNum;
	}

	/**
	 * Returns a string for this object in the format "A [type] called [name] taking place on: [days] at [time]"
	 */
	@Override
	public String toString(){
		String objString = "";
		switch(this.type){
			case COURSE:
				objString = "A class called " + this.name + " taking place on: ";
				break;
			case STUDY:
				objString = "A Study time called " + this.name + " taking place on: ";
				break;
			case ASSIGN:
				objString = "An Assignment called " + this.name + "due on: ";
				break;
			case EXAM:
				objString = "An exam called " + this.name + " taking place on: ";
				break;
			case EXTRACURR:
				objString = "An extracurricular called " + this.name + " taking place on: ";
				break;
		}
		objString = objString + getDaysString() + " at " + getTimeString();
		return objString;
	}

	public int getTime(){
		return this.time;
	}

	public String getTimeString(){
		int minutes = this.time%60;
		int hours = (this.time/60)%60;
		String timeString = Integer.toString(hours) + ":" + Integer.toString(minutes);
		return timeString;
	}

	public int getTimeFromString(String timeString){
		String[] digits = timeString.split(":");
		int hours;
		int minutes;
		//digits should only be 2 elements large if the string was formatted correctly
		try {
			hours = Integer.valueOf(digits[0]);
		} catch (NumberFormatException e) {
			hours = 0;
		}
		try {
			minutes = Integer.valueOf(digits[1]);
		} catch (NumberFormatException e) {
			minutes = 0;
		}
		return hours*60+minutes;
	}
}