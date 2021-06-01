package sample;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Asking for user to input required function
        Scanner input= new Scanner(System.in);
        System.out.println("---------------My Gym Manager------------");
        System.out.println("1.Add new Member(Press 1)");
        System.out.println("2.Delete Member(Press 2)");
        System.out.println("3.View Member Details(Press 3)");
        System.out.println("4.Save File(Press 4)");
        System.out.println("5.Sort Details(Press 5)");
        System.out.println("6.Open GUI(Press 6)");
        System.out.println("7.Exit System(Press 7)");
        System.out.print("How can I help you? ");
        MyGymManager myGymManager=new MyGymManager();
        int firstChoice=0;
        boolean decision=true;
        while (decision) {
            while (!input.hasNextInt()) {
                System.out.println("Invalid Data Given!!!");
                System.out.print("How can I help you? ");
                input.next();
            }
            firstChoice = input.nextInt();

            switch (firstChoice) {
                case 1:
                    myGymManager.addMember();
                    System.out.println("Next Choice:");
                    break;
                case 2:
                    myGymManager.removeMember();
                    System.out.println("Next Choice:");
                    break;
                case 3:
                    myGymManager.printMember();
                    System.out.println("Next Choice:");
                    break;
                case 4:
                    myGymManager.saveMember();
                    System.out.println("Next Choice:");
                    break;
                case 5:
                    myGymManager.sortMember();
                    System.out.println("Next Choice:");
                    break;
                case 6:
                    try{
                        myGymManager.openGUI();
                        decision = false;
                    }catch (IllegalStateException e){
                        System.out.println("Application launch must not be called more than once");
                    }
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("I couldn't recognize your requirement! Try Again....");

            }
        }
    }
}
