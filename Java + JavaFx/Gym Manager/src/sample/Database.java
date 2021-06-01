package sample;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Database {
    // Creating a Mongo client.
    MongoClient mongo = new MongoClient("localhost", 27017);

    // Accessing the database and the collection.
    MongoDatabase database = mongo.getDatabase("mygym");
    MongoCollection<Document> collection = database.getCollection("members");
    int count = (int) collection.count();

    //Array List to store Data.
    ArrayList<String> id = new ArrayList<String>();
    ArrayList<String> fName = new ArrayList<String>();
    ArrayList<String> lName = new ArrayList<String>();
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<String> age = new ArrayList<String>();
    ArrayList<String> sName = new ArrayList<String>();

    //Inserting document into the collection Default Member.
    public void insertData(DefaultMember member) {
        String id = member.getMembershipId();
        String fName = member.getFirstName();
        String lName = member.getLastName();
        String date = member.getMembershipStartDate();
        Document document = new Document("Membership ID", id)
                .append("First Name", fName)
                .append("Last Name", lName)
                .append("Membership Start Date", date);

        collection.insertOne(document);
    }
    //Inserting document into the collection Student Member.
    public void insertData(DefaultMember member, String schoolName) {
        String id = member.getMembershipId();
        String fName = member.getFirstName();
        String lName = member.getLastName();
        String date = member.getMembershipStartDate();
        Document document = new Document("Membership ID", id)
                .append("First Name", fName)
                .append("Last Name", lName)
                .append("Membership Start Date", date)
                .append("School Name", schoolName);

        collection.insertOne(document);
    }
    //Inserting document into the collection Over60 Member.
    public void insertData(String id, String fName, String lName, String date, int age) {
        String ages= String.valueOf(age);
        Document document = new Document("Membership ID", id)
                .append("First Name", fName)
                .append("Last Name", lName)
                .append("Membership Start Date", date)
                .append("Age",ages);

        collection.insertOne(document);
    }
    //Searching for specific data in the database.
    public boolean getData(String id) {
        Document num = collection.find(eq("Membership ID", id))
                .first();
        if (num == null) {
            return true;
        }
        return false;
    }

    //Deleting Data in the Database.
    public void delData(String id) {
        collection.deleteOne(eq("Membership ID", id));
    }

    //Retrieving Data from Database and storing it in the arraylist.
    public void saveData(String name,int count,ArrayList list){
        int i = 1,j=1,k=1,l=1,m=1,n=1;
        // Getting the iterator
        Iterator<Document> it = collection.find().iterator();
        while (it.hasNext()) {
            list.add((String) it.next().get(name));
            count++;
        }
    }

    //Printing the Data from the arraylist.
    public void printData() {
        System.out.println("Membership ID"+"\t"+"First Name"+"\t\t"+"Last Name"+"\t"+"Start Date"+"\t\t"+"Age"+"\t\t"+"School Name");
        for (int i = 0; i < id.size(); i++) {
            System.out.println("\t"+id.get(i)+"\t\t"+fName.get(i)+"\t\t\t"+lName.get(i)+"\t\t"+date.get(i)+"\t\t"+age.get(i)+"\t\t"+sName.get(i));
        }
    }

    //File writing taking from arraylist.
    public void writeData(){
        PrintWriter pw = null;
        File file = new File("MyGYM Details.txt");
        try {
            pw = new PrintWriter(file);
            pw.println("Membership ID"+"\t"+"First Name"+"\t\t"+"Last Name"+"\t"+"Start Date"+"\t\t"+"Age"+"\t\t"+"School Name");
            for (int i = 0; i < id.size(); i++) {
                pw.println("\t"+id.get(i)+"\t\t"+fName.get(i)+"\t\t\t"+lName.get(i)+"\t\t"+date.get(i)+"\t\t"+age.get(i)+"\t\t"+sName.get(i));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } finally {
            try {
                pw.close();
            } catch (Exception e) {
                System.out.println("Something went wrong file closing the file streams");
            }
        }
        System.out.println("File Saved,Check MyGYM Details.txt");
    }

    //Getting sorted Database data
    public void sortData(String ascending) {
        if (Objects.equals(ascending, "yes")) {
            List<Document> SortedDocuments = (List<Document>) collection.find().sort(Sorts.ascending("First Name"))
                    .projection(fields(include("Membership ID", "First Name", "Last Name", "Membership Start Date", "School Name", "Age"), excludeId())).into(
                            new ArrayList<Document>());
            for (Document document2 : SortedDocuments) {
                System.out.println(document2);
            }
        } else {
            List<Document> SortedDocuments = (List<Document>) collection.find().sort(Sorts.descending("First Name"))
                    .projection(fields(include("Membership ID", "First Name", "Last Name", "Membership Start Date", "School Name", "Age"), excludeId())).into(
                            new ArrayList<Document>());
            for (Document document2 : SortedDocuments) {
                System.out.println(document2);
            }
        }
    }
}







