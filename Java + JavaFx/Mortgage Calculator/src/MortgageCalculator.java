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

public class MortgageCalculator extends Application {
    private static String fifteen,sixteen,seventeen,eighteen,nineteen;
    private static File file4;
    private static TextField pField4,dField4,tField4,iField4,payField4;
    //handling with files using methods.
    static void fileHandling4(){
        try {
            fifteen = pField4.getText();
            sixteen = dField4.getText();
            seventeen = tField4.getText();
            eighteen = iField4.getText();
            nineteen = payField4.getText();
            if (Objects.equals(fifteen, "")) {
                fifteen = "0";
            }
            if (Objects.equals(sixteen, "")) {
                sixteen = "0";
            }
            if (Objects.equals(seventeen, "")) {
                seventeen = "0";
            }
            if (Objects.equals(eighteen, "")) {
                eighteen = "0";
            }
            if (Objects.equals(nineteen, "")) {
                nineteen = "0";
            }
            PrintWriter pw = null;
            file4 = new File("mortgage.txt");
            try {
                pw = new PrintWriter(file4);
                pw.println(fifteen);
                pw.println(sixteen);
                pw.println(seventeen);
                pw.println(eighteen);
                pw.println(nineteen);
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
    static void getFileHandling4(){
        try {
            Scanner fileReader3 = new Scanner(file4);
            double pt4 = 0;
            double dp4=0;
            double ie4 = 0;
            double te4 = 0;
            double fe4 = 0;


            while (fileReader3.hasNextDouble()) {
                pt4 = fileReader3.nextDouble();
                dp4=fileReader3.nextDouble();
                te4= fileReader3.nextDouble();
                ie4 = fileReader3.nextDouble();
                fe4 = fileReader3.nextDouble();

            }
            if (pt4 == 0 && ie4 == 0 && te4 == 0 && fe4 == 0 && dp4 == 0) {
                System.out.println("Values not given");
            } else {
                pField4.setText(String.valueOf(pt4));
                tField4.setText(String.valueOf(te4));
                iField4.setText(String.valueOf(ie4));
                payField4.setText(String.valueOf(fe4));
                dField4.setText(String.valueOf(dp4));
            }
        }catch (FileNotFoundException | NullPointerException e){
            System.out.println("File not Found");
        }
    }
    //method to do calculation but some are same as loan calculator calculations .
    public static double CalculateDownPaymentAmount4(double loanTerm,double paymentAmount,double interestRate
            ,double propertyPrice){
        final double easyCalculation = Math.pow(1 + (interestRate / 1200), 12 * loanTerm);
        return Math.round((((paymentAmount*(easyCalculation-1))/(interestRate*easyCalculation/1200))-propertyPrice)
                * 100.0) / 100.0;
    }
    @Override
    public void start(Stage stage) throws Exception {
        //alert boxes and tooltip for guidance
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert");
        alert.setContentText("ONLY ONE FIELD CAN BE LEFT OUT!");
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Alert");
        alert2.setContentText("All 4 Fields have been filled!!");
        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
        alert3.setTitle("Results");

        Tooltip compulsory = new Tooltip();
        compulsory.setText("Compulsory To Be Given");
        Label star4=new Label("*");
        star4.setLayoutX(338);
        star4.setLayoutY(247);
        star4.setTooltip(compulsory);
        //labels and textfields for required calculations.
        Label pLabel4=new Label("Property Price :");
        pLabel4.setLayoutY(105);
        pLabel4.setLayoutX(72);
        pLabel4.setMinSize(91,18);
        pField4=new TextField();
        pField4.setLayoutY(98);
        pField4.setLayoutX(180);
        pField4.setMinSize(152,32);
        pField4.setOnMouseClicked(event -> {
            VirtualKeyboard.changeField(pField4);
        });

        Label dLabel4=new Label("Down Payment :");
        dLabel4.setLayoutY(155);
        dLabel4.setLayoutX(68);
        dLabel4.setMinSize(96,18);
        dField4=new TextField();
        dField4.setLayoutY(148);
        dField4.setLayoutX(180);
        dField4.setMinSize(152,32);
        dField4.setOnMouseClicked(event -> {
            VirtualKeyboard.changeField(dField4);
        });
        Label tLabel4=new Label("Loan Term :");
        tLabel4.setLayoutY(200);
        tLabel4.setLayoutX(92);
        tLabel4.setMinSize(71,18);
        tField4=new TextField();
        tField4.setPromptText("in Years");
        tField4.setLayoutY(193);
        tField4.setLayoutX(180);
        tField4.setMinSize(152,32);
        tField4.setOnMouseClicked(event -> {
            VirtualKeyboard.changeField(tField4);
        });

        Label iLabel4=new Label("Interest Rate :");
        iLabel4.setLayoutY(244);
        iLabel4.setLayoutX(83);
        iLabel4.setMinSize(82,18);
        iField4=new TextField();
        iField4.setLayoutY(237);
        iField4.setLayoutX(180);
        iField4.setMinSize(152,32);
        iField4.setOnMouseClicked(event -> {
            VirtualKeyboard.changeField(iField4);
        });

        Label payLabel4=new Label("Payment :");
        payLabel4.setLayoutY(295);
        payLabel4.setLayoutX(105);
        payLabel4.setMinSize(59,18);
        payField4=new TextField();
        payField4.setLayoutY(288);
        payField4.setLayoutX(178);
        payField4.setMinSize(152,32);
        payField4.setOnMouseClicked(event -> {
            VirtualKeyboard.changeField(payField4);
        });

        Button btReset4=new Button("Reset");
        btReset4.setMinSize(54,32);
        btReset4.setLayoutX(195);
        btReset4.setLayoutY(368);
        btReset4.setOnAction(event -> {
            pField4.setText("");
            dField4.setText("");
            iField4.setText("");
            tField4.setText("");
            payField4.setText("");
        });
        PieChart pieChart4 = new PieChart();
        pieChart4.setLayoutX(462);
        pieChart4.setLayoutY(417);
        pieChart4.setMaxSize(250, 200);
        pieChart4.setLegendVisible(false);
        pieChart4.setLabelLineLength(3);

        Label results4 = new Label("Results\nProperty Price:          Rs.\nDown Payment:        Rs.\nLoan Term:\n" +
                "No.of Payments(months):\nInterest Rate:\nPayment Amount:     Rs.");
        results4.setMinSize(127, 139);
        results4.setLayoutY(429);
        results4.setLayoutX(160);
        results4.setFont(Font.font("Garamond",18));
        Label resultsViewer4 = new Label("");
        resultsViewer4.setMinSize(127, 139);
        resultsViewer4.setLayoutY(429);
        resultsViewer4.setLayoutX(345);
        resultsViewer4.setFont(Font.font("Garamond",18));
        pField4.setFocusTraversable(false);
        tField4.setFocusTraversable(false);
        iField4.setFocusTraversable(false);
        payField4.setFocusTraversable(false);
        dField4.setFocusTraversable(false);
        //calculate button
        Button btCalculate4=new Button("Calculate");
        btCalculate4.setMinSize(72,32);
        btCalculate4.setLayoutX(274);
        btCalculate4.setLayoutY(368);
        btCalculate4.setOnAction(event -> {
            String pPrice4=pField4.getText();
            String dPayment4=dField4.getText();
            String iValue4=iField4.getText();
            String payAmount4=payField4.getText();
            String loanTerm4=tField4.getText();
            double interestRate4 = 0;
            double pAmount4 =0;
            double lTerm4=0;
            double downPayment4=0;
            double paymentAmount4= 0;
            //getting textfield inputs and validating them for calculations.
            //passing through methods to calculate
            pieChart4.setVisible(true);
            if(!Objects.equals(pPrice4, "") && !Objects.equals(dPayment4, "") && !Objects.equals(iValue4, "")
                    && !Objects.equals(payAmount4, "") && !Objects.equals(loanTerm4, "")){
                pieChart4.setVisible(false);
                alert2.show();
            }else if(Objects.equals(pPrice4, "") && Objects.equals(dPayment4, "")
                    ||Objects.equals(pPrice4, "") && Objects.equals(iValue4, "")
                    || Objects.equals(pPrice4, "") && Objects.equals(payAmount4, "")
                    ||Objects.equals(pPrice4, "") && Objects.equals(loanTerm4, "")
                    ||Objects.equals(dPayment4, "") && Objects.equals(iValue4, "")
                    ||Objects.equals(dPayment4, "") && Objects.equals(payAmount4, "")
                    ||Objects.equals(dPayment4, "") && Objects.equals(loanTerm4, "")
                    ||Objects.equals(iValue4, "") && Objects.equals(payAmount4, "")
                    ||Objects.equals(iValue4, "") && Objects.equals(loanTerm4, "")
                    ||Objects.equals(payAmount4, "") && Objects.equals(loanTerm4, "")){
                pieChart4.setVisible(false);
                alert.show();
            }else if(Objects.equals(payAmount4, "")){
                interestRate4 = Double.parseDouble(iField4.getText());
                pAmount4 = Double.parseDouble(pField4.getText());
                lTerm4=Double.parseDouble(tField4.getText());
                downPayment4=Double.parseDouble(dField4.getText());
                paymentAmount4= LoanCalculator.CalculatePaymentAmount2(pAmount4-downPayment4,lTerm4
                        ,interestRate4);
                alert3.setContentText("Payment = Rs."+paymentAmount4);
                alert3.show();
            }else if(Objects.equals(loanTerm4, "")){
                interestRate4 = Double.parseDouble(iField4.getText());
                pAmount4 = Double.parseDouble(pField4.getText());
                paymentAmount4=Double.parseDouble(payField4.getText());
                downPayment4=Double.parseDouble(dField4.getText());
                lTerm4= LoanCalculator.CalculateLoanTerm(pAmount4-downPayment4,paymentAmount4,interestRate4);
                alert3.setContentText("Loan Term ="+lTerm4+"Year(s)");
                alert3.show();
            }else if(Objects.equals(pPrice4, "")){
                interestRate4 = Double.parseDouble(iField4.getText());
                lTerm4=Double.parseDouble(tField4.getText());
                paymentAmount4=Double.parseDouble(payField4.getText());
                downPayment4=Double.parseDouble(dField4.getText());
                pAmount4 = LoanCalculator.CalculateLoanAmount(lTerm4,paymentAmount4,interestRate4)+downPayment4;
                alert3.setContentText("Property Price = Rs."+pAmount4);
                alert3.show();
            }else if(Objects.equals(dPayment4, "")){
                interestRate4 = Double.parseDouble(iField4.getText());
                lTerm4=Double.parseDouble(tField4.getText());
                paymentAmount4=Double.parseDouble(payField4.getText());
                pAmount4 = Double.parseDouble(pField4.getText());
                downPayment4=CalculateDownPaymentAmount4(lTerm4,paymentAmount4,interestRate4,pAmount4);
                alert3.setContentText("Down Payment = Rs."+downPayment4);
                alert3.show();
            }else if(Objects.equals(iValue4, "")){
                alert3.setContentText("Interest rate cannot be left blank");
                alert3.show();
            }
            double futureValue=paymentAmount4*12*lTerm4;
            //adding data to piechart
            ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
                    new PieChart.Data("Property Price",pAmount4 ),
                    new PieChart.Data("Interest", Math.round((pAmount4-futureValue) * 100.0) / 100.0)
            );
            pieChart4.setData(list);
            for (final PieChart.Data data : pieChart4.getData()) {
                data.getNode().addEventHandler(MouseEvent.ANY, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Tooltip.install(data.getNode(), new Tooltip("Rs." + data.getPieValue()));
                    }
                });
            }
            resultsViewer4.setText("\n" + pAmount4 + "\n" + downPayment4 + "\n" + lTerm4 + " Year(s)\n" +
                    Math.round((pAmount4-downPayment4)/paymentAmount4)  + "\n" + interestRate4 +" %\n" + paymentAmount4);
        });
        getFileHandling4();
        Button btBack = new Button("Go Back");
        btBack.setMinSize(66, 32);
        btBack.setLayoutX(24);
        btBack.setLayoutY(577);
        btBack.setOnAction(e -> {
            fileHandling4();
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
        Navigation.getToolbar(toolBar,"mortgage",stage);
        //setting up anchorpane and stage to run the calculator
        AnchorPane root5 = new AnchorPane();
        Scene scene5=new Scene(root5,714, 618);
        scene5.getStylesheets().add("layouts.css");
        root5.getChildren().addAll(star4,pLabel4,pField4,toolBar,thisPane,dLabel4,dField4,tLabel4,tField4,iLabel4,
                iField4,payField4,payLabel4,btCalculate4,btReset4, pieChart4, results4, resultsViewer4,btBack);

        stage.setTitle("Financial Calculator-Mortgage");
        stage.setScene(scene5);
        stage.show();

    }
}
