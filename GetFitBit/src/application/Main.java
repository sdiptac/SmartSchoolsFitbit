package application;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

	private final static ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);
	private final static int REFRESH_TIME = 1;
	
	public final static SimpleDateFormat DATE_TO_SQL_TIMESTAMP_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public final static SimpleDateFormat SIMPLE_DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
	public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	private final static String exampleStartDate = "2016-7-11";
	private final static String exampleEndDate = "2016-07-15";
	
	public static void main(String[] args) {
		
		Runnable getUserRunnable = () -> {
			ArrayList<UserProfile> users = Authorization.getAuthorization();
			users.stream().forEach(user -> {
				try {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, +1);
					Date dayAfter = cal.getTime();
					
					getAndPrintExampleActivities(user.getUserID(), user.getfitbitID(), user.getToken(), Authorization.getMostRecentSync(user.getUserID()), dayAfter);
					getAndPrintExampleSleeps(user.getUserID(), user.getfitbitID(), user.getToken(), Authorization.getMostRecentSync(user.getUserID()), dayAfter);		
					getAndPrintExampleHRs(user.getUserID(), user.getfitbitID(), user.getToken(), Authorization.getMostRecentSync(user.getUserID()), dayAfter);
				} catch (Exception e) {
				}
			});
		};
		
		SCHEDULER.scheduleAtFixedRate(getUserRunnable, 0, REFRESH_TIME, TimeUnit.HOURS);
	}
	
	public static void getAndPrintExampleActivities(String userID, String fitbitID, String aToken, Date startDate, Date endDate){
		ArrayList<Activity> example = DailyActivity.getActivities(fitbitID, aToken, startDate, endDate);
		
		Insert.insertDailyActivity(DeviceInfo.getDeviceId(userID, Device.fitbit), example);
	}
	
	public static void getAndPrintExampleSleeps(String userID, String fitbitID, String aToken, Date startDate, Date endDate){
		ArrayList<Sleep> example = DailySleep.getSleeps(fitbitID, aToken, startDate, endDate);
		
		Insert.insertSleep(DeviceInfo.getDeviceId(userID, Device.fitbit), example);
	}
	public static void getAndPrintExampleHRs(String userID, String fitbitID, String aToken, Date startDate, Date endDate){
		ArrayList<ArrayList<HeartRate>> example = HRPerMinute.getHRs(fitbitID, aToken, startDate, endDate);
		
		Insert.insertHRPM(DeviceInfo.getDeviceId(userID, Device.fitbit), example);
	}
}
