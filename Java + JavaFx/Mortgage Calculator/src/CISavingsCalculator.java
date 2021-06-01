import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;


public class CISavingsCalculator extends Application {
    public  static  File mainFile;
    private static String one,two,three,four;
    private static File file;
    private static TextField pField,iField,tField,fField;
    //handling with files using methods.
    static void fileHandling(){
        try {
            one = pField.getText();
            two = tField.getText();
            three = iField.getText();
            four = fField.getText();
            if (Objects.equals(one, "")) {
                one = "0";
            }
            if (Objects.equals(two, "")) {
                two = "0";
            }
            if (Objects.equals(three, "")) {
                three = "0";
            }
            if (Objects.equals(four, "")) {
                four = "0";
            }
            PrintWriter pw = null;
            file = new File("CISavings.txt");
            try {
                pw = new PrintWriter(file);
                pw.println(one);
                pw.println(two);
                pw.println(three);
                pw.println(four);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } finally {
                try {
                    pw.close();
                } catch (Exception e) {
                    System.out.println("Something went wrong file closing the file streams");
                }
            }
        }catch (NullPointerException e){
            System.out.println("Catching Null point Exception");
        }
    }
    static void getFileHandling(){
        try {
            Scanner fileReader = new Scanner(file);
            double pt = 0;
            double ie = 0;
            double te = 0;
            double fe = 0;

            while (fileReader.hasNextDouble()) {
                pt = fileReader.nextDouble();
                ie = fileReader.nextDouble();
                te = fileReader.nextDouble();
                fe = fileReader.nextDouble();
            }
            if (pt == 0 && ie == 0 && te == 0 && fe == 0) {
                System.out.println("Values not given");
            } else {
                CISavingsCalculator.pField.setText(String.valueOf(pt));
                CISavingsCalculator.tField.setText(String.valueOf(ie));
                CISavingsCalculator.iField.setText(String.valueOf(te));
                CISavingsCalculator.fField.setText(String.valueOf(fe));
            }
        }catch (FileNotFoundException | NullPointerException e){
            System.out.println("File not Found");
        }
    }
    //methods to do calculations
    public double CalculatePAmount(double futureValue,double interestRate,double time){
        return Math.round(futureValue/Math.pow((1+(interestRate/1200)),12*time) * 100.0) / 100.0;

    }
    public double CalculateIRate(double principalAmount,double futureValue,double time){
        return Math. round(1200*(Math.pow(futureValue/principalAmount,1/(12*time))-1) * 100.0) / 100.0;

    }
    public double CalculateTime(double principalAmount,double futureValue,double interestRate){
        return Math.round(Math.log10(futureValue/principalAmount)/(12*Math.log10(1+(interestRate/1200)))* 10.0) / 10.0;
    }
    public double CalculateFutureValue(double principalAmount,double time,double interestRate){
        return Math.round(principalAmount*Math.pow(1+(interestRate/1200),12*time)* 100.0) / 100.0 ;

    }
    @Override
    public void start(Stage stage) throws Exception {
        //setting up alert boxes
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert");
        alert.setContentText("ONLY ONE FIELD CAN BE LEFT OUT!");
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Alert");
        alert2.setContentText("All 4 Fields have been filled!!");
        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
        alert3.setTitle("Results");
        //designing the layout..
        //adding necessary  Texfields and buttons
        Label pLabel = new Label("Principal Investment :");
        pLabel.setLayoutX(42);
        pLabel.setLayoutY(140);
        pLabel.setMinSize(128, 18);
        pField = new TextField();
        pField.setLayoutX(180);
        pField.setLayoutY(132);
        pField.setMinSize(152, 32);

        pField.setOnMouseClicked(event -> {
            VirtualKeyboard.changeField(pField);
        });

        Label tLabel = new Label("Period of Time :");
        tLabel.setLayoutX(76);
        tLabel.setLayoutY(189);
        tLabel.setMinSize(94, 18);

        tField = new TextField();
        tField.setPromptText("in years");
        tField.setLayoutX(180);
        tField.setLayoutY(182);
        tField.setMinSize(152, 32);

        tField.setOnMouseClicked(event -> {
            VirtualKeyboard.changeField(tField);
        });


        Label iLabel = new Label("Interest Rate :");
        iLabel.setLayoutX(92);
        iLabel.setLayoutY(245);
        iLabel.setMinSize(79, 18);
        iField = new TextField();
        iField.setLayoutX(180);
        iField.setLayoutY(238);
        iField.setMinSize(152, 32);

        iField.setOnMouseClicked(event -> {
            VirtualKeyboard.changeField(iField);
        });


        Label fLabel = new Label("Future Value :");
        fLabel.setLayoutX(90);
        fLabel.setLayoutY(301);
        fLabel.setMinSize(82, 18);
        fField = new TextField();
        fField.setLayoutX(180);
        fField.setLayoutY(294);
        fField.setMinSize(152, 32);

        fField.setOnMouseClicked(event -> {
            VirtualKeyboard.changeField(fField);
        });
        //Creating a piechart.
        PieChart pieChart = new PieChart();
        pieChart.setLayoutX(462);
        pieChart.setLayoutY(417);
        pieChart.setMaxSize(250, 200);
        pieChart.setLegendVisible(false);
        pieChart.setLabelLineLength(3);

        Label results = new Label("Results\nPrincipal Investment:Rs.\nPeriod of Time:\nInterest Rate:\n" +
                "Future Value:            Rs.");
        results.setMinSize(127, 139);
        results.setLayoutY(429);
        results.setLayoutX(150);
        results.setId("results");
        results.setFont(Font.font("Garamond",18));
        Label resultsViewer = new Label("");
        resultsViewer.setMinSize(127, 139);
        resultsViewer.setLayoutY(429);
        resultsViewer.setLayoutX(320);
        resultsViewer.setId("results");
        resultsViewer.setFont(Font.font("Garamond",18));
        //setting focus false because of editable purposes.
        pField.setFocusTraversable(false);
        tField.setFocusTraversable(false);
        iField.setFocusTraversable(false);
        fField.setFocusTraversable(false);
        //creating a calculate button
        Button btCalculate = new Button("Calculate");
        btCalculate.setMinSize(72, 32);
        btCalculate.setLayoutX(274);
        btCalculate.setLayoutY(368);
        btCalculate.setOnAction(event -> {
            String pAmount = pField.getText();
            String pTime = tField.getText();
            String iValue = iField.getText();
            String fValue = fField.getText();
            double interestRate = 0;
            double futureValue = 0;
            double principalAmount = 0;
            double time = 0;
            pieChart.setVisible(true);
            //getting textfield inputs and validating them for calculations.
            //passing through methods to calculate
            if (!Objects.equals(pAmount, "") && !Objects.equals(pTime, "") && !Objects.equals(iValue, "")
                    && !Objects.equals(fValue, "")) {
                pieChart.setVisible(false);
                alert2.show();
            } else if ((Objects.equals(pAmount, "") && Objects.equals(pTime, ""))
                    || (Objects.equals(pAmount, "") && Objects.equals(iValue, ""))
                    || (Objects.equals(pAmount, "") && Objects.equals(fValue, ""))
                    || Objects.equals(pTime, "") && Objects.equals(fValue, "")
                    || Objects.equals(pTime, "") && Objects.equals(iValue, "")
                    || Objects.equals(iValue, "") && Objects.equals(fValue, "")) {
                pieChart.setVisible(false);
                alert.show();
            } else if (Objects.equals(pAmount, "")) {
                interestRate = Double.parseDouble(iField.getText());
                futureValue = Double.parseDouble(fField.getText());
                time = Double.parseDouble(tField.getText());
                principalAmount = CalculatePAmount(futureValue, interestRate, time);
                alert3.setContentText("Principal Amount =  Rs." + principalAmount);
                alert3.show();
            } else if (Objects.equals(pTime, "")) {
                interestRate = Double.parseDouble(iField.getText());
                futureValue = Double.parseDouble(fField.getText());
                principalAmount = Double.parseDouble(pField.getText());
                time = CalculateTime(principalAmount, futureValue, interestRate);
                alert3.setContentText("Time =  " + time +" Year(s)");
                alert3.show();
            } else if (Objects.equals(iValue, "")) {
                futureValue = Double.parseDouble(fField.getText());
                principalAmount = Double.parseDouble(pField.getText());
                time = Double.parseDouble(tField.getText());
                interestRate = CalculateIRate(principalAmount, futureValue, time);
                alert3.setContentText("Interest Rate =  " + interestRate+" %");
                alert3.show();
            } else if (Objects.equals(fValue, "")) {
                interestRate = Double.parseDouble(iField.getText());
                principalAmount = Double.parseDouble(pField.getText());
                time = Double.parseDouble(tField.getText());
                futureValue = CalculateFutureValue(principalAmount, time, interestRate);
                alert3.setContentText("Future Value =  Rs." + futureValue);
                alert3.show();
            }

            ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
                    new PieChart.Data("Principal", principalAmount),
                    new PieChart.Data("Interest", Math.round((futureValue - principalAmount) * 100.0) / 100.0)
            );
            //setting data to piechart inside the calculate button
            pieChart.setData(list);
            for (final PieChart.Data data : pieChart.getData()) {
                data.getNode().addEventHandler(MouseEvent.ANY, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Tooltip.install(data.getNode(), new Tooltip("Rs." + data.getPieValue()));
                    }
                });
            }
            //viewing the results using a label
            resultsViewer.setText("\n" + principalAmount + "\n" + time + " Year(s)\n" + interestRate + " %\n" + futureValue);

        });
        //a reset button to set everything back
        Button btReset = new Button("Reset");
        btReset.setMinSize(54, 32);
        btReset.setLayoutX(195);
        btReset.setLayoutY(368);
        btReset.setOnAction(event -> {
            try {
                pField.setText("");
                tField.setText("");
                iField.setText("");
                fField.setText("");
                resultsViewer.setText("");
                pieChart.setData(null);
            }catch (NullPointerException e){
                System.out.println("Throwing NullPointerException");
            }
        });
        getFileHandling();
        Button btBack = new Button("Go Back");
        btBack.setMinSize(66, 32);
        btBack.setLayoutX(24);
        btBack.setLayoutY(577);
        btBack.setOnAction(e -> {
            fileHandling();
            Calculator scene = new Calculator();
            try {
                scene.start(stage);
            } catch (Exception exception) {
                System.out.println("Sorry Class Not Found");
            }
        });
        //getting the keyboard pane from methods in Virtualkeyboard class
        Pane thisPane = new Pane();
        thisPane = VirtualKeyboard.myPane(thisPane);
        //getting the toolbar from the navigation class using methods
        ToolBar toolBar = new ToolBar();
        Navigation.getToolbar(toolBar,"compound", stage);

        //setting up anchorpane and stage to run the calculator
        AnchorPane root2 = new AnchorPane();
        Scene scene2 = new Scene(root2, 700, 618);
        scene2.getStylesheets().add("layouts.css");
        root2.getChildren().addAll(pLabel, tLabel, iLabel, fLabel, pField, tField, iField, fField, btCalculate
                , btReset, thisPane, btBack, pieChart, results, resultsViewer,toolBar);
        stage.setTitle("Financial Calculator-Compound Interest Savings");
        stage.setScene(scene2);
        stage.show();

    }
}
