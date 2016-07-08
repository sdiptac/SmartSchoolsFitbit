package application;

import java.io.*;
import java.net.*;
import java.util.Base64;


public class URLReader {
	    public static void getData(String aToken) throws Exception {

	        URL url = new URL("https://api.fitbit.com/1/user/4PBXBQ/profile.json");
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setRequestMethod("GET");
	        String encodeToken = Base64.getEncoder().encodeToString(aToken.getBytes("UTF-8"));
	        connection.setRequestProperty("Authorization", " Bearer " + encodeToken);
	        
	

	     
			
	        
	        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

	        String inputLine;
	        while ((inputLine = in.readLine()) != null)
	            System.out.println(inputLine);
	        in.close();
	    }
	}
