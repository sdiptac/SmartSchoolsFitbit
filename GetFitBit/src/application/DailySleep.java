package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

public class DailySleep extends GetInfoFromFitbit{
	
	public static ArrayList<Sleep> getSleeps(String fitbitId,String aToken, Date startDate, Date endDate){
		LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		ArrayList<Sleep> sleeps = new ArrayList<Sleep>();
		
		ArrayList<String> dateRange = new ArrayList<String>();

		for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
			dateRange.add(date.format(Main.DATE_TIME_FORMATTER));
		}
		
		dateRange.stream().forEach(date -> {
			try{    
				Sleep sleep = getSleep(fitbitId, aToken, date);
				if(sleep != null){
					sleeps.add(sleep);					
				}
			}catch(Exception e){
		    }
		});
		return sleeps;
	}
	
	public static Sleep getSleep(String fitbitId,String aToken, String date) throws Exception{    
		URL url = new URL("https://api.fitbit.com/1/user/"+ fitbitId + "/sleep/date/" + date + ".json");
       
		BufferedReader in = new BufferedReader(new InputStreamReader(getHttpURLConnectionFromURL(url, aToken).getInputStream()));
        
        String data = in.readLine();
        in.close();
        System.out.println(data);
        final JSONArray sleep = new JSONObject(data).getJSONArray("sleep");
        
        String timeStamp = null;
	    int restlessCount = 0;
	    int restlessDuration = 0;
	    
        for (int i = 0; i < sleep.length(); ++i){
        	JSONObject allSleep = sleep.getJSONObject(i);
        	Boolean isMainSleep	= allSleep.getBoolean("isMainSleep");
        	
        	if(isMainSleep){
        		timeStamp = allSleep.getString("startTime");
        		restlessCount = allSleep.getInt("restlessCount");
        		restlessDuration = allSleep.getInt("restlessCount");
        		break;
        	}
        }

        final JSONObject sleepSummary = new JSONObject(data).getJSONObject("summary");
        
        if(timeStamp != null && !timeStamp.isEmpty()){
        	Date sqlDate = Main.DATE_TO_SQL_TIMESTAMP_FORMATTER.parse(timeStamp.replace('T', ' '));
        	return new Sleep(Main.DATE_TO_SQL_TIMESTAMP_FORMATTER.format(sqlDate), date, restlessCount, restlessDuration, sleepSummary.getInt("totalMinutesAsleep"), sleepSummary.getInt("totalSleepRecords"), sleepSummary.getInt("totalTimeInBed"));        	

        }else{
        	return null;
        } 
	}
}
