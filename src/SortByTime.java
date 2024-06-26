// Gilbert Wang
// November 21st, 2022
// SortByTime
// This is a compartor class that compares episodes by Time.
import java.util.Comparator;

public class SortByTime implements Comparator <Episode>{
	public int compare(Episode a1, Episode a2) {
		return a1.getTime()-a2.getTime();
	}
}