package CLI;

public class FootBallClub extends SportsClub implements Comparable<FootBallClub> {
	private int fCWins;
	private int fCDraws;
	private int fCDefeats;
	private int fCMatchesPlayed;
	private int fCGoals;
	private int fCPoints;
	private int fCAgainstGoals;
	private int fCGoalDifference;

	public FootBallClub(String clubName, String clubType, String clubLocation, int clubFoundedYear, int clubMemberCount){
		super(clubName,clubType,clubLocation,clubFoundedYear,clubMemberCount);
	}

	public int getFCWins() {
		return fCWins;
	}

	public void setFCWins(int fCWins) {
		this.fCWins = fCWins;
	}

	public int getFCDraws() {
		return fCDraws;
	}

	public void setFCDraws(int fCDraws) {
		this.fCDraws = fCDraws;
	}

	public int getFCDefeats() {
		return fCDefeats;
	}

	public void setFCDefeats(int fCDefeats) {
		this.fCDefeats = fCDefeats;
	}

	public int getFCMatchesPlayed() {
		return fCMatchesPlayed;
	}

	public void setFCMatchesPlayed(int fCMatchesPlayed) {
		this.fCMatchesPlayed = fCMatchesPlayed;
	}

	public int getFCGoals() {
		return fCGoals;
	}

	public void setFCGoals(int fCGoals) {
		this.fCGoals = fCGoals;
	}

	public int getFCPoints() {
		return fCPoints;
	}

	public void setFCPoints(int fCPoints) {
		this.fCPoints = fCPoints;
	}

	public int getFCAgainstGoals() {
		return fCAgainstGoals;
	}

	public void setFCAgainstGoals(int fCAgainstGoals) {
		this.fCAgainstGoals = fCAgainstGoals;
	}

	public int getFCGoalDifference() {
		this.fCGoalDifference = fCGoals -fCAgainstGoals;
		return fCGoalDifference;
	}

	public void setFCGoalDifference(int fCGoalDifference) {
		this.fCGoalDifference = fCGoalDifference;
	}



	@Override
	public String toString() {
		return super.toString()+"\n For this Season Club has won "+ fCWins +" matches ,"+ fCDraws
				+" draws and has faced "+ fCDefeats +" defeats. Totally they have played "+ fCMatchesPlayed
				+" matches. Scoring " + fCGoals +" Goals and receiving "+ fCAgainstGoals+" goals.\n " +
				"There total Points is " + fCPoints ;
	}

	@Override
	public int compareTo(FootBallClub o) {
		if(this.fCPoints == o.fCPoints){
			return this.fCGoalDifference- o.fCGoalDifference;
		}
		return this.fCPoints -o.fCPoints;
	}
}
