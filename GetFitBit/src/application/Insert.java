package application;

import java.util.ArrayList;

public class Insert {
	public static void insertDailyActivity(String uId, ArrayList<Activity> activityArray){
		int calories = 0;
		String date = null;
		int floors = 0;
		int steps = 0;
		int restingHeartRate = 0;
		final String query = "insert into dailyActivity values "
		
		for (Activity e : activityArray){
			calories = e.getActivityCalories();
			date = e.getDate();
			floors = e.getFloors();
			steps = e.getSteps();
			restingHeartRate = e.getRestingHeartRate();
		}
	}
}
