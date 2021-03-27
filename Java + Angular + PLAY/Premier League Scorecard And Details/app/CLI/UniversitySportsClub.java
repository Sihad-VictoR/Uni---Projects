package CLI;

public class UniversitySportsClub extends FootBallClub {
	private String universityName;

	public UniversitySportsClub(String clubName, String clubType, String clubLocation, int clubFoundedYear,
								int clubMemberCount) {
		super(clubName, clubType, clubLocation, clubFoundedYear, clubMemberCount);
	}


	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	@Override
	public String toString() {
		return "    "+ universityName + "    ";
	}
}
