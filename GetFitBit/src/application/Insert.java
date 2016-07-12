package application;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Insert {
	public static void insertDailyActivity(String uId, int deviceId, ArrayList<Activity> activityArray){
		int calories = 0;
		String date = null;
		int floors = 0;
		int steps = 0;
		int restingHeartRate = 0;
		
		for (Activity e : activityArray){
			calories = e.getActivityCalories();
			date = e.getDate();
			floors = e.getFloors();
			steps = e.getSteps();
			restingHeartRate = e.getRestingHeartRate();
		
			try{
				Connector.connect();
				final String query = "insert into dailyActivity values (?,?,?,?,?,?)";
				PreparedStatement statement= Connector.connection.prepareStatement(query);
				statement.setInt(1, deviceId);
				statement.setInt(2, calories);
				statement.setInt(3, steps);
				statement.setInt(4, floors);
				statement.setInt(5, restingHeartRate);
				statement.setString(6, date);
				statement.executeUpdate();
				
			} catch (SQLException ex) {
				System.out.println(ex.toString());
			}
		}
	}
}
