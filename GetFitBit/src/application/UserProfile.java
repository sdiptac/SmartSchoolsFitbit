package application;

public class UserProfile {
	private String fitbitID;
	private String token;
	private String userID;
	
	public UserProfile(String fitbitID, String token, String userID){
		this.fitbitID = fitbitID;
		this.token = token;
		this.userID = userID;
	}
	
	public String getID(){
		return this.fitbitID;
	}
	
	public String getToken(){
		return this.token;
	}
	
	public String getUserID(){
		return this.userID;
	}
}