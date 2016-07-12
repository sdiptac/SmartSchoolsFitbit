package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceInfo {
	public static int getDeviceId(String userId, Device device){
		int deviceID = 0;
		Connector.connect();
		try{
			final String query = "select deviceID from user_device natural join device where userId = ? and typeOfDevice = ?";
			PreparedStatement statement= Connector.connection.prepareStatement(query);
			
			statement.setString(1, userId);
			statement.setString(2, device.toString());
			
			ResultSet resultset = statement.executeQuery();
			if(!resultset.next()){
        		System.out.println("No linked device found");
			}else{
				deviceID = resultset.getInt("deviceID");
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		Connector.disconnect();
		return deviceID;
	}
}
