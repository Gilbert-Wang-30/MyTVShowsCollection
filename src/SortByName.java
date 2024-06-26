// Gilbert Wang
// November 21st, 2022
// SortByName
// This is a compartor class that compares episodes by Name.
import java.util.Comparator;

public class SortByName implements Comparator <Episode>{
	public int compare(Episode a1, Episode a2) {
		return a1.getTitle().compareToIgnoreCase(a2.getTitle());
	}
}