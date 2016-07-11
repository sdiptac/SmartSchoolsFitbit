package application;


import java.sql.*;
import java.util.ArrayList;


public class Authorization {

	public static ArrayList<UserProfile> getAuthorization(){
		
		ArrayList<UserProfile> IDs = new ArrayList<UserProfile>();
		try{
			final String query = "select accessToken,fitbitID, userID from user where accessToken is not null and fitbitID is not null";
			Connector.connect();
			PreparedStatement statement= Connector.connection.prepareStatement(query);
			ResultSet resultset = statement.executeQuery();
			
        	if(!resultset.next()){
        		System.out.println("No such user found");
        	}else {
        		do{
        			UserProfile user = new UserProfile(resultset.getString("fitbitID"), resultset.getString("accessToken"), resultset.getString("userID"));
        			IDs.add(user);
        		}while(resultset.next());
        	}
        	Connector.disconnect();
		}catch(Exception e){
			System.out.println(e.toString());
        }
		
		return IDs;
	}
}
