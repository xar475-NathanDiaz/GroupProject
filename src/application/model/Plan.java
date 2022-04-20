package application.model;

class Plan{
	/**
	 * Enum to specify what type of plan this is.
	 * The enum will be able to change how the plan is displayed
	 * and how it is scheduled like if it has multiple times or days
	 */
	enum planType{COURSE,STUDY,ASSIGN,EXAM,EXTRACURR};

	String name;
	int days;
	int time;
	planType type;

	public Plan(String name, int days, int time, planType type){
		this.name = name;
		this.days = days;
		this.time = time;
		this.type = type;
	}
}