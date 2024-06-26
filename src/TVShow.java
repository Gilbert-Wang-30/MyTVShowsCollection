// Gilbert Wang
// November 21st, 2022
// TVShow
// This program creates TV show objects.
import java.util.*;

public class TVShow implements Comparable<TVShow>{
	private String title;
	private String Genre;
	private int Season;
	private int numEpisode;
	private int totalTime=0;
	private ArrayList<Episode> epList;

	// Constructor 
		//Parameters:
			//String title: the title of the TV show
			//String Genre: the Genre of the TV show
			//int Season: the number of seasons of the TV show
			//int numEpisode: the number of episodes of the TV show
	public TVShow(String title, String Genre, int Season, int numEpisode) {
		this.title = title;
		this.Genre = Genre;
		this.Season = Season;
		this.numEpisode = numEpisode;
	}
	// Constructor overload
		// ArrayList<Episode> eachEpisode: an array list of episode object storing info of all the episodes
	public TVShow(String title, String Genre, int Season, int numEpisode, ArrayList<Episode> eachEpisode) {
		this.title = title;
		this.Genre = Genre;
		this.Season = Season;
		this.numEpisode = numEpisode;
		this.epList = eachEpisode;
		for(int i=0; i<epList.size();i++) {
			this.totalTime += epList.get(i).returnTime();
		}
	}
	// compareTo
		// Description: compareTo method, compares by title
		// Parameters: TVShow p: the show that we are comparing
		// Return: difference of the titles
	public int compareTo(TVShow p) {
		return this.title.compareToIgnoreCase(p.title);
	}
	
	// equals
		// Description: equals method, compares by title
		// Parameters: object o: the show that we are comparing
		// Return: boolean if they equal
	public boolean equals(Object o) {
		TVShow tvshow = (TVShow) o;
		return title.equalsIgnoreCase(tvshow.title);
	}
	
	// ToString
		// Description: toString Method
		// Return: String of info about this show
	public String toString(){
		return String.format("Title: %s%nGenre: %s%n# of Seasons: %d%n# of Episodes: %d%nTotal Time: %s%n", 
				title,Genre, Season, numEpisode, StringTotalTime());
	}
	
	// StringTotalTime
		// Description: returns you a properly written form of total time of this show
		// Return: string of a properly written form of total time of this show
	public String StringTotalTime() {
		return String.format("%02d:%02d:%02d", totalTime/3600, totalTime%3600/60, totalTime%3600%60);
	}
	// DisplayAllEpisodes
		//Description: output all episodes of the tvshow
	public void DisplayAllEpisodes() {
		for(int i =0; i<epList.size(); i++) {
			System.out.println((i+1)+") "+epList.get(i).getTitle());

		}
	}
	//DisplayEpisodes
	// Description: This method display a episodes you have 
	// Parameters: 
		// int index: the index of the episode in the TV show 
	public void DisplayEpisodes(int index) {
		System.out.println(epList.get(index).toString());
	}
	//WatchAnEpisodes
	// Description: This method changes an episode in the TV show to watched 
	// Parameters: 
		// int index: the index of the episode in the TV show
	public void WatchAnEpisodes(int index) {
		this.epList.get(index).setWatched();
		System.out.println("Episode set to watched");
	}
	//AddAnEpisodes
	// Description: This method add an episode to the tv show  
		// Parameters: 
		// Episode newEpisode: the new episode you are adding
	public void AddAnEpisodes(Episode newEpisode) {
		Collections.sort(epList);
		if(Collections.binarySearch(epList, newEpisode)>0) {
			epList.add(newEpisode);
			this.totalTime += newEpisode.getTime();
			System.out.println("Episode added");
		}
		else {
			System.out.println("The episode number you entered already exists");
		}
	}
	
	//AddAnEpisodes
	// Description: This method removes an episode to the tv show  
		// Parameters: 
		// int choice: the way of you removing the show
	public void removeEpisodes(int choice) {
		Collections.sort(epList);
		try {
			Scanner in = new Scanner(System.in);
			if(choice ==1) {
				DisplayAllEpisodes();
				System.out.print("Enter the number of the episode you want to remove: ");
				int i = in.nextInt()-1;
				this.totalTime -= epList.get(i).getTime();
				epList.remove(i);
				System.out.println("Episode Removed");
				this.numEpisode--;
			}
			else if(choice == 2) {
				System.out.print("Enter the title of the episode you want to remove: ");
				String inTitle = in.nextLine();
				Episode newEpisode = new Episode(0, inTitle, 0, false, new Time("00:00:00"));
				Collections.sort(epList, new SortByName());
				int epiIndex = Collections.binarySearch(epList, newEpisode, new SortByName());
				if(epiIndex>0) {
					this.totalTime -= epList.get(epiIndex).getTime();
					epList.remove(epiIndex);
					System.out.println("Episode is removed");
					this.numEpisode--;
				}
				else {
					System.out.println("Episode name you entered doesn't exist");
				}
			}
			else if(choice == 3) {
				System.out.print("Enter the index of the first episode you want to remove: ");
				int firstIn = in.nextInt()-1;
				System.out.print("Enter the index of the last episode you want to remove: ");
				int lastIn = in.nextInt()-1;
				if(firstIn>=lastIn)
					throw new NumberFormatException();
				for(int i = lastIn; i>=firstIn;i--) {
					this.totalTime -= epList.get(i).getTime();
					epList.remove(i);
					this.numEpisode--;
				}
				System.out.println("Episodes are removed");
			}
			else if(choice == 4) {
				for(int i =epList.size()-1; i>=0;i--) {
					if(epList.get(i).getWatched()) {
						this.totalTime -= epList.get(i).getTime();
						epList.remove(i);
						this.numEpisode--;
					}
				}
				System.out.println("Episodes Removed");
			}
			else {
				throw new NumberFormatException();
			}
			if(epList.size()==0) {
				System.out.println("You have removed all of the episodes");
			}
			else {
				// Check how many seasons are left
				Collections.sort(epList);
				int numSeason = 1;
				int indexSeason= epList.get(0).getSeason();
				for(int i =1; i< epList.size(); i++) {
					if(epList.get(i).getSeason()!=indexSeason) {
						numSeason++;
						indexSeason = epList.get(i).getSeason();
					}
				}
				this.Season = numSeason;
				DisplayAllEpisodes();
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Input invalid\n");
		}
	}

	//sortEpisodes
	// Description: This method sort episodes in the tv show  
		// Parameters: 
		// int choice: the way of you sorting the show
	public void sortEpisodes(int choice) {
		if(choice ==1)
			Collections.sort(epList);
		else if(choice == 2)
			Collections.sort(epList, new SortByName());
		else if(choice ==3)
			Collections.sort(epList, new SortByTime());
		else {
			System.out.println("Input invalid");
		}
		System.out.println();
		DisplayAllEpisodes();
	}
	//removeSeason
	//Description: this method removes a whole season for the TV show
		// Parameter:
			//int seasonNum: the number of the season the you want to remove
	public void removeSeason(int seasonNum) {
		int num = 0;
		for(int i =0; i< epList.size();i++) {
			if(epList.get(i).getSeason()==seasonNum) {
				this.totalTime -= epList.get(i).getTime();
				epList.remove(i);
				num++;
				i--;
			}
		}
		if(num==0) {
			System.out.println("The season you entered doesn't exist");
		}
		else {
			System.out.println("Season Removed");
			this.Season--;
			this.numEpisode-=num;
		}
	}
	
	// getters
	public String getTitle() {
		return this.title;
	}
	public ArrayList<Episode> getEpList(){
		return this.epList;
	}

}
