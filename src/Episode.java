// Gilbert Wang
// November 21st, 2022
// Episode
// This program creates episode objects.
public class Episode implements Comparable<Episode>{
	private int EpisodeNum;
	private String EpisodeTitle;
	private int EpisodeSeason;
	private Boolean watched;
	private Time time;
	
	// Constructor
		// Parameters
			//int EpisodeNum: number of the episode
			//String EpisodeTitle: title of the episode
			//int EpisodeSeason: season of the episode
			//Boolean watched: if you have watched this episode
			//Time time: the time of the episode
	public Episode(int EpisodeNum, String EpisodeTitle, int EpisodeSeason, Boolean watched, Time time) {
		this.EpisodeNum =EpisodeNum;
		this.EpisodeTitle = EpisodeTitle;
		this.EpisodeSeason = EpisodeSeason;
		this.watched = watched;
		this.time = time;
	}
	
	// compareTo
		// Description: compareTo method, compares by number
		// Parameters: Episode p: the episode that you are comparing
	public int compareTo(Episode p) {
		if(this.EpisodeSeason!=p.EpisodeSeason) {
			return this.EpisodeSeason-p.EpisodeSeason;
		}
		return this.EpisodeNum-p.EpisodeNum;
	}
	
	// ToString
		// Description: toString Method
	public String toString(){
		return String.format("Title: %s%nSeason#: %d%nEpisode#: %d%nWatched: %s%nTime: %s%n", 
			EpisodeTitle, EpisodeSeason, EpisodeNum,watched+"",time.toString());
	}
	// getters
	public int returnTime() {
		return this.time.getTotalSeconds();
	}
	public String getTitle() {
		return this.EpisodeTitle;
	}
	public int getNumber() {
		return this.EpisodeNum;
	}
	public int getTime() {
		return this.time.getTotalSeconds();
	}
	public int getSeason() {
		return this.EpisodeSeason;
	}
	public boolean getWatched() {
		return this.watched;
	}
	// setters
	public void setWatched() {
		this.watched = true;
	}


}
