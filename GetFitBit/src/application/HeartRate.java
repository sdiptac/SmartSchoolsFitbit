package application;

public class HeartRate {
	private String timeStamp;
	private int avgHeartRate = 0;
	private String date;
	
	public HeartRate(String timeStamp, int avgHeartRate, String date){
		this.timeStamp = timeStamp;
		this.avgHeartRate = avgHeartRate;
		this.date = date;
	}
	
	public String getTimeStamp(){
		return timeStamp;
	}
	
	public int getAvgHeartRate(){
		return avgHeartRate;
	}
	
	public String toString(){
		return String.format("timeStamp: %d, avgHeartRate: %d", timeStamp, avgHeartRate);
	}
	
	public String getDate(){
		return date;
	} 
}
