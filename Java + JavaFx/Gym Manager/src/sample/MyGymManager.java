package sample;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGymManager implements GymManager {
    @Override
    //adding member
    public void addMember() {
        //Calling another class creating an object.
        //Setting off logger information.
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.OFF);
        Database db=new Database();
        Scanner sc = new Scanner(System.in);
        //checking for total count
        if (db.count <= 100) {
            System.out.println("No.of slots available="+(100-db.count));
            //generating an automated id for the new member.
            String membershipId = membershipId();
            System.out.println("New Membership ID: " + membershipId);

            //Getting information from the user and validating it.
            System.out.print("First Name: ");
            String firstName=sc.nextLine();
            while(!isStringOnlyAlphabet(firstName)){
                System.out.println("No spaces/Numbers or symbols...Enter First Name Again: ");
                firstName=sc.nextLine();
            }

            System.out.print("Last Name: ");
            String lastName=sc.nextLine();
            while(!isStringOnlyAlphabet(lastName)){
                System.out.println("No spaces/Numbers or symbols...Enter Last Name Again: ");
                lastName=sc.nextLine();
            }
            // Setting up Date
            String finalDate;
            while(true) {
                try {
                    Scanner input= new Scanner(System.in);
                    Date dateValidator = new Date();
                    int day = 0, month = 0, year = 0;
                    System.out.println("Start Membership Date ");
                    System.out.print("Day: ");
                    day = input.nextInt();
                    dateValidator.setCheckDay(day);
                    System.out.print("Month: ");
                    month = input.nextInt();
                    dateValidator.setCheckMonth(month);
                    System.out.print("Year: ");
                    year = input.nextInt();
                    dateValidator.setCheckYear(year);
                    dateValidator.setCheckDate(dateValidator.getCheckDay(), dateValidator.getCheckMonth(), dateValidator.getCheckYear());
                    finalDate = String.valueOf(dateValidator.getCheckDate());
                    System.out.println(finalDate);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Numbers Required..");
                }
            }
            System.out.println("Enter the membership type(Default member[d],Student Member[s],Over 60 Member [o]) :");
            String memberType=sc.nextLine();
            while(!isStringOnlyAlphabet(memberType)){
                System.out.println("No spaces/Numbers or symbols...Enter membership type Again: ");
                memberType=sc.nextLine();
            }
            boolean request=true;
            DefaultMember member=null;
            //adding each member to required categories.
            while (request) {
                switch (memberType) {
                    case "D":
                    case "d":
                        member=new DefaultMember(membershipId,firstName,lastName,finalDate);
                        try {
                            db = new Database();
                            db.insertData(member);
                        }catch (NullPointerException e){
                            System.out.println("Null Values Found");
                        }
                        request = false;
                        break;
                    case "S":
                    case "s":
                        System.out.println("Enter Student's School Name :");
                        Scanner sName=new Scanner(System.in);
                        String schoolName=sName.nextLine();
                        member=new StudentMember(membershipId,firstName,lastName,finalDate,schoolName);
                        db = new Database();
                        try {
                            db.insertData(member,schoolName);
                        }catch (NullPointerException e){
                            System.out.println("Null Values Found");
                        }
                        request = false;
                        break;
                    case "O":
                    case "o":
                        System.out.println("Enter Member's Age(Over 60) :");
                        Scanner sAge=new Scanner(System.in);
                        while (!sAge.hasNextInt()) {
                            System.out.println("Invalid Data Given!!!");
                            System.out.print("Enter Age Again: ");
                            sAge.next();
                        }
                        int age =sAge.nextInt();
                        while (age<60){
                            System.out.print("Enter Age Again(age>60): ");
                            while (!sAge.hasNextInt()) {
                                System.out.println("Invalid Data Given!!!");
                                System.out.print("Enter Age Again: ");
                                sAge.next();
                            }
                            age =sAge.nextInt();
                        }
                        member=new Over60Member(membershipId,firstName,lastName,finalDate,age);
                        try {
                            db = new Database();
                            db.insertData(member.getMembershipId(),member.getFirstName(),member.getLastName(),member.getMembershipStartDate(),age);
                        }catch (NullPointerException e){
                            System.out.println("Null Values Found");
                        }
                        request = false;
                        break;
                    default:
                        System.out.println("I couldn't recognize your requirement! Try Again....");
                        memberType=sc.nextLine();
                }
            }
            System.out.println("Member Successfully Added");
        }else {
            System.out.println("No free slots available.....");
        }
    }

    @Override
    public void printMember() {
        //Calling another class creating an object.
        //Setting off logger information.
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.OFF);
        Database db=new Database();
        //Getting data from the database and storing it in the arrayLists.
        int i = 1,j=1,k=1,l=1,m=1,n=1;
        db.saveData("Membership ID",i,db.id);
        db.saveData("First Name",j,db.fName);
        db.saveData("Last Name",k,db.lName);
        db.saveData("Membership Start Date",l,db.date);
        db.saveData("Age",m,db.age);
        db.saveData("School Name",n,db.sName);
        System.out.println("Successfully Saved....");
        //printing Data.
        db.printData();

    }

    @Override
    public void removeMember() {
        Scanner delInput=new Scanner(System.in);
        System.out.println("Enter Membership ID(ex-GMEM64):");
        String delID=delInput.next();
        //Calling another class creating an object.
        //Setting off logger information.
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.OFF);
        Database db=new Database();
        //Deleting Data
        if(!db.getData(delID)){
            db.delData(delID);
            System.out.println("Member Successfully Deleted");
            System.out.println("No.of slots available="+(100-(db.count-1)));
        }else {
            System.out.println("No Member Found in the ID");
            System.out.println("No.of slots available="+(100-db.count));
        }
    }

    @Override
    public void sortMember() {
        //Sorting member in ascending or descending order.
        Scanner sortInput=new Scanner(System.in);
        System.out.println("Sort Data(1-Ascending,2-Descending):");
        while (!sortInput.hasNextInt()) {
            System.out.println("Invalid Data Given!!!");
            System.out.print("How can I help you? ");
            sortInput.next();
        }
        int sort=sortInput.nextInt();
        //Calling another class creating an object.
        //Setting off logger information.
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.OFF);
        Database db=new Database();
        switch (sort){
            case 1:
                db.sortData("yes");
                break;
            case 2:
                db.sortData("no");
                break;
            default:
                System.out.println("Sorry Enter 1 or 2...");
                sortMember();
        }
    }

    @Override
    public void openGUI() {
        //opening GUI.
        MainGUI fx=new MainGUI();
        fx.printSomething();
    }

    @Override
    public void saveMember() {
        //Calling another class creating an object.
        //Setting off logger information.
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.OFF);
        Database db=new Database();
        //Getting data from the database and storing it in the arrayLists.
        int i = 1,j=1,k=1,l=1,m=1,n=1;
        db.saveData("Membership ID",i,db.id);
        db.saveData("First Name",j,db.fName);
        db.saveData("Last Name",k,db.lName);
        db.saveData("Membership Start Date",l,db.date);
        db.saveData("Age",m,db.age);
        db.saveData("School Name",n,db.sName);
        System.out.println("Successfully Saved....");
        //writing details to a file.
        db.writeData();
    }

    private static String membershipId(){
        Random rand = new Random();
        int number=rand.nextInt(100);
        //Calling another class creating an object.
        //Setting off logger information.
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.OFF);
        Database db=new Database();
        //Checking for existing number and getting a new number.
        String code="GMEM"+number;
        while (!db.getData(code)){
            number=rand.nextInt(100);
            code="GMEM"+number;
        }
        return code;
    }
    public static boolean isStringOnlyAlphabet(String str)
    {
        return ((str != null) && (!str.equals("")) && (str.matches("^[a-zA-Z]*$")));
    }
}
