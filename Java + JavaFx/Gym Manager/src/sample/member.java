package sample;

import javafx.beans.property.SimpleStringProperty;

public class member {
    private  final SimpleStringProperty MemberID;
    private  final SimpleStringProperty firstName;
    private  final SimpleStringProperty lastName;
    private  final SimpleStringProperty startDate;
    private  final SimpleStringProperty mAge;
    private  final SimpleStringProperty schoolName;
    //constructor
    member(String id, String firstName, String lName, String sDate, String age,String sName)
    {
        this.MemberID = new SimpleStringProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName =  new SimpleStringProperty(lName);
        this.startDate =  new SimpleStringProperty(sDate);
        this.mAge =  new SimpleStringProperty(age);
        this.schoolName =  new SimpleStringProperty(sName);

    }
    //Getters and Setters

    public String getMemberID() {
        return MemberID.get();
    }

    public void setMemberID(String id) {
        this.MemberID.set(id);
    }



    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String fName) {
        firstName.set(fName);
    }



    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lName) {
        lastName.set(lName);
    }


    public String getStartDate() {
        return startDate.get();
    }

    public void setStartDate(String sDate) {
        this.startDate.set(sDate);
    }


    public String getMAge() {
        return mAge.get();
    }

    public void setMAge(String age) {
        this.mAge.set(age);
    }

    public String getSchoolName() {
        return schoolName.get();
    }
    public void setSchoolName(String schoolName) {
        this.schoolName.set(schoolName);
    }
}
