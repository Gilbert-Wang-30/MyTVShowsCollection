// Gilbert Wang
// November 21st, 2022
// Driver
// This program contains the main program, and allow you to access and edit your TVShow collection.
import java.io.*;
import java.util.*;

public class Driver {
	// main
	public static void main (String[] args) throws IOException {
		//Variables
		boolean valid;
		int input = 0;
		int intInput = 0;
		ArrayList <TVShow> TVShowList = new ArrayList<TVShow>();
		BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in));
		int mainMenuChoice, subMenuChoice = 0;
		AddTVShow(TVShowList,"umbrella.txt");
		// Display
		mainMenuChoice = displayMenu (0, stdIn);
		while(mainMenuChoice !=3) {
			if (mainMenuChoice == 1) {
				subMenuChoice = displayMenu (1, stdIn);
				while(subMenuChoice!=6) {
					if(subMenuChoice == 1) {
						DisplayAllTitles(TVShowList);
					}
					else if(subMenuChoice == 2) {
						DisplayAllInfo(TVShowList);
					}
					else if(subMenuChoice == 3) {
						System.out.print ("\n\nPlease enter the file name:  ");
						String in = stdIn.readLine ();
						AddTVShow(TVShowList, in);
					}
					else if(subMenuChoice ==4) {
						RemoveTVShow(TVShowList);
					}
					else if(subMenuChoice ==5) {
						System.out.println(showStatus(TVShowList));
					}
					subMenuChoice = displayMenu (1, stdIn);
				}
			}
			//Main Menu Choice 2
			else if (mainMenuChoice == 2&&TVShowList.size()>0) {
				valid = false;
				while(!valid) {
					DisplayAllTitles(TVShowList);
					System.out.print ("\n\nPlease enter the number of the show you want to display:  ");
					try {
						input = Integer.parseInt(stdIn.readLine ());	
						intInput = input;
						if(input-1<0||input-1>=TVShowList.size())
							throw new NumberFormatException();
						valid =true;
					}
					catch(NumberFormatException e) {
						System.out.println("Input invalid\n");
						valid= false;
					}
				}
				TVShow show = TVShowList.get(input-1);
				subMenuChoice = displayMenu (2, stdIn);
				while(subMenuChoice!=7&&TVShowList.size()>0) {
					if(subMenuChoice == 1) {
						show.DisplayAllEpisodes();
					}
					else if(subMenuChoice == 2) {
						valid = false;
						while(!valid) {
							show.DisplayAllEpisodes();
							System.out.print ("\n\nPlease enter the number of the Episode you want to display:  ");
							try {
								input = Integer.parseInt(stdIn.readLine ());	
								if(input-1<0||input-1>show.getEpList().size())
									throw new NumberFormatException();
								valid =true;
							}
							catch(NumberFormatException e) {
								System.out.println("Input invalid");
								valid= false;
							}
						}
						show.DisplayEpisodes(input-1);
					}
					else if(subMenuChoice == 3) {
						valid = false;
						while(!valid) {
							show.DisplayAllEpisodes();
							System.out.print ("\n\nPlease enter the number of the Episode you watched:  ");
							try {
								input = Integer.parseInt(stdIn.readLine ());	
								if(input-1<0||input-1>show.getEpList().size())
									throw new NumberFormatException();
								valid =true;
							}
							catch(NumberFormatException e) {
								System.out.println("Input invalid");
								valid= false;
							}
						}
						show.WatchAnEpisodes(input-1);
					}
					else if(subMenuChoice == 4) {
						try {
							System.out.print("Please enter the title of the episode: ");
							String title = stdIn.readLine();
							System.out.print("Please enter the number of the episode: ");
							int episodeNum = Integer.parseInt(stdIn.readLine());
							System.out.print("Please enter the season of the episode: ");
							int episodeSeason = Integer.parseInt(stdIn.readLine());
							System.out.print("Please enter the time of the episode in proper format: ");
							String time = stdIn.readLine();
							if(time.length()!=8||time.indexOf(':')==time.lastIndexOf(':')||time.indexOf(':')<0)
								throw new NumberFormatException();
							show.AddAnEpisodes(new Episode(episodeNum, title, episodeSeason, false, new Time(time)));							
						}
						catch(NumberFormatException e) {
							System.out.println("Input invalid\n");
						}
						catch(StringIndexOutOfBoundsException e) {
							System.out.println("Input invalid\n");
						}
					}
					else if(subMenuChoice == 5) {
						System.out.println ("1) Remove by ep#");
						System.out.println ("2) Remove by title");
						System.out.println ("3) Remove by range");
						System.out.println ("4) Remove watched");
						try {
							System.out.print("Please enter the way you want to remove: ");
							int way = Integer.parseInt(stdIn.readLine());
							show.removeEpisodes(way);
							if(show.getEpList().size()==0) {
								TVShowList.remove(intInput-1);
								break;
							}
						}
						catch(NumberFormatException e) {
							System.out.println("Input invalid\n");
						}
					}
					else if(subMenuChoice == 6) {
						System.out.println ("1) Sort by number");
						System.out.println ("2) Sort by title");
						System.out.println ("3) Sort by time");
						try {
							System.out.print("Please enter the way you want to sort: ");
							int way = Integer.parseInt(stdIn.readLine());
							show.sortEpisodes(way);
							}
						catch(NumberFormatException e) {
							System.out.println("Input invalid\n");
						}
					}
					
					subMenuChoice = displayMenu (2, stdIn);
				}
			}
			else if(TVShowList.size()<=0) {
				System.out.println("No Shows to access");
			}
			mainMenuChoice = displayMenu (0, stdIn);
		}
		System.out.println("Program is completed");
	}
	
	//DisplayAllTitles
	// Description: This method display all the titles of the TV show you have 
	// Parameters: 
		// ArrayList <TVShow> TVShowList: this is the array list of your TV shows
	public static void DisplayAllTitles(ArrayList <TVShow> TVShowList) {
		if(TVShowList.size()<=0) {
			System.out.println("No shows to display");
		}
		else {
			for(int i=0;i<TVShowList.size();i++)
				System.out.println((i+1)+") "+TVShowList.get(i).getTitle());
		}
	}
	
	//DisplayAllInfo
	// Description: This method display all the info of a TV 
	// Parameters: 
		// ArrayList <TVShow> TVShowList: this is the array list of your TV shows
	public static void DisplayAllInfo(ArrayList <TVShow> TVShowList) throws IOException {
		if(TVShowList.size()<=0) {
			System.out.println("No shows to display");
		}
		else {
			boolean valid = false;
			int input = 0;
			while(!valid) {
				DisplayAllTitles(TVShowList);
				System.out.print ("\n\nPlease enter the number of the show you want to display:  ");
				try {
					BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in));
					input = Integer.parseInt(stdIn.readLine ());	
					if(input-1<0||input-1>=TVShowList.size())
						throw new NumberFormatException();
					valid =true;
				}
				catch(NumberFormatException e) {
					System.out.println("Input invalid\n");
					valid= false;
				}
			}
			System.out.println("\n"+TVShowList.get(input-1).toString());
		}
	}
	//AddTVShow
	// Description: This method adds a TV show to the array list of TV show you have 
	// Parameters: 
		// ArrayList <TVShow> TVShowList: this is the array list of your TV shows
		// String input: the name of the file that the program is reading
	public static void AddTVShow(ArrayList <TVShow> TVShowList, String input) {
		String line;
		String title;
		String genre;
		int season = 0;
		int seasonNum = 0;
		int numEpisode;
		int totalNumEpisode = 0;
		ArrayList<Episode> eachEpisode = new ArrayList<Episode>();
		try {
			BufferedReader inFile = new BufferedReader (new FileReader(input.toLowerCase()));
			title = inFile.readLine();
			if(!TVShowList.contains(new TVShow(title, "", 0, 0))){
				genre = inFile.readLine();
				line = inFile.readLine();
				while (line != null){
					seasonNum++;
					season = Integer.parseInt(line.substring(7));
					numEpisode = Integer.parseInt(inFile.readLine());
					totalNumEpisode +=numEpisode;
					for(int i=0;i<numEpisode;i++) {
						eachEpisode.add(new Episode(Integer.parseInt(inFile.readLine().substring(8)), inFile.readLine(), season, false, new Time(inFile.readLine())));
					}
					line = inFile.readLine();
				}
				TVShowList.add(new TVShow(title, genre, seasonNum, totalNumEpisode,eachEpisode));
				inFile.close ();
				System.out.println("Adding is complete");
			}
			else {
				System.out.println("The show you entered already exist");
			}
		}
		// Errors
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		catch (IOException e) {
			System.out.println("Reading error");
		}
		catch (NumberFormatException e)	{
			System.out.println("Invalid file format");
		}
		catch (StringIndexOutOfBoundsException e) {
			System.out.println("Invalid file format");
		}
	}
	//RemoveTVShow
		// Description: This method removes a TV show from the array list of TV show you have 
		// Parameters: 
			// ArrayList <TVShow> TVShowList: this is the array list of your TV shows
	public static void RemoveTVShow(ArrayList <TVShow> TVShowList) throws IOException {
		boolean valid = false;
		int input = 0;
		int choice = 0;
		if(TVShowList.size()<=0) {
			System.out.println("No shows to remove");
		}
		else {
			while(!valid) {
				DisplayAllTitles(TVShowList);
				System.out.print ("\n\nPlease enter the number of the show you want to remove:  ");
				try {
					BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in));
					input = Integer.parseInt(stdIn.readLine ());	
					if(input-1<0||input-1>=TVShowList.size())
						throw new NumberFormatException();
					valid =true;
				}
				catch(NumberFormatException e) {
					System.out.println("Input invalid\n");
					valid= false;
				}
			}
			input--;
			System.out.println("1) Remove by show");
			System.out.println("2) Remove by season");
			System.out.print("Type the number of the way you want to remove the Show: ");
			try {
				Scanner s = new Scanner(System.in);
				choice = Integer.parseInt (s.nextLine ());
				if(choice == 1) {
					TVShowList.remove(input);
					System.out.println("Show Removed");
				}
				else if(choice == 2) {
					System.out.print("\nType the number of the season: ");
					TVShowList.get(input).removeSeason(Integer.parseInt(s.nextLine()));
					if(TVShowList.get(input).getEpList().size()<=0) {
						TVShowList.remove(input);
						System.out.println("No more episodes, show removed");
					}
				}
				else {
					System.out.println("Invalid number format");
				}
			}
			catch (NumberFormatException e)	{
				System.out.println("Invalid number format");
			}
		}
	}
	//showStatue
		// Description: Display how much of the show has been watched
		// Parameters: 
			// ArrayList <TVShow> TVShowList: this is the array list of your TV shows
	public static String showStatus(ArrayList<TVShow> TVShowList) {
		String returnString= "";
		String seasonString= "";
		Collections.sort(TVShowList);
		for(int i =0; i< TVShowList.size(); i++) {
			returnString += String.format("%n%n%s", TVShowList.get(i).getTitle());
			TVShow show = TVShowList.get(i);
			ArrayList<Episode> epList = show.getEpList();
			Collections.sort(epList);
			int watched = 0;
			int unWatched = 0;
			int total = 0;
			int watchedSeason = 0;
			int currentSeason= epList.get(0).getSeason();
			int indexSeason= epList.get(0).getSeason();
			for(int j = 0; j< epList.size(); j++) {
				indexSeason= epList.get(j).getSeason();
				if(currentSeason == indexSeason) {
					if(epList.get(j).getWatched()){
						watched++;
						total++;
					}
					else {
						unWatched++;
						total++;
					}
					seasonString = String.format("%nSeason %d = %d eps watched out of %d eps",currentSeason,watched, total);
				}
				else {
					if(watched == total) {
						watchedSeason ++;
					}
					returnString += seasonString;
					currentSeason = indexSeason;
					j--;
					watched = 0;
					total = 0;
				}
			}
			if(watched == total) {
				watchedSeason ++;
			}
			returnString += seasonString;
			returnString += String.format("%n%d Seasons completely watched", watchedSeason);
			returnString += String.format("%n%d episode unwatched", unWatched);
		}
		return returnString;
	}
	
	//displayMenu
		// Description: This method stores the menu 
		// Parameters: 
			// int menuNum: the menu that you are currently in
			// BufferedReader stdIn: the user input
	public static int displayMenu (int menuNum, BufferedReader stdIn) throws IOException {
		int choice= 0;
		if (menuNum == 0) {
			System.out.println ("----------  MAIN MENU  -----------");
			System.out.println ("1) Accessing your TV shows list");
			System.out.println ("2) Accessing within a particular TV show");
			System.out.println ("3) Exit");
			System.out.println ("----------------------------------");
		}
		else if (menuNum == 1) {
			System.out.println ("\n---------  SUB-MENU #1  ----------");
			System.out.println ("1) Display a list of all your TV shows"); // Just the CD titles, numbered in order
			System.out.println ("2) Display info on a particular TV show"); 
			System.out.println ("3) Add a TV show"); // Adds a CD by reading from input file
			System.out.println ("4) Remove (show or season)");
			System.out.println ("5) Show status");
			System.out.println ("6) Return back to main menu.");
			System.out.println ("----------------------------------");
		}
		else {
			System.out.println ("\n---------  SUB-MENU #2  ----------");
			System.out.println ("1) Display all episodes (in the last sorted order) ");
			System.out.println ("2) Display info on a particular episode ");
			System.out.println ("3) Watch an episode");
			System.out.println ("4) Add an episode");
			System.out.println ("5) Remove episode(s) (4 options)");
			System.out.println ("6) Sort episodes (3 options)");
			System.out.println ("7) Return back to main menu");
			System.out.println ("----------------------------------");
		}
		boolean valid = false;
			while(!valid) {
			try {
				System.out.print ("\n\nPlease enter your choice:  ");
				choice = Integer.parseInt (stdIn.readLine ());
				valid =true;
			}
			catch (NumberFormatException e)	{
				System.out.println("Invalid number format");
			}
		}
			return choice;
	}


}

