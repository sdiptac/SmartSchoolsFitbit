package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

public class HRPerMinute extends GetInfoFromFitbit{
	
	public static ArrayList<ArrayList<HeartRate>> getHRs(String uId,String aToken, Date startDate, Date endDate){
		LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		ArrayList<ArrayList<HeartRate>> HRs = new ArrayList<ArrayList<HeartRate>>();
		
		ArrayList<String> dateRange = new ArrayList<String>();

		
		for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
			dateRange.add(date.format(Main.DATE_TIME_FORMATTER));
		}
		
		dateRange.stream().forEach(date -> {
			try{    
				HRs.add(getHR(uId, aToken, date));
			}catch(Exception e){
		    }
		});
		return HRs;
	}
	
	public static ArrayList<HeartRate> getHR(String uId,String aToken, String date) throws Exception{    
		URL url = new URL("https://api.fitbit.com/1/user/" + uId + "/activities/heart/date/" + date + "/1d/1min/time/00:00/23:59.json");
		ArrayList<HeartRate> HRPerDay= new ArrayList<>();
		BufferedReader in = new BufferedReader(new InputStreamReader(getHttpURLConnectionFromURL(url, aToken).getInputStream()));
        
        String data = in.readLine();
        in.close();

        final JSONObject intradayHeart = new JSONObject(data).getJSONObject("activities-heart-intraday");
        final JSONArray HRPM = intradayHeart.getJSONArray("dataset");
        String timeStamp = null;
	    int heartRate = 0;
	    
	    
        for (int i = 0; i < HRPM.length(); ++i){
        	JSONObject allHRPM = HRPM.getJSONObject(i);
        	timeStamp = allHRPM.getString("time");
        	heartRate = allHRPM.getInt("value");
        	HRPerDay.add(new HeartRate(date + " " + timeStamp,heartRate));
        }

    	return HRPerDay;
       
	}
}