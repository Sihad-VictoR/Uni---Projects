package sample;

public class DefaultMember{
    private String membershipId;
    private String firstName;
    private String lastName;
    private String membershipStartDate;
    //constructor
    public DefaultMember(String membershipId,String firstName,String lastName,String membershipStartDate){
        super();
        this.membershipId=membershipId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.membershipStartDate=membershipStartDate;
        setMembershipId(membershipId);
        setFirstName(firstName);
        setLastName(lastName);
        setMembershipStartDate(membershipStartDate);
    }
    //get values from the class
    public String getMembershipId() {
        return membershipId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getMembershipStartDate() {
        return membershipStartDate;
    }
    //set values in the class
    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setMembershipStartDate(String membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }
}