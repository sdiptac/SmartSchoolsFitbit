package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

	private final static ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);
	private final static int REFRESH_TIME = 1;
	
	public final static SimpleDateFormat SIMPLE_DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
	public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	private final static String exampleStartDate = "2016-07-09";
	private final static String exampleEndDate = "2016-07-11";
	
	public static void main(String[] args) {
		
		Runnable getUserRunnable = () -> {
			ArrayList<UserProfile> auth = Authorization.getAuthorization();
			auth.stream().forEach(user -> {
				try {
					//getAndPrintExampleActivities(user.getID(), user.getToken(), SIMPLE_DATE_FORMATTER.parse(exampleStartDate), SIMPLE_DATE_FORMATTER.parse(exampleEndDate));
					getAndPrintExampleSleeps(user.getID(), user.getToken(), SIMPLE_DATE_FORMATTER.parse(exampleStartDate), SIMPLE_DATE_FORMATTER.parse(exampleEndDate));
				} catch (ParseException e) {
				}
			});
		};
		
		SCHEDULER.scheduleAtFixedRate(getUserRunnable, 0, REFRESH_TIME, TimeUnit.HOURS);
	}
	
	public static void getAndPrintExampleActivities(String uID, String aToken, Date startDate, Date endDate){
		ArrayList<Activity> example = DailyActivity.getActivities(uID, aToken, startDate, endDate);
		example.stream().forEach(a -> System.out.println(a.toString()));
	}
	
	public static void getAndPrintExampleSleeps(String uID, String aToken, Date startDate, Date endDate){
		ArrayList<Sleep> example = DailySleep.getSleeps(uID, aToken, startDate, endDate);
		example.stream().forEach(a -> System.out.println(a.toString()));
	}
}
