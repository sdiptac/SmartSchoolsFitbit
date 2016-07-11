package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.json.*;

public class DailyActivity {
	public static void getActivity(String uId,String aToken) throws Exception{
		String simpledate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	    
		URL url = new URL("https://api.fitbit.com/1/user/" + uId + "/activities/date/" + simpledate + ".json");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("GET");
        
        connection.setRequestProperty("Authorization", " Bearer " + aToken);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        String data = in.readLine();
        System.out.println(data);
        final JSONObject object = new JSONObject(data);
        final JSONObject summary = object.getJSONObject("summary");
        
        System.out.println(summary.getInt("activityCalories"));
        System.out.println(summary.getInt("steps"));
        System.out.println(summary.getInt("floors"));
        
       
         
        in.close();
	}
	
	public static void getResting(String uId,String aToken) throws Exception{
		//String simpledate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	    
		URL url = new URL("https://api.fitbit.com/1/user/" + uId +"/activities/heart/date/today/1d.json");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("GET");
        
        connection.setRequestProperty("Authorization", " Bearer " + aToken);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        String data = in.readLine();
        System.out.println(data);
        final JSONObject object = new JSONObject(data);
        final JSONArray heart = object.getJSONArray("activities-heart");
        final JSONObject heartObject = heart.getJSONObject(0);
        
        System.out.println(heartObject.getJSONObject("value").getInt("restingHeartRate"));
        
        in.close();
	}
}
