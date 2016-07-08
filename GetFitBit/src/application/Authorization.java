package application;


import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class Authorization {

	
	public static ArrayList<String[]> getAuthorization(){
		Statement statement = null;
		ResultSet resultset = null;
		ArrayList<String[]> IDs = new ArrayList<String[]>();
		try{
			final String query = "select userID,email,accessToken,fitbitID from user where accessToken is not null and fitbitID is not null";
			Connector.connect();
			
			statement = Connector.connection.createStatement();
			resultset = statement.executeQuery(query);
            	if(!resultset.next()){
            		System.out.println("No such user found");
            	}else {
            		while(resultset.next()){
            			String[] row = new String[4];
            			row[0] = resultset.getString("userID");
            			row[1] = resultset.getString("email");
            			row[2] = resultset.getString("accessToken");
            			row[3] = resultset.getString("fitbitID");
            			IDs.add(row);
            		}
            	}
            	Connector.disconnect();
		}catch(Exception e){
				System.out.println(e.toString());
        }
		
		return IDs;
	}
}
