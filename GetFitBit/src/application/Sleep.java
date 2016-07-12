package application;

public class Sleep {
   private String timeStamp = null;
   private int restlessCount = 0;
   private int restlessDuration = 0;
   private int minutesAsleep = 0;
   private int sleepRecords = 0;
   private int timeInBed = 0;
   
   public Sleep(String timeStamp, int restlessCount, int restlessDuration, int minutesAsleep, int sleepRecords, int timeInBed){
	   this.timeStamp = timeStamp;
	   this.restlessCount = restlessCount;
	   this.restlessDuration = restlessDuration;
	   this.minutesAsleep = minutesAsleep;
	   this.sleepRecords = sleepRecords;
	   this.timeInBed = timeInBed;
   }

	public String getTimeStamp() {
		return timeStamp;
	}

	public int getRestlessCount() {
		return restlessCount;
	}

	public int getRestlessDuration() {
		return restlessDuration;
	}

	public int getMinutesAsleep() {
		return minutesAsleep;
	}

	public int getSleepRecords() {
		return sleepRecords;
	}

	public int getTimeInBed() {
		return timeInBed;
	}
	
	public String toString(){
		return String.format("TimeStamp: %s, RestlessCount: %d, RestlessDuration: %d, MinutesAsleep: %d, SleepRecords: %d, timeInBed: %d", timeStamp, restlessCount, restlessDuration, minutesAsleep, sleepRecords, timeInBed);
	}
}
