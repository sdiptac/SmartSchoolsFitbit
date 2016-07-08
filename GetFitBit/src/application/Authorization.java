package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Authorization {

	
	public static ArrayList<String> getUserIDs(){

		List<String> IDs = new ArrayList<String>();
		try{
			final String query = "";
			Connector.connect();
			
			PreparedStatement prepare = Connector.connection.prepareStatement(query);
			prepare.setString(1, "%" + firstname + "%");
			prepare.setString(2, "%" + lastname + "%");
			ResultSet resultset = prepare.executeQuery();
            	if(!resultset.next()){
            		System.out.println("No such user found");
            	}else {
            		while(resultset.next()){
            			String[] row = new String[4];
            			row[0] = resultset.getString("userID");
            			row[1] = resultset.getString("first_name");
            			row[2] = resultset.getString("last_name");
            			row[3] = resultset.getString("email");
            			info.add(row);
            		}
            	}
            	Connector.disconnect();
		}catch(Exception e){
            	System.out.println("Database Error");
            	return info;
		}
	}
}
