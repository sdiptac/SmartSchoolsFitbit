package application;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

	private final static ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);
	private final static int REFRESH_TIME = 1;
	
	public static void main(String[] args) {
		
		Runnable getUserRunnable = () -> {
			ArrayList<String[]> auth = Authorization.getAuthorization();
			String id = auth.get(0)[1];
			String token = auth.get(0)[0];
			try {
				DailyActivity.getActivity(id, token);
				DailyActivity.getResting(id, token);
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		};
		
		
		SCHEDULER.scheduleAtFixedRate(getUserRunnable, 0, REFRESH_TIME, TimeUnit.HOURS);
	}
}
