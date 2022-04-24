package application.model;

public class Plan{
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

	public Plan(){
	}

	public Plan(String name, int days, int time, planType type){
		this.name = name;
		this.days = days;
		this.time = time;
		this.type = type;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public static String dayNameToInitial(String day){
		if(day.equals("Monday")){
			return "M";
		}else if(day.equals("Tuesday")){
			return "T";
		}else if(day.equals("Wednesday")){
			return "W";
		}else if(day.equals("Thursday")){
			return "R";
		}else if(day.equals("Friday")){
			return "F";
		}else if(day.equals("Saturday")){
			return "S";
		}else{//default state, falls back to Sunday
			return "U";
		}
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
	 * @param dayString A string representing days of the week that was read from a schedule file
	 * @return A int that represents days that will be stored in 
	 */
	public void setDaysFromString(String dayString){
		int dayNum = 0;
		for (char i : dayString.toCharArray()) {
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
		this.days = dayNum;
	}

	public int getTime(){
		return this.time;
	}

	public String getTimeString(){
		int minutes = this.time%60;
		int hours = (this.time/60)%60;
		//this ensures that the minutes place is always 2 digits
		String leadingZero = (minutes<10)?"0":"";
		String timeString = Integer.toString(hours) + ":" + leadingZero + Integer.toString(minutes);
		return timeString;
	}

	public void setTime(int time){
		this.time = time;
	}

	public void setTimeFromString(String timeString){
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
		this.time =  hours*60+minutes;
	}

	/**
	 * @return the string representation of the plan type for writing to a file
	 */
	public String getPlanTypeAsString(){
		switch(this.type){
			case STUDY:
				return "Study time";
			case ASSIGN:
				return "Assignment";
			case EXAM:
				return "Exam";
			case EXTRACURR:
				return "Extracurricular";
			case COURSE:
			default:
				return "Class";
		}
	}

	/**
	 * To be used when reading from a file or from the button labels on PlannerController.
	 * The model is set up so that there is no difference between the typeStrings written to the schedule files and the button labels.
	 * This allows for as little error as possible.
	 * @param typeString a string representation of the plan type
	 */
	public void setPlanFromString(String typeString){
		if(typeString.equals("Study time")){
			this.type = planType.STUDY;
		}else if(typeString.equals("Assignment")){
			this.type = planType.ASSIGN;
		}else if(typeString.equals("Exam")){
			this.type = planType.EXAM;
		}else if(typeString.equals("Extracurricular")){
			this.type = planType.EXTRACURR;
		}else{//this is the default case, whether the parameter is "Class" or not
			this.type = planType.COURSE;
		}
	}

	/**
	 * Returns a string for this object in csv format for writing to schedule files
	 */
	@Override
	public String toString(){
		return getPlanTypeAsString()+","+getName()+","+getDaysString()+","+Integer.toString(getTime())+"\n";
	}
}