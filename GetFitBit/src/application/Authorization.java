package application;


import java.sql.*;
import java.util.ArrayList;


public class Authorization {

	public static ArrayList<UserProfile> getAuthorization(){
		
		ArrayList<UserProfile> IDs = new ArrayList<UserProfile>();
		try{
			final String query = "select accessToken, fitbitID, userID from user natural join user_device natural join device where accessToken is not null and fitbitID is not null and typeOfDevice = 'fitbit'";
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
	
	public static Date getMostRecentSync(String userID){
		final String query = "select accessToken, fitbitID, userID, dayOfActivity from user natural join user_device natural join device natural join dailyActivity where accessToken is not null and fitbitID is not null and typeOfDevice = 'fitbit' and userID = ?";
		Connector.connect();
		PreparedStatement statement;
		try {
			statement = Connector.connection.prepareStatement(query);
			statement.setString(1, userID);
			ResultSet resultset = statement.executeQuery();
			if(!resultset.next()){
        		return null;
        	}else{
        		
        	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Connector.disconnect();
		return null;
	}
}
