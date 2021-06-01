package sample;

import java.util.Scanner;

public class Date {
    private static final Scanner input=new Scanner(System.in);
    private int checkDay;
    private int checkMonth;
    private int checkYear;
    private String checkDate;

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(int checkDay, int checkMonth, int checkYear) {
        switch (checkMonth) {
            case 1:
                while (checkDay<1 || checkDay>31){
                    System.out.println("Invalid Day Entered!");
                    System.out.println("Day: ");
                    checkDay = input.nextInt();
                }
                break;
            case 2:
                if ((checkYear % 400 == 0) || ((checkYear % 4 == 0) && (checkYear % 100 != 0))) {
                    while (checkDay<1 || checkDay>29){
                        System.out.println("Invalid Day Entered!Leap Year has 29 days in February");
                        System.out.println("Day: ");
                        checkDay = input.nextInt();
                    }
                } else {
                    while (checkDay<1 || checkDay>28){
                        System.out.println("Invalid Day Entered!");
                        System.out.println("Day: ");
                        checkDay = input.nextInt();
                    }
                }
                break;
            case 3:
                while (checkDay<1 || checkDay>31){
                    System.out.println("Invalid Day Entered!");
                    System.out.println("Day: ");
                    checkDay = input.nextInt();
                }
                break;
            case 4:
                while (checkDay<1 || checkDay>30){
                    System.out.println("Invalid Day Entered!");
                    System.out.println("Day: ");
                    checkDay = input.nextInt();
                }
                break;
            case 5:
                while (checkDay<1 || checkDay>31){
                    System.out.println("Invalid Day Entered!");
                    System.out.println("Day: ");
                    checkDay = input.nextInt();
                }
                break;
            case 6:
                while (checkDay<1 || checkDay>30){
                    System.out.println("Invalid Day Entered!");
                    System.out.println("Day: ");
                    checkDay = input.nextInt();
                }
                break;
            case 7:
                while (checkDay<1 || checkDay>31){
                    System.out.println("Invalid Day Entered!");
                    System.out.println("Day: ");
                    checkDay = input.nextInt();
                }
                break;
            case 8:
                while (checkDay<1 || checkDay>31){
                    System.out.println("Invalid Day Entered!");
                    System.out.println("Day: ");
                    checkDay = input.nextInt();
                }
                break;
            case 9:
                while (checkDay<1 || checkDay>30){
                    System.out.println("Invalid Day Entered!");
                    System.out.println("Day: ");
                    checkDay = input.nextInt();
                }
                break;
            case 10:
                while (checkDay<1 || checkDay>31){
                    System.out.println("Invalid Day Entered!");
                    System.out.println("Day: ");
                    checkDay = input.nextInt();
                }
                break;
            case 11:
                while (checkDay<1 || checkDay>30){
                    System.out.println("Invalid Day Entered!");
                    System.out.println("Day: ");
                    checkDay = input.nextInt();
                }
                break;
            case 12:
                while (checkDay<1 || checkDay>31){
                    System.out.println("Invalid Day Entered!");
                    System.out.println("Day: ");
                    checkDay = input.nextInt();
                }

        }
        this.checkDay = checkDay;
        this.checkMonth = checkMonth;
        this.checkYear = checkYear;
        this.checkDate=checkDay+"/"+checkMonth+"/"+checkYear;

    }

    public int getCheckMonth() {
        return checkMonth;
    }

    public void setCheckMonth(int checkMonth) {
        while (checkMonth<1 || checkMonth>12){
            System.out.println("Invalid Month Entered!");
            System.out.println("Month: ");
            checkMonth = input.nextInt();
        }

        this.checkMonth = checkMonth;
    }

    public int getCheckYear() {
        return checkYear;
    }

    public void setCheckYear(int checkYear) {

        while (checkYear <1990 || checkYear >2100){
            System.out.println("Invalid Year Entered!");
            System.out.println("Year: ");
            checkYear  = input.nextInt();
        }
        this.checkYear = checkYear;
    }

    public int getCheckDay() {
        return checkDay;
    }

    public void setCheckDay(int checkDay) {
        while (checkDay<1 || checkDay>31){
            System.out.println("Invalid Day Entered!");
            System.out.println("Day: ");
            checkDay= input.nextInt();
        }
        this.checkDay = checkDay;
    }
}
