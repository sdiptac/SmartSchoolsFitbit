package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceInfo {
	public static int getDeviceId(String userId, Device device){
		int deviceID = 0;
		Connector.connect();
		try{
			final String query = "select deviceID from user_device natural join device where userID = ? and typeOfDevice = ?";
			PreparedStatement statement= Connector.connection.prepareStatement(query);
			
			statement.setString(1, userId);
			statement.setString(2, device.toString());
			
			ResultSet resultset = statement.executeQuery();
			resultset.next();
			deviceID = resultset.getInt("deviceID");
			System.out.println(deviceID);
		} catch (SQLException ex) {
			System.out.println("class device info " + ex.toString());
		}
		Connector.disconnect();
		
		return deviceID;
		
	}
}
