import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class SavingsCalculator extends Application {
    private static String five,six,seven,eight,nine;
    private static File file2;
    public static TextField payField2;
    private static TextField pField2,tField2,iField2,fField2;
    //handling with files using methods.
    static void fileHandling2(){
        try {
            five = pField2.getText();
            six = tField2.getText();
            seven = iField2.getText();
            eight = fField2.getText();
            nine = payField2.getText();
            if (Objects.equals(five, "")) {
                five = "0";
            }
            if (Objects.equals(six, "")) {
                six = "0";
            }
            if (Objects.equals(seven, "")) {
                seven = "0";
            }
            if (Objects.equals(eight, "")) {
                eight = "0";
            }
            if (Objects.equals(nine, "")) {
                nine = "0";
            }
            PrintWriter pw = null;
            file2 = new File("savings.txt");
            try {
                pw = new PrintWriter(file2);
                pw.println(five);
                pw.println(six);
                pw.println(seven);
                pw.println(eight);
                pw.println(nine);
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
            System.out.println("okay");
        }
    }
    static void getFileHandling2(){
        try {
            Scanner fileReader = new Scanner(file2);
            double pt = 0;
            double ie = 0;
            double te = 0;
            double fe = 0;
            double pay=0;

            while (fileReader.hasNextDouble()) {
                pt = fileReader.nextDouble();
                ie = fileReader.nextDouble();
                te = fileReader.nextDouble();
                fe = fileReader.nextDouble();
                pay = fileReader.nextDouble();
            }
            if (pt == 0 && ie == 0 && te == 0 && fe == 0) {
                System.out.println("Values not given");
            } else {
                pField2.setText(String.valueOf(pt));
                tField2.setText(String.valueOf(ie));
                iField2.setText(String.valueOf(te));
                fField2.setText(String.valueOf(fe));
                payField2.setText(String.valueOf(pay));
            }
        }catch (FileNotFoundException | NullPointerException e){
            System.out.println("File not Found");
        }
    }
    //methods to do calculations
    public double CalculateTime2(double paymentAmount,double futureValue,double interestRate){
        return Math.round((Math.log10((1+(((interestRate/100)*futureValue)/paymentAmount))/
                (Math.log10(1+(interestRate/100))*12))) * 10.0) / 10.0;
    }

    public double CalculateFutureValue2(double principalAmount,double time,double interestRate,double paymentAmount){
        final double easyCalculation =Math.round((Math.pow(1 + (interestRate / 1200), 12 * time)) * 100.0) / 100.0 ;
        double compoundInterest=Math.round((principalAmount* easyCalculation) * 100.0) / 100.0;
        double futureValueOfSeries=Math.round((paymentAmount*((easyCalculation -1 )/(interestRate/1200)))
                * 100.0) / 100.0;

        return compoundInterest+futureValueOfSeries ;
    }

    public double CalculatePaymentAmount(double futureValue,double time,double interestRate){
        final double easyCalculation = Math.pow(1 + (interestRate / 1200), 12 * time);
        return Math.round((futureValue/((easyCalculation -1 )/(interestRate/1200))) * 100.0) / 100.0;
    }

    public double CalculatePrincipalAmount(double futureValue,double time,double interestRate,double payment){
        final double easyCalculation = Math.pow(1 + (interestRate / 1200), 12 * time);
        return Math.round(((futureValue-(payment*((easyCalculation-1)/(interestRate/1200))))/easyCalculation)
                * 100.0) / 100.0;
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
        //creating a star label and adding tooltip to indicate its presence
        Label star=new Label("*");
        star.setLayoutX(338);
        star.setLayoutY(95);
        Label star2=new Label("*");
        star2.setLayoutX(338);
        star2.setLayoutY(193);
        Tooltip compulsory = new Tooltip();
        Tooltip compulsory2 = new Tooltip();
        compulsory2.setText("Only Needed To Calculate Future Value");
        compulsory.setText("Compulsory To Be Given");
        star.setTooltip(compulsory);
        star2.setTooltip(compulsory);
        //adding labels and textfields for required calculations.
        Label pLabel2=new Label("Principal Investment :");
        pLabel2.setLayoutX(41);
        pLabel2.setLayoutY(94);
        pLabel2.setMinSize(128,18);
        pField2=new TextField();
        pField2.setLayoutX(180);
        pField2.setLayoutY(86);
        pField2.setMinSize(152,32);

        pField2.setOnMouseClicked(event -> {
            VirtualKeyboard.changeField(pField2);
        });
        Label tLabel2=new Label("Period of Time :");
        tLabel2.setLayoutX(72);
        tLabel2.setLayoutY(139);
        tLabel2.setMinSize(94,18);
        tField2=new TextField();
        tField2.setPromptText("in Years");
        tField2.setLayoutX(180);
        tField2.setLayoutY(132);
        tField2.setMinSize(152,32);

        tField2.setOnMouseClicked(event -> {
            VirtualKeyboard.changeField(tField2);
        });
        Label iLabel2=new Label("Interest Rate :");
        iLabel2.setLayoutX(87);
        iLabel2.setLayoutY(189);
        iLabel2.setMinSize(79,18);
        iField2=new TextField();
        iField2.setLayoutX(180);
        iField2.setLayoutY(182);
        iField2.setMinSize(152,32);

        iField2.setOnMouseClicked(event -> {
            VirtualKeyboard.changeField(iField2);
        });
        Label fLabel2=new Label("Future Value :");
        fLabel2.setLayoutX(88);
        fLabel2.setLayoutY(245);
        fLabel2.setMinSize(82,18);
        fField2=new TextField();
        fField2.setLayoutX(180);
        fField2.setLayoutY(238);
        fField2.setMinSize(152,32);
        fField2.setEditable(false);

        fField2.setOnMouseClicked(event -> {
            VirtualKeyboard.changeField(fField2);
        });
        Label payLabel2=new Label("Payment Amount :");
        payLabel2.setLayoutX(61);
        payLabel2.setLayoutY(301);
        payLabel2.setMinSize(110,18);
        payField2=new TextField();
        payField2.setLayoutX(180);
        payField2.setLayoutY(294);
        payField2.setMinSize(152,32);
        payField2.setEditable(false);

        payField2.setOnMouseClicked(event -> {
            VirtualKeyboard.changeField(payField2);
        });

        Button btReset2=new Button("Reset");
        btReset2.setMinSize(54,32);
        btReset2.setLayoutX(195);
        btReset2.setLayoutY(368);

        Label results2 = new Label("Results\nPrincipal Investment:Rs.\nPeriod of Time:\nInterest Rate:\n" +
                "Future Value:            Rs.\nPayment Amount:     Rs.");
        results2.setMinSize(127, 139);
        results2.setLayoutY(429);
        results2.setLayoutX(175);
        results2.setFont(Font.font("Garamond",18));
        Label resultsViewer2 = new Label("");
        resultsViewer2.setMinSize(127, 139);
        resultsViewer2.setLayoutY(429);
        resultsViewer2.setLayoutX(345);
        resultsViewer2.setFont(Font.font("Garamond",18));
        pField2.setFocusTraversable(false);
        tField2.setFocusTraversable(false);
        iField2.setFocusTraversable(false);
        fField2.setFocusTraversable(false);
        payField2.setFocusTraversable(false);

        // A calculate button to do calculations
        Button btCalculate2=new Button("Calculate");
        btCalculate2.setMinSize(72,32);
        btCalculate2.setLayoutX(274);
        btCalculate2.setLayoutY(368);
        btCalculate2.setOnAction(event -> {
            String payAmount2=payField2.getText();
            String pAmount2=pField2.getText();
            String pTime2=tField2.getText();
            String iValue2=iField2.getText();
            String fValue2=fField2.getText();
            double interestRate2 =0;
            double time2=0;
            double futureValue2 =0;
            double paymentAmount2=0;
            double principalAmount2=  0;
            //getting textfield inputs and validating them for calculations.
            //passing through methods to calculate
            if(!Objects.equals(pAmount2, "") && !Objects.equals(pTime2, "") && !Objects.equals(iValue2, "")
                    && !Objects.equals(fValue2, "") && !Objects.equals(payAmount2, "")){

                alert2.show();
            }else if((Objects.equals(pAmount2, "")&& Objects.equals(fValue2, ""))
                    ||Objects.equals(pTime2, "")&& Objects.equals(fValue2, "")
                    ||Objects.equals(pTime2, "")&& Objects.equals(iValue2, "")
                    ||Objects.equals(pTime2, "")&& Objects.equals(payAmount2, "")
                    ||Objects.equals(iValue2, "")&& Objects.equals(fValue2, "")
                    ||Objects.equals(iValue2, "")&& Objects.equals(payAmount2, "")
                    ||Objects.equals(fValue2, "")&& Objects.equals(payAmount2, "")){

                alert.show();
            }else if(Objects.equals(pTime2, "")
                    ||(Objects.equals(pTime2, "")&& (Objects.equals(pAmount2, "")))){
                 interestRate2 = Double.parseDouble(iField2.getText());
                 futureValue2 = Double.parseDouble(fField2.getText());
                 paymentAmount2=Double.parseDouble(payField2.getText());
                 time2=CalculateTime2(paymentAmount2,futureValue2,interestRate2);
                alert3.setContentText("Time ="+ time2+" Year(s)");
                alert3.show();
            }else if(Objects.equals(fValue2, "")
                    ||(Objects.equals(fValue2, "")&& (Objects.equals(pAmount2, "")))){
                 paymentAmount2=Double.parseDouble(payField2.getText());
                 principalAmount2 = Double.parseDouble(pField2.getText());
                 time2= Double.parseDouble(tField2.getText());
                 interestRate2 = Double.parseDouble(iField2.getText());
                 futureValue2 =CalculateFutureValue2(principalAmount2,time2,interestRate2,paymentAmount2);
                alert3.setContentText("Future Value = Rs."+futureValue2);
                alert3.show();
            }else if(Objects.equals(payAmount2, "")
                    ||(Objects.equals(payAmount2, "")&& (Objects.equals(pAmount2, "")))){
                 interestRate2 = Double.parseDouble(iField2.getText());
                 time2=Double.parseDouble(tField2.getText());
                 futureValue2 =Double.parseDouble(fField2.getText());
                 paymentAmount2=CalculatePaymentAmount(futureValue2,time2,interestRate2);
                alert3.setContentText("Payment Amount = Rs."+paymentAmount2);
                alert3.show();
            }else if(Objects.equals(pAmount2, "")){
                 interestRate2 = Double.parseDouble(iField2.getText());
                 time2=Double.parseDouble(tField2.getText());
                 futureValue2 =Double.parseDouble(fField2.getText());
                 paymentAmount2=Double.parseDouble(payField2.getText());
                 principalAmount2=CalculatePrincipalAmount(futureValue2,time2,interestRate2,paymentAmount2);
                alert3.setContentText("Principal Amount = Rs."+principalAmount2);
                alert3.show();}
            else{
                alert3.setContentText("Interest rate cannot be left blank");
                alert3.show();
            }
            //viewing the results using a label
            resultsViewer2.setText("\n" + principalAmount2 + "\n" + time2+ " Years\n" + interestRate2 + " %\n"
                    + futureValue2+"\n" + paymentAmount2);

        });
        //Reset button
        btReset2.setOnAction(event -> {
            try{pField2.setText("");
            tField2.setText("");
            iField2.setText("");
            fField2.setText("");
            payField2.setText("");
            resultsViewer2.setText("");
            }catch (NullPointerException e){
                System.out.println("Throwing NullPointerException");
            }

        });

        getFileHandling2();
        Button btBack = new Button("Go Back");
        btBack.setMinSize(66, 32);
        btBack.setLayoutX(24);
        btBack.setLayoutY(577);
        btBack.setOnAction(e -> {
            fileHandling2();
            Calculator scene = new Calculator();
            try {
                scene.start(stage);
            } catch (Exception exception) {
                System.out.println("Sorry Class Not Found");
            }
        });
        //getting the keyboard pane from methods in Virtualkeyboard class
        Pane thisPane=new Pane();
        thisPane=VirtualKeyboard.myPane(thisPane);
        //getting the toolbar from the navigation class using methods
        ToolBar toolBar = new ToolBar();
        Navigation.getToolbar(toolBar,"savings", stage);
        //setting up anchorpane and stage to run the calculator
        AnchorPane root3 = new AnchorPane();
        root3.getChildren().addAll(payLabel2,tLabel2,iLabel2,fLabel2,pLabel2,payField2,pField2,tField2,iField2,fField2
                ,btReset2,star,star2,btCalculate2,thisPane,btBack,results2,resultsViewer2,toolBar);
        Scene scene3=new Scene(root3,714, 618);
        scene3.getStylesheets().add("layouts.css");

        stage.setTitle("Financial Calculator-Savings");

        stage.setScene(scene3);
        stage.show();
    }
}
