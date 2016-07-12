package application;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetInfoFromFitbit {
	protected static HttpURLConnection getHttpURLConnectionFromURL(URL url, String aToken) throws IOException{
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("GET");
        
        connection.setRequestProperty("Authorization", " Bearer " + aToken);
        return connection;
	}
}
