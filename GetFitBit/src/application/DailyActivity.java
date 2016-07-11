package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

public class DailyActivity {
	
	public static ArrayList<Activity> getActivities(String uId,String aToken, Date startDate, Date endDate){
		LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		ArrayList<Activity> activities = new ArrayList<Activity>();
		
		ArrayList<String> dateRange = new ArrayList<String>();
		
		for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
			dateRange.add(date.format(Main.DATE_TIME_FORMATTER));
		}
		
		dateRange.parallelStream().forEach(date -> {
			try{
				URL url = new URL("https://api.fitbit.com/1/user/" + uId + "/activities/date/" + date + ".json");
				
		        BufferedReader in = new BufferedReader(new InputStreamReader(getInputStreamFromURL(url, aToken).getInputStream()));
		        
		        String data = in.readLine();
		        System.out.println(data);
		        in.close();
	
		        final JSONObject object = new JSONObject(data);
		        final JSONObject summary = object.getJSONObject("summary");
		        
		        int restingHeartRate = 0;
		        
		        try{
		        	restingHeartRate = getRestingDaily(uId, aToken, date);
		        }catch(Exception e){
		        }
		        
		        activities.add(new Activity(summary.getInt("activityCalories"), summary.getInt("steps"), summary.getInt("floors"), restingHeartRate));
			}catch(Exception e){
		     }
		});
		
		return activities;
	}
	
	private static int getRestingDaily(String uId,String aToken, String date) throws Exception{
		URL url = new URL("https://api.fitbit.com/1/user/" + uId +"/activities/heart/date/" + date + "/1d.json");
    
        BufferedReader in = new BufferedReader(new InputStreamReader(getInputStreamFromURL(url, aToken).getInputStream()));
        
        String data = in.readLine();
        in.close();

        final JSONObject object = new JSONObject(data);
        final JSONArray heart = object.getJSONArray("activities-heart");
        final JSONObject heartObject = heart.getJSONObject(0);
        
        return heartObject.getJSONObject("value").getInt("restingHeartRate");
	}
	
	private static HttpURLConnection getInputStreamFromURL(URL url, String aToken) throws IOException{
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("GET");
        
        connection.setRequestProperty("Authorization", " Bearer " + aToken);
        return connection;
	}
}
