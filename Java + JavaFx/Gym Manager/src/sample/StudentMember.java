package sample;

public class StudentMember extends DefaultMember {
    private String schoolName;

    public StudentMember(String membershipId, String firstName, String lastName, String membershipStartDate,String schoolName) {
        super(membershipId, firstName, lastName, membershipStartDate);
        this.schoolName=schoolName;
    }
    //getters and setters
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
