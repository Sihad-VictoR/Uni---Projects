package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.bson.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainGUI extends Application {
    public void printSomething() {
        System.out.println("Opening GUI for GYM MANAGER Application.........");
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(MainGUI.class);
            }
        }.start();
    }

    //observable list to store data
    private final ObservableList<member> dataList = FXCollections.observableArrayList();
    //ArrayLists to store data taken from database.
    ArrayList<String> id = new ArrayList<String>();
    ArrayList<String> fName = new ArrayList<String>();
    ArrayList<String> lName = new ArrayList<String>();
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<String> age = new ArrayList<String>();
    ArrayList<String> sName = new ArrayList<String>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Creating a table view to show a table.
        TableView tableView=new TableView();
        tableView.setMinSize(765,472);
        //Creating table columns and designing the layout.
        TableColumn<String,member> column1 = new TableColumn<>("Membership ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("MemberID"));
        column1.setMinWidth(119);

        TableColumn<String,member> column2 = new TableColumn<>("First Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        column2.setMinWidth(116);

        TableColumn<String,member> column3 = new TableColumn<>("Last Name");
        column3.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        column3.setMinWidth(116);

        TableColumn<String,member> column4 = new TableColumn<>("Start Date");
        column4.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        column4.setMinWidth(128);

        TableColumn<String,member> column5 = new TableColumn<>("Age");
        column5.setCellValueFactory(new PropertyValueFactory<>("mAge"));
        column5.setMinWidth(116);

        TableColumn<String,member> column6 = new TableColumn<>("School Name");
        column6.setCellValueFactory(new PropertyValueFactory<>("schoolName"));
        column6.setMinWidth(171);

        tableView.getColumns().addAll(column1,column2,column3,column4,column5,column6);

        //Designing the layout and adding a textfield for the search option.
        Label myGym=new Label("Search  :");
        myGym.setMinSize(118,50);
        myGym.setLayoutX(400);
        myGym.setLayoutY(8);

        TextField filterField=new TextField();
        filterField.setLayoutX(476);
        filterField.setLayoutY(15);
        filterField.setMaxSize(270,40);
        //getting data from database using a method
        int i = 1,j=1,k=1,l=1,m=1,n=1;
        saveData("Membership ID",i,id);
        saveData("First Name",j,fName);
        saveData("Last Name",k,lName);
        saveData("Membership Start Date",l,date);
        saveData("Age",m,age);
        saveData("School Name",n,sName);
        read();

        // Wrap the ObservableList in a FilteredList (initially displaying all data).
        FilteredList<member> filteredData = new FilteredList<>(dataList, b -> true);

        // Setting the filter Predicate whenever the filter changes.(listener)
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(member -> {
                // If filter text is empty, displaying all members in the list

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (member.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (member.getMemberID().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches memberId name.
                }else if (member.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if (member.getSchoolName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches School name.
                }
                else if (member.getStartDate().contains(lowerCaseFilter))
                    return true;  // Filter matches Start Date.
                else
                    return false; // Does not match.
            });
        });

        // Wrap the FilteredList in a SortedList.
        SortedList<member> sortedData = new SortedList<>(filteredData);

        // Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        // Add sorted (and filtered) data to the table.
        tableView.setItems(sortedData);


        //creating a stack pane and adding table view to the stack pane
        StackPane sPane=new StackPane();
        sPane.getChildren().add(tableView);
        sPane.setMinSize(768,471);
        sPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        sPane.setLayoutY(71);
        //adding stack pane and other elements to anchor pane
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: #D8BFD8;");
        root.getChildren().addAll(sPane,filterField,myGym);
        //Creating scene and showing stage.
        Scene scene1 = new Scene(root, 765, 551);
        primaryStage.setScene(scene1);
        primaryStage.setResizable(false);
        primaryStage.setTitle("My Gym Manager");
        primaryStage.show();
    }
    public ObservableList<member> read() throws IOException, ClassNotFoundException {
        //adding details to observable list
        for (int i = 0; i < id.size(); i++) {
            member emp1 = new member(String.valueOf(id.get(i)),String.valueOf(fName.get(i)),String.valueOf(lName.get(i)),String.valueOf(date.get(i)),String.valueOf(age.get(i)),String.valueOf(sName.get(i)));
            dataList.add(emp1);
        }
        return FXCollections.observableArrayList(dataList);
    }
    public void saveData(String name,int count,ArrayList list){
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.OFF);
        Database db=new Database();
        int i = 1,j=1,k=1,l=1,m=1,n=1;
        // Getting the iterator
        Iterator<Document> it = db.collection.find().iterator();
        while (it.hasNext()) {
            list.add((String) it.next().get(name));
            count++;
        }
    }

}



