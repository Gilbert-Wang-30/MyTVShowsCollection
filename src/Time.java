// Gilbert Wang
// November 21st, 2022
// Time
// This program creates Time objects.
public class Time implements Comparable<Time>{
	private int hour;
	private int minute;
	private int seconds;
	private int totalSeconds;
	private String totalTime;
	
	// Constructor
		// Parameter: time:the time in correct format
	public Time(String time) {
		totalTime = time;
		this.hour = Integer.parseInt(time.substring(0,2));
		this.minute = Integer.parseInt(time.substring(3,5));
		this.seconds = Integer.parseInt(time.substring(6,8));
		totalSeconds = hour*3600+minute*60+seconds;
	}
	
	// compareTo
		// Description: compareTo method, compares by time
		// Parameters: Time p: the time you are comparing
	public int compareTo(Time p) {
		return this.totalSeconds-p.totalSeconds;
	}
	
	// getter
	public int getTotalSeconds() {
		return this.totalSeconds;
	}
	
	// ToString
		// Description: toString Method
		// Return a string
	public String toString() {
		return this.totalTime;
	}
}
