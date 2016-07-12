package application;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Insert {
	public static void insertDailyActivity(int deviceId, ArrayList<Activity> activityArray){
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
				Connector.disconnect();
				
			} catch (SQLException ex) {
				System.out.println(ex.toString());
			}
		}
	}
	public static void insertSleep(int deviceId, ArrayList<Sleep> sleepArray){
		String timeStamp = null;
		int restlessCount = 0;
		int restlessDuration = 0;
		int minutesAsleep = 0;
		int sleepRecords = 0;
		int timeInBed = 0;
		for (Sleep e : sleepArray){
			timeStamp = e.getTimeStamp();
			restlessCount = e.getRestlessCount();
			restlessDuration = e.getRestlessDuration();
			minutesAsleep = e.getMinutesAsleep();
			sleepRecords = e.getSleepRecords();
			timeInBed = e.getTimeInBed();
		
			try{
				Connector.connect();
				final String query = "insert into sleep values (?,?,?,?,?,?,?)";
				PreparedStatement statement= Connector.connection.prepareStatement(query);
				statement.setInt(1, deviceId);
				statement.setString(2, timeStamp);
				statement.setInt(3, minutesAsleep);
				statement.setInt(4, timeInBed);
				statement.setInt(5, restlessCount);
				statement.setInt(6, restlessDuration);
				statement.setInt(7, sleepRecords);
				statement.executeUpdate();
				Connector.disconnect();
			} catch (SQLException ex) {
				System.out.println(ex.toString());
			}
		}
	}
	public static void insertHRPM(int deviceId, ArrayList<ArrayList<HeartRate>> hrPerDay){
		String timeStamp = null;
		int hrPerMin = 0;
		for (ArrayList<HeartRate> a : hrPerDay){
			for (HeartRate h : a){
				timeStamp = h.getTimeStamp();
				hrPerMin = h.getAvgHeartRate();
				try{
					Connector.connect();
					final String query = "insert into heartRate values (?,?,?)";
					PreparedStatement statement= Connector.connection.prepareStatement(query);
					statement.setInt(1, deviceId);
					statement.setString(2, timeStamp);
					statement.setInt(3, hrPerMin);
					statement.executeUpdate();
					Connector.disconnect();
				} catch (SQLException ex) {
					System.out.println(ex.toString());
				}
			}
			
		}
	}
}
