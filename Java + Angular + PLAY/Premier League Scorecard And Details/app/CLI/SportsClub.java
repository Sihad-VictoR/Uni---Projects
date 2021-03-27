package CLI;

import java.io.Serializable;

public class SportsClub implements Serializable {

	private String clubName;
	private String clubType;
	private String clubLocation;
	private int clubFoundedYear;
	private int clubMemberCount;

	public SportsClub(String clubName, String clubType, String clubLocation, int clubFoundedYear, int clubMemberCount) {
		this.clubName = clubName;
		this.clubType = clubType;
		this.clubLocation = clubLocation;
		this.clubFoundedYear = clubFoundedYear;
		this.clubMemberCount = clubMemberCount;
	}


	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getClubType() {
		return clubType;
	}

	public void setClubType(String clubType) {
		this.clubType = clubType;
	}

	public String getClubLocation() {
		return clubLocation;
	}

	public void setClubLocation(String clubLocation) {
		this.clubLocation = clubLocation;
	}

	public int getClubFoundedYear() {
		return clubFoundedYear;
	}

	public void setClubFoundedYear(int clubFoundedYear) {
		this.clubFoundedYear = clubFoundedYear;
	}

	public int getClubMemberCount() {
		return clubMemberCount;
	}

	public void setClubMemberCount(int clubMemberCount) {
		this.clubMemberCount = clubMemberCount;
	}


	@Override
	public String toString() {
		return "Name of the Club is "+clubName + ".It is a " +clubType + ".It is Located in "+ clubLocation + ".Club "+
				"was founded in "+ clubFoundedYear + " and there are " + clubMemberCount +" members in the club.";
	}
}
