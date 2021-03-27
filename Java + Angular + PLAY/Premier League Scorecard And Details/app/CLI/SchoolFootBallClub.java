package CLI;

public class SchoolFootBallClub extends FootBallClub {

	private String schoolName;

	public SchoolFootBallClub(String clubName, String clubType, String clubLocation, int clubFoundedYear,
							  int clubMemberCount) {
		super(clubName, clubType, clubLocation, clubFoundedYear, clubMemberCount);
	}


	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	@Override
	public String toString() {
		return super.toString()+ schoolName +"    ";
	}
}
