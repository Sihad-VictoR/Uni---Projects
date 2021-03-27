package controllers;

import CLI.*;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GUIController {
	//This Controller is mainly used to retrieve Data from the files. And do Back end process
	//that needs to be sent to the front end.
	private static File saveFile = new File("leagueClubs.txt");
	private static File matchFile = new File("leagueMatches.txt");

	static LeagueManager manager = PremierLeagueManager.getInstance();
	static List<FootBallClub> clubList = manager.getLeagueClubs();
	static List<Match> matchList = manager.getMatchList();

	public static List<FootBallClub> getClubList() {
		clubList.clear();
		matchList.clear();
		manager.readFile();
		Collections.sort(clubList,Collections.reverseOrder());
		return clubList;
	}

	public static List<Match> getMatchList() {
		return matchList;
	}

	public static List<Match> getMatchSortList() {
		Collections.sort(matchList);
		return matchList;
	}

	public static void dataToTable(){
		clubList.clear();
		matchList.clear();
		manager.readFile();
	}

	public static List<FootBallClub> goalSortedClubList() {
		Collections.sort(clubList,new GoalSortingComparator().reversed());
		return clubList;
	}

	public static List<FootBallClub> winSortedClubList() {
		Collections.sort(clubList,new WinSortingComparator().reversed());
		return clubList;
	}

	//Randomly generating a match using random Object.
	public static Match autoGenerateMatch(){
		Random random = new Random();

		int home = random.nextInt(10);
		int away = random.nextInt(10);


		List<FootBallClub> newList = new ArrayList<>();
		for(FootBallClub footBallClub: clubList){
			if(footBallClub.getFCMatchesPlayed()<38){
				newList.add(footBallClub);
			}
		}if(newList.size()<2){
			return null;
		}
		String homeName = newList.get(random.nextInt(newList.size())).getClubName();
		String awayName = newList.get(random.nextInt(newList.size())).getClubName();

		while(homeName.equals(awayName)){
			homeName = newList.get(random.nextInt(newList.size())).getClubName();
		}
		TimeZone.setDefault(TimeZone.getTimeZone("India"));
		int min = 1;
		int max = 12;
		int max2 = 28;
		int day = (int)(Math.random()*(max2-min+1)+min);
		int month = (int) (Math.random()*(max-min+1)+min);
		String days ="";
		String months ="";
		if(day<10){
			days = "0"+day;
		}else days= String.valueOf(day);
		if(month<10){
			months = "0"+month;
		}else months= String.valueOf(month);

		String newDate = 2020+"-" +months+"-"+ days;
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");

		Date date2 = null;

		try {
			date2 = format.parse(newDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Match newMatch = new Match(homeName,awayName,date2,home,away);
		manager.addNewMatch(newMatch);
		manager.saveFile();

		return newMatch;

	}

}
