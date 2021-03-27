package controllers;

import CLI.Match;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GUIControllerTest {

	@Test
	void autoGenerateMatch() {
		GUIController.dataToTable();
		Match testMatch = GUIController.autoGenerateMatch();
		assertEquals("Arsenal",testMatch.getClubAway());
	}
}
