package application;

import java.io.*;
import java.net.*;


public class UserProfile {
	    public static void getUserProfile(String uId,String aToken) throws Exception {

	        URL url = new URL("https://api.fitbit.com/1/user/" + uId + "/profile.json");
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setRequestMethod("GET");
	        
	        connection.setRequestProperty("Authorization", " Bearer " + aToken);
	        
	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

	        String inputLine;
	        while ((inputLine = in.readLine()) != null)
	            System.out.println(inputLine);
	        in.close();
	    }
	}
