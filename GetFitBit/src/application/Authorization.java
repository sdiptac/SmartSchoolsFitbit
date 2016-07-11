package application;


import java.sql.*;
import java.util.ArrayList;


public class Authorization {

	
	public static ArrayList<String[]> getAuthorization(){
		
		ArrayList<String[]> IDs = new ArrayList<String[]>();
		try{
			final String query = "select accessToken,fitbitID from user where accessToken is not null and fitbitID is not null";
			Connector.connect();
			PreparedStatement statement= Connector.connection.prepareStatement(query);
			ResultSet resultset = statement.executeQuery();
			
            	if(!resultset.next()){
            		System.out.println("No such user found");
            	}else {
            		do{
            			String[] row = new String[2];
            			row[0] = resultset.getString("accessToken");
            			row[1] = resultset.getString("fitbitID");
            			IDs.add(row);
            		}while(resultset.next());
            	}
            	Connector.disconnect();
		}catch(Exception e){
				System.out.println(e.toString());
        }
		
		return IDs;
	}
}
