package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;

public class Sleep {
	public static void getSleep(String uId,String aToken) throws Exception{
		
		String simpledate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	    String timeStamp = null;
	    int restlessCount = 0;
	    int restlessDuration = 0;
	    int minutesAsleep = 0;
	    int sleepRecords = 0;
	    int timeInBed = 0;
		URL url = new URL("https://api.fitbit.com/1/user/"+ uId + "/sleep/date/" + simpledate + ".json");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("GET");
        
        connection.setRequestProperty("Authorization", " Bearer " + aToken);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        String data = in.readLine();
        System.out.println(data);
        final JSONArray sleep = new JSONObject(data).getJSONArray("sleep");
        for (int i = 0; i < sleep.length(); ++i){
        	JSONObject allSleep = sleep.getJSONObject(i);
        	String isMainSleep	= allSleep.getString("isMainSleep");
        	if(isMainSleep.equals("true")){
        		timeStamp = allSleep.getString("startTime");
        		restlessCount = allSleep.getInt("restlessCount");
        		restlessDuration = allSleep.getInt("restlessCount");
        	}
        }
        final JSONObject sleepSummary = new JSONObject(data).getJSONObject("summary");
        minutesAsleep = sleepSummary.getInt("totalMinutesAsleep");
        sleepRecords = sleepSummary.getInt("totalSleepRecords");
        timeInBed = sleepSummary.getInt("totalTimeInBed");
        System.out.println(timeStamp);
        System.out.println(restlessCount);
        System.out.println(restlessDuration);
        System.out.println(minutesAsleep);
        System.out.println(sleepRecords);
        System.out.println(timeInBed);
        in.close();
	}
}
