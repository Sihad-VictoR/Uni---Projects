package sample;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyGymManagerTest {

    @Test
    public void addAndRemoveMember() {
        DefaultMember member=new DefaultMember("GMEM00","Test","Test","12/07/2020");
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.OFF);
        Database db=new Database();
        db.insertData(member);
        db.insertData(member,"Test");
        db.insertData("GMEM00","Test","Test","12/07/2020",90);
        String delID="GMEM00";
        db.delData(delID);
        db.delData(delID);
        db.delData(delID);
    }

    @Test
    public void printMember() {
        // NOTE: THIS RUNS THE MAIN METHOD
        MyGymManager manager=new MyGymManager();
        manager.printMember();
    }

    @Test
    public void sortMember() {
        // NOTE: THIS RUNS THE MAIN METHOD
        MyGymManager manager=new MyGymManager();
        manager.sortMember();
    }

    @Test
    public void openGUI() {
        // NOTE: THIS OPENS THE GUI BY RUNNING THE MAIN METHOD TO OPEN GUI
        MyGymManager manager=new MyGymManager();
        manager.openGUI();
    }

    @Test
    public void saveMember() {
        //By reading the file we can test that the file is being saved.
        File file =new File("MyGYM Details.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        }
        while (sc.hasNextLine())
            System.out.println(sc.nextLine());
    }
}