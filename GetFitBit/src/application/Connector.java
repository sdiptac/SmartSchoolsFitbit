package application;

import java.sql.*;

public class Connector {
	private static final String url = "jdbc:mysql://lusmartschools.cymnuwsxway1.us-east-1.rds.amazonaws.com/ssdb";
	private static final String user = "admin";
	private static final String password = "coolsmartschools";
	public static Connection connection = null;
	
	public static boolean connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url,user,password);
			return true;
		} catch(Exception e){
			System.out.println(e.toString());
			return false;
		}
	}
	
	public static void disconnect(){
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.toString());
			
		}
	}
}

