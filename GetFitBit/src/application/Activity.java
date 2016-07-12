package application;

public class Activity{
	private String date;
	private int activityCalories;
	private int steps;
	private int floors;
	private int restingHeartRate = 0;
	
	public Activity(String date, int activityCalories, int steps, int floors, int restingHeartRate){
		this.date = date;
		this.activityCalories = activityCalories;
		this.steps = steps;
		this.floors = floors;
		this.restingHeartRate = restingHeartRate;
	}
	
	public int getActivityCalories() {
		return activityCalories;
	}
	public int getSteps() {
		return steps;
	}
	public int getFloors() {
		return floors;
	}
	
	public int getRestingHeartRate() {
		return restingHeartRate;
	}
	
	public String getDate() {
		return date;
	}
	
	public String toString(){
		return String.format("ActivityCalories: %d, Steps: %d, Floors: %d, RestingHeartRate: %d", activityCalories, steps, floors, restingHeartRate);
	}
}
