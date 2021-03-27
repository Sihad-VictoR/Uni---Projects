package CLI;

import java.io.Serializable;
import java.util.Date;

public class Match implements Serializable,Comparable<Match> {
	private String clubHome;
	private String clubAway;
	private Date matchDate;
	private int clubHomeScore;
	private int clubAwayScore;

	public Match(String clubHome, String clubAway, Date matchDate, int clubHomeScore, int clubAwayScore) {
		this.clubHome = clubHome;
		this.clubAway = clubAway;
		this.matchDate = matchDate;
		this.clubHomeScore = clubHomeScore;
		this.clubAwayScore = clubAwayScore;
	}

	public String getClubHome() {
		return clubHome;
	}

	public void setClubHome(String clubHome) {
		this.clubHome = clubHome;
	}

	public String getClubAway() {
		return clubAway;
	}

	public void setClubAway(String clubAway) {
		this.clubAway = clubAway;
	}

	public Date getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	public int getClubHomeScore() {
		return clubHomeScore;
	}

	public void setClubHomeScore(int clubHomeScore) {
		this.clubHomeScore = clubHomeScore;
	}

	public int getClubAwayScore() {
		return clubAwayScore;
	}

	public void setClubAwayScore(int clubAwayScore) {
		this.clubAwayScore = clubAwayScore;
	}

	@Override
	public int compareTo(Match o) {
		return this.getMatchDate().compareTo(o.getMatchDate());
	}
}
