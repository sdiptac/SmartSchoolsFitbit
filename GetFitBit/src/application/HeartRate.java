package application;

public class HeartRate {
	private String timeStamp;
	private int avgHeartRate = 0;
	public HeartRate(String timeStamp, int avgHeartRate){
		this.timeStamp = timeStamp;
		this.avgHeartRate = avgHeartRate;
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
}
