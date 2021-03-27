package CLI;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PremierLeagueManager implements LeagueManager{
	//Files that store information.
	private static File fileClubs = new File("leagueClubs.txt");
	private static File fileMatches = new File("leagueMatches.txt");

	//Two List (Array List) to hold information retrieved or to be save to the file.
	private  List<FootBallClub> leagueClubs = new ArrayList<>();
	private  List<Match> matchList = new ArrayList<>();

	//	Making PremierLeagueManager class a singleton class

	private static  LeagueManager instance;

	public static LeagueManager getInstance(){
		if(instance == null){
			synchronized(PremierLeagueManager.class){
				if(instance == null)
					instance = new PremierLeagueManager();
			}
		}
		return instance;
	}

	private PremierLeagueManager(){}

	//adding a new club to the list.
	@Override
	public void addNewClub(SportsClub club) {
		leagueClubs.add((FootBallClub) club);
		System.out.println("Club Successfully added!");
	}

	//Deleting a club from the ArrayList having Data.
	@Override
	public void deleteClub(String clubName) {

		boolean clubFound = false;
		Iterator<FootBallClub> ite = leagueClubs.iterator();

		while(ite.hasNext()) {
			if(ite.next().getClubName().toLowerCase().equals(clubName.toLowerCase()))
				ite.remove();
			clubFound = true;
		}
		String result = (clubFound) ? "Club Successfully deleted.Number of Clubs in the League- "+leagueClubs.size() :
				"Club not Found";
		System.out.println(result);
	}

	//displaying specific details of a club using to string method.
	@Override
	public void displayStatsOfAClub(String clubName) {
		boolean clubFound = false;
		for (FootBallClub c : leagueClubs) {
			if (c.getClubName().toLowerCase().equals(clubName.toLowerCase())) {
				System.out.println(c.toString());
				clubFound = true;
			}
		}
		System.out.println((clubFound) ? "" : "Club not Found");
	}

	//Displaying each club and their standings in the table.
	@Override
	public void displayPremierLeagueStats() {
		String leftAlignFormat = "| %-15s | %-4d | %-4d | %-4d | %-4d | %-4d | %-4d | %-4d | %-4d |%n";
		System.out.format("+-----------------+------+------+------+------+------+------+------+------+%n");
		System.out.format("| Club Name       |  MP  |   W  |   D  |   L  |  GF  |  GA  |  GD  |  Pts |%n");
		System.out.format("+-----------------+------+------+------+------+------+------+------+------+%n");
		Collections.sort(leagueClubs,Collections.reverseOrder());
		for(FootBallClub c : leagueClubs){
			System.out.format(leftAlignFormat,c.getClubName(),c.getFCMatchesPlayed(),c.getFCWins(),c.getFCDraws()
					,c.getFCDefeats(),c.getFCGoals(),c.getFCAgainstGoals(),c.getFCGoalDifference(),c.getFCPoints());
		}
		System.out.format("+-----------------+------+------+------+------+------+------+------+------+%n");
	}

	//adding a new Match to their respective clubs
	@Override
	public void addNewMatch(Match match) {
		int awayWin = 0;
		int homeWin = 0;
		int awayDefeat = 0;
		int homeDefeat = 0;
		int drawMatch = 0;
		int awayPoints = 0;
		int homePoints = 0;
		int drawPoints = 0;
		//Checking who won the match using user inputs.
		if(match.getClubAwayScore()>match.getClubHomeScore()){
			awayWin+=1;
			homeDefeat+=1;
			awayPoints+=3;
		}else if(match.getClubAwayScore()<match.getClubHomeScore()){
			homeWin+=1;
			awayDefeat+=1;
			homePoints+=3;
		}else {
			drawMatch+=1;
			drawPoints+=1;
		}
		//Adding details to the clubs
		for(FootBallClub f:leagueClubs) {
			if (f.getClubName().toLowerCase().equals(match.getClubAway().toLowerCase())) {
				f.setFCGoals(f.getFCGoals() + match.getClubAwayScore());
				f.setFCAgainstGoals(f.getFCAgainstGoals() + match.getClubHomeScore());
				matchDecider(awayWin, awayDefeat, drawMatch, awayPoints, drawPoints, f);
			}else if(f.getClubName().toLowerCase().equals(match.getClubHome().toLowerCase())) {
				f.setFCGoals(f.getFCGoals() + match.getClubHomeScore());
				f.setFCAgainstGoals(f.getFCAgainstGoals() + match.getClubAwayScore());
				matchDecider(homeWin, homeDefeat, drawMatch, homePoints, drawPoints, f);
			}
		}

		matchList.add(match);
		System.out.println("Match added...");
	}

	//Common method to add common details.
	private void matchDecider(int awayWin, int awayDefeat, int drawMatch, int awayPoints, int drawPoints, FootBallClub f) {
		f.setFCWins(f.getFCWins() + awayWin);
		f.setFCDefeats(f.getFCDefeats() + awayDefeat);
		f.setFCDraws(f.getFCDraws() + drawMatch);
		f.setFCMatchesPlayed(1+f.getFCMatchesPlayed());
		f.setFCPoints(f.getFCPoints()+awayPoints);
		f.setFCPoints(f.getFCPoints()+drawPoints);
		f.setFCGoalDifference(f.getFCGoals()-f.getFCAgainstGoals());
	}

	// writing Football objects to the file
	@Override
	public void saveFile() {
		try {
			FileOutputStream fos = new FileOutputStream(fileClubs);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			for(FootBallClub s:leagueClubs){
				oos.writeObject(s);
			}

			oos.flush();
			fos.close();
			oos.close();
		} catch (IOException e) {
			System.out.println("Sorry..The specified File cannot be found or no values in the file. Please check the directory.");
		}
		try {
			// writing Match objects to the file
			FileOutputStream fosMatches = new FileOutputStream(fileMatches);
			ObjectOutputStream oosMatches = new ObjectOutputStream(fosMatches);

			for(Match m:matchList){
				oosMatches.writeObject(m);
			}

			oosMatches.flush();
			fosMatches.close();
			oosMatches.close();
		} catch (IOException e) {
			System.out.println("Sorry..The specified File cannot be found or no values in the file. Please " +
					"check the directory.");
		}
	}

	@Override
	public void readFile() {
		leagueClubs.clear();
		matchList.clear();
		//reading Football club object from the file and adding it to the arrayList.
		try {
			FileInputStream fileInput = new FileInputStream(fileClubs);
			ObjectInputStream ois = new ObjectInputStream(fileInput);

			for(;;){
				try {
					FootBallClub c = (FootBallClub) ois.readObject();
					leagueClubs.add(c);
				}catch (EOFException e){
					break;
				}
			}
			fileInput.close();
			ois.close();
		}catch (IOException | ClassNotFoundException i){
			System.out.println("Sorry..The specified File cannot be found. Please check the directory.");
		}

		try {
			//reading Match club object from the file and adding it to the arrayList.
			FileInputStream fisMatches = new FileInputStream(fileMatches);
			ObjectInputStream oisMatches = new ObjectInputStream(fisMatches);

			for(;;){
				try {
					Match m = (Match) oisMatches.readObject();
					matchList.add(m);
				}catch (EOFException e){
					break;
				}
			}
			fisMatches.close();
			oisMatches.close();
		}catch (IOException | ClassNotFoundException i){
			System.out.println("Sorry..The specified File cannot be found. Please check the directory.");
		}

	}

	public List<FootBallClub> getLeagueClubs() {
		return leagueClubs;
	}

	public void setLeagueClubs(List<FootBallClub> leagueClubs) {
		this.leagueClubs = leagueClubs;
	}

	public List<Match> getMatchList() {
		return matchList;
	}

	public void setMatchList(List<Match> matchList) {
		this.matchList = matchList;
	}
}
