package application;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

	private final static ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);
	private final static int REFRESH_TIME = 5;
	
	public static void main(String[] args) {
		
		Runnable getUserRunnable = () -> {
			System.out.println("something");
			ArrayList<String[]> auth = Authorization.getAuthorization();
			String id = auth.get(0)[3];
			String token = auth.get(0)[2];
			System.out.println(auth.size());
			try {
				UserProfile.getUserProfile(id, token);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
		
		
		SCHEDULER.scheduleAtFixedRate(getUserRunnable, 0, REFRESH_TIME, TimeUnit.SECONDS);
	}
}
