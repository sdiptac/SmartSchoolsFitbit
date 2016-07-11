package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
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
					ArrayList<Activity> example = DailyActivity.getActivities(user.getID(), user.getToken(), SIMPLE_DATE_FORMATTER.parse(exampleStartDate), SIMPLE_DATE_FORMATTER.parse(exampleEndDate));

					example.stream().forEach(a -> System.out.println(a.toString()));
				} catch (ParseException e) {
				}
			});
		};
		
		SCHEDULER.scheduleAtFixedRate(getUserRunnable, 0, REFRESH_TIME, TimeUnit.HOURS);
	}
}
