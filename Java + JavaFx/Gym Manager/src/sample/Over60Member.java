package sample;

public class Over60Member extends DefaultMember {

    private int age;

    public Over60Member(String membershipId, String firstName, String lastName, String membershipStartDate,int age) {
        super(membershipId, firstName, lastName, membershipStartDate);
        setAge(age);
    }
    //getters and setters
    public int getAge() {
        return age;
    }

    public void setAge(int age) { this.age=age; }
}
