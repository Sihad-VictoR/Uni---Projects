package CLI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class ConsoleMenu {

	//Getting the instance of PremierLeagueManager.
	private static LeagueManager manager = PremierLeagueManager.getInstance();

	public static void main(String[] args) {
		//Running Console Questions.
		System.out.println("------------------------------ Premier League ------------------------------ ");
		System.out.println("Auto Save is enabled!");
		while(true){
			
			System.out.println("\nLogged in as a Manager - ManagerID : 0019013840148");
			System.out.println("\n1.Add a new Club");
			System.out.println("2.Relegate/Delete an Existing Club");
			System.out.println("3.Display various statistics of a Specific Club");
			System.out.println("4.Display statistics of Premier League Clubs");
			System.out.println("5.Add a Match");
			System.out.println("6.Save File");
			System.out.println("7.Open Angular Gui");
			System.out.println("8.Exit");
			System.out.print("\n-Enter Choice:");
			int choice = checkInteger();
			manager.readFile();

			switch (choice){
				case 1:
					addClub();
					manager.saveFile();
					break;
				case 2:
					System.out.println("-Enter Club Name:");
					String delClubName = checkString();
					manager.deleteClub(delClubName);
					manager.saveFile();
					break;
				case 3:
					System.out.println("-Enter Club Name:");
					String statClubName = checkString();
					manager.displayStatsOfAClub(statClubName);
					break;
				case 4:
					manager.displayPremierLeagueStats();
					break;
				case 5:
					try {
						manager.addNewMatch(addMatch());
					}catch (NullPointerException e){
						System.out.println("");
					}
					manager.saveFile();
					break;
				case 6:
					manager.saveFile();
					System.out.println("File has been saved..");
					break;
				case 7:
					try
					{
						Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"dir && cd ../ && sbt run\"");
						//Command to Run in ide and cmd
						//dir && cd ../ &&
					}
					catch (Exception e)
					{
						System.out.println("HEY Buddy ! U r Doing Something Wrong ");
						e.printStackTrace();
					}
					break;
				case 8:
					return;
				default:
					System.out.println("Unknown Number Entered");

			}

		}
	}

	//Add Club Method called when creating a club.
	public static void addClub(){
		//Checking for possible clubs size.
		int count = manager.getLeagueClubs().size();
		if(count==20) {
			System.out.println("Maximum Clubs reached!");
			return;
		}
		System.out.println("Number of Clubs in the League: "+count);
		//Printing the types of clubs available and asking for the input.
		System.out.println("\nType of FootBall Club For Premier League");
		System.out.println("1.English Premier League Club");
		System.out.println("2.Premier Youth League Club(U23/University FootBall Club)");
		System.out.println("3.Premier Youth League Club(U18/School FootBall Club)");
		System.out.print("\n-Enter Choice:");
		int addChoice = checkInteger();

		switch (addChoice){
			case 1:
				//Getting inputs if it is a EPL club
				System.out.println("-Enter New Club Name:");
				String clubName = checkString();
				System.out.println("-Enter New Club Location:");
				String clubLocation = checkString();
				if(checkClub(clubName)){
					System.out.println("Club Already Exist in that Name and location");
					break;
				}

				System.out.println("-Enter New Club Founded Year:");
				int date = checkInteger();
				if( date<1600 || date>2020 ){
					System.out.println("Entered date cannot be Possible.TryAgain:");
					date = checkInteger();
				}

				System.out.println("-Enter number of Members in the Club:");
				int clubMemberCount = checkInteger();
				SportsClub finalClub = new FootBallClub(clubName,"FootBall",clubLocation, date,
						clubMemberCount);
				manager.addNewClub(finalClub);
				break;
			//the other two options are kept for future updates..
			case 2:
			case 3:
				System.out.println("Currently Not Available, Will be updated soon...");
				break;
			default:
				System.out.println("Requirement Unmatched");

		}
	}

	//Method to add a new played Match
	public static Match addMatch(){
		//getting inputs , validate and creating objects of that type.
		int count = 1;
		System.out.println("Teams Available in the Premier League");
		System.out.println("-------------------------------------");
		for(FootBallClub club:manager.getLeagueClubs()){
			System.out.println(count+"."+club.getClubName());
			count++;
		}
		System.out.println("-------------------------------------");

		System.out.println("-Enter Home Club Name:");
		String homeClubName = checkString();
		while(!checkClub(homeClubName)){
			System.out.print("Club not Found! or Total Matches played.  Try Again:");
			homeClubName= checkString();
		}

		System.out.println("-Enter Away Club Name:");
		String awayClubName = checkString();
		while(!checkClub(awayClubName)){
			System.out.print("Club not Found! or Total Matches played.  Try Again:");
			awayClubName = checkString();
		}

		if(awayClubName.equals(homeClubName)){
			System.out.println("Same teams Entered..");
			return null;
		}

		System.out.println("-Enter Home Goals:");
		int homeGoals = checkInteger();
		while(homeGoals>11 || homeGoals<0){
			System.out.println("Goals cannot be Greater than 11 or Less than zero!");
			homeGoals = checkInteger();
		}

		System.out.println("-Enter Away Goals:");
		int awayGoals = checkInteger();
		while(awayGoals>11 || awayGoals<0){
			System.out.println("Goals cannot be Greater than 11 or Less than zero!");
			awayGoals = checkInteger();
		}

		//getting date input and parsing it.
		System.out.println("-Enter date(yyyy-MM-dd) :");
		String dateString;
		Date date2;
		while(true) {
			try {
				TimeZone.setDefault(TimeZone.getTimeZone("India"));
				String date=new Scanner(System.in).nextLine() ;

				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				date2 = format.parse(date);
				dateString = format.format(date2);
				break;
			} catch (ParseException e) {
				System.out.println("Invalid date given!TryAgain:");
			}
		}

		Match newMatch = new Match(homeClubName,awayClubName,date2,homeGoals,awayGoals);
		return newMatch;

	}
	//These three methods are created to check the inputs given by the user.
	//When he gives a integer value the checkInteger() method run to check the value is integer if not ask to try Again.
	public static int checkInteger(){
		Scanner input= new Scanner(System.in);
		while(!input.hasNextInt()){
			System.out.println("Invalid number- Try Again:");
			input.next();
		}
		int i = input.nextInt();
		return i;
	}

	//When user gives a string, as we need only string and no numbers in it it validates it.
	public static String checkString(){
		Scanner manager_Input = new Scanner(System.in);
		String name =manager_Input.next();
		while(Pattern.compile( "[0-9]" ).matcher( name ).find() || name.length() < 3){
			System.out.print("Invalid Name . Try Again:");
			name=manager_Input.next();
		}
		return name;
	}

	//the checkClub() method checks for the club that is existing and the whether the club can play matches.
	public static boolean checkClub(String clubName){
		for(FootBallClub club:manager.getLeagueClubs()){
			if(club.getClubName().toLowerCase().equals(clubName.toLowerCase())){
				if(club.getFCMatchesPlayed() <38){
					return true;
				}else if(club.getFCMatchesPlayed() >37){
					return false;
				}
			}
		}
		return false;
	}
}
