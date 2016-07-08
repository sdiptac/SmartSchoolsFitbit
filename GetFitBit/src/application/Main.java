package application;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

	private final static ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);
	private final static int REFRESH_TIME = 5;
	
	public static void main(String[] args) {
		
		Runnable getUserRunnable = () -> {
			String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0UEJYQlEiLCJhdWQiOiIyMjdWNTMiLCJpc3MiOiJGaXRiaXQiLCJ0eXAiOiJhY2Nlc3NfdG9rZW4iLCJzY29wZXMiOiJ3aHIgd251dCB3cHJvIHdzbGUgd3dlaSB3c29jIHdzZXQgd2FjdCB3bG9jIiwiZXhwIjoxNDk5NTIzOTI4LCJpYXQiOjE0Njc5ODc5Mjh9.W74YpdpGl9Nw66uwTWeZARtAE_3cR6UNNawtKKbKXOs";
			String id = "4PBXBQ";
			try {
				URLReader.getUserProfile(id,token);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
		
		SCHEDULER.scheduleAtFixedRate(getUserRunnable, 0, REFRESH_TIME, TimeUnit.SECONDS);
	}
}
