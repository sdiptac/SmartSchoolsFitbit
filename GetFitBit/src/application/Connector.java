package application;

import java.sql.*;

public class Connector {
	private static final String url = ""; //insert url
	private static final String user = "admin";
	private static final String password = ""; //insert password
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

