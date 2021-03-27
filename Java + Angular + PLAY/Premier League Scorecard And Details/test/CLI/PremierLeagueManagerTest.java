package CLI;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PremierLeagueManagerTest {

	LeagueManager manager = PremierLeagueManager.getInstance();
	List<FootBallClub> testList= manager.getLeagueClubs();

	@Test
	void addNewClub() {
		SportsClub testClub = new FootBallClub("Barcelona","Football","Spain"
				,1992,20000);
		manager.addNewClub(testClub);
		assertEquals("Barcelona",testList.get(0).getClubName());
		assertEquals("Football",testList.get(0).getClubType());
		assertEquals("Spain",testList.get(0).getClubLocation());
		assertEquals(1992,testList.get(0).getClubFoundedYear());
		assertEquals(20000,testList.get(0).getClubMemberCount());
	}

	@Test
	void deleteClub() {
		manager.deleteClub("Barcelona");
		assertEquals(1,testList.size());
	}


	@Test
	void addNewMatch() {
		SportsClub testClub2 = new FootBallClub("Juventus","Football","Spain"
				,1990,23000);
		manager.addNewClub(testClub2);

		Match testMatch = new Match("Barcelona","Juventus"
				,new Date(2020,10,2),1,2);
		manager.addNewMatch(testMatch);
		assertEquals(3,testList.get(1).getFCPoints());
	}

	@Test
	void saveFileAndReadFileExists() {
		File saveFile = new File("leagueClubs.txt");
		File saveFileMatches = new File("leagueMatches.txt");

		assertTrue(saveFile.exists());
		assertTrue(saveFileMatches.exists());
	}

}
