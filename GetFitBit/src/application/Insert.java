package application;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Insert {
	public static void insertDailyActivity(int deviceId, ArrayList<Activity> activityArray){
		Connector.connect();
		for (Activity e : activityArray){
			int calories = e.getActivityCalories();
			String date = e.getDate();
			int floors = e.getFloors();
			int steps = e.getSteps();
			int restingHeartRate = e.getRestingHeartRate();
		
			try{
				final String query = "insert into dailyActivity(deviceID, calories, stepCount, floors, restingHR, dayOfActivity) values (?,?,?,?,?,?)";
				PreparedStatement statement= Connector.connection.prepareStatement(query);
				statement.setInt(1, deviceId);
				statement.setInt(2, calories);
				statement.setInt(3, steps);
				statement.setInt(4, floors);
				statement.setInt(5, restingHeartRate);
				statement.setString(6, date);
				if(statement.executeUpdate() == 1){
					System.out.println("uploaded");
				}else{
					System.out.println("could not upload");
				}
				
			} catch (SQLException ex) {
				System.out.println("insertDailyActivity() " + ex.toString());
			}
		}
		Connector.disconnect();
	}
	public static void insertSleep(int deviceId, ArrayList<Sleep> sleepArray){		
		Connector.connect();
		System.out.println("size "+sleepArray.size());
		
		for (Sleep e : sleepArray){
			String timeStamp = e.getTimeStamp();
			int restlessCount = e.getRestlessCount();
			int restlessDuration = e.getRestlessDuration();
			int minutesAsleep = e.getMinutesAsleep();
			int sleepRecords = e.getSleepRecords();
			int timeInBed = e.getTimeInBed();
		
			try{
				final String query = "insert into sleep(deviceID, startTimeOfSleep, dayOfSleep, sleepDuration, inBedDuration, restlessCount, restlessDuration, sleepRecords) values (?,?,?,?,?,?,?,?)";
				PreparedStatement statement= Connector.connection.prepareStatement(query);
				statement.setInt(1, deviceId);
				statement.setString(2, timeStamp);
				statement.setString(3, e.getDate());
				statement.setInt(4, minutesAsleep);
				statement.setInt(5, timeInBed);
				statement.setInt(6, restlessCount);
				statement.setInt(7, restlessDuration);
				statement.setInt(8, sleepRecords);
				statement.executeUpdate();
			} catch (SQLException ex) {
				System.out.println("insertSleep() " + ex.toString());
			}
		}
		Connector.disconnect();
	}
	
	public static void insertHRPM(int deviceId, ArrayList<ArrayList<HeartRate>> hrPerDay){
		Connector.connect();
		for (ArrayList<HeartRate> a : hrPerDay){
			for (HeartRate h : a){
				String timeStamp = h.getTimeStamp();
				int hrPerMin = h.getAvgHeartRate();
				try{
					final String query = "insert into heartRate(deviceID, timeOfHR, BPM, dayOfHR) values (?,?,?,?)";
					PreparedStatement statement= Connector.connection.prepareStatement(query);
					statement.setInt(1, deviceId);
					statement.setString(2, timeStamp);
					statement.setInt(3, hrPerMin);
					statement.setString(4, h.getDate());
					statement.executeUpdate();
				} catch (SQLException ex) {
					System.out.println("insertHRPM() " +ex.toString());
				}
			}	
		}
		Connector.disconnect();
	}
}
