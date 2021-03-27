package CLI;

import java.util.List;

public interface LeagueManager {
	void addNewClub(SportsClub club);
	void deleteClub(String clubName);
	void displayStatsOfAClub(String clubName);
	void displayPremierLeagueStats();
	void addNewMatch(Match match);
	void saveFile();
	void readFile();
	List<FootBallClub> getLeagueClubs();
	List<Match> getMatchList();
}
