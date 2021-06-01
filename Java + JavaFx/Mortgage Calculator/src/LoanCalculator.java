import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class LoanCalculator extends Application {
    private static String ten,eleven,twelve,thirteen,fourteen;
    private static File file3;
    private static TextField aField3,tField3,iField3,payField3;
    private static MenuButton payBack3;
    //handling with files using methods.
    static void fileHandling3(){
        try {
            ten = aField3.getText();
            eleven = tField3.getText();
            twelve = iField3.getText();
            thirteen = payField3.getText();

            if (Objects.equals(ten, "")) {
                ten = "0";
            }
            if (Objects.equals(eleven, "")) {
                eleven = "0";
            }
            if (Objects.equals(twelve, "")) {
                twelve = "0";
            }
            if (Objects.equals(thirteen, "")) {
                thirteen = "0";
            }
            PrintWriter pw = null;
            file3 = new File("Loan.txt");
            try {
                pw = new PrintWriter(file3);
                pw.println(ten);
                pw.println(eleven);
                pw.println(twelve);
                pw.println(thirteen);
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
    static void getFileHandling3(){
        try {
            Scanner fileReader3 = new Scanner(file3);
            double pt3 = 0;
            double ie3 = 0;
            double te3 = 0;
            double fe3 = 0;
            while (fileReader3.hasNextDouble()) {
                pt3 = fileReader3.nextDouble();
                te3= fileReader3.nextDouble();
                ie3 = fileReader3.nextDouble();
                fe3 = fileReader3.nextDouble();
            }
            if (pt3 == 0 && ie3 == 0 && te3 == 0 && fe3 == 0) {
                System.out.println("Values not given");
            } else {
                aField3.setText(String.valueOf(pt3));
                tField3.setText(String.valueOf(te3));
                iField3.setText(String.valueOf(ie3));
                payField3.setText(String.valueOf(fe3));
            }
        }catch (FileNotFoundException | NullPointerException e){
            System.out.println("File not Found");
        }
    }
    //methods to do calculations
    public static double CalculatePaymentAmount2(double loanAmount,double loanTerm,double interestRate){
        final double easyCalculation = Math.pow(1 + (interestRate / 1200), 12 * loanTerm);
        return Math.round(((loanAmount*(interestRate/1200)* easyCalculation)/((easyCalculation -1))) * 100.0) / 100.0;
    }
    public static  double CalculateLoanTerm(double loanAmount,double paymentAmount,double interestRate){
        return Math.round((((-Math.log10(1-(interestRate/100)/12*loanAmount/paymentAmount))/
                (Math.log10(1+((interestRate/100)/12))))/12) * 10.0) / 10.0;
    }
    public static double CalculateLoanAmount(double loanTerm,double paymentAmount,double interestRate){
        final double easyCalculation = Math.pow(1 + (interestRate / 1200), 12 * loanTerm);
        return Math.round((((paymentAmount*(easyCalculation-1))/(interestRate*easyCalculation/1200))) * 100.0) / 100.0;
    }

    @Override
    public void start(Stage stage) throws Exception {
        {
            //alert boxes to prompt results and tooltip for guidance
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
            Label star3=new Label("*");
            star3.setLayoutX(338);
            star3.setLayoutY(193);
            star3.setTooltip(compulsory);
            //labels and texfields for required calculations.
            Label aLabel3=new Label("Loan Amount :");
            aLabel3.setLayoutY(84);
            aLabel3.setLayoutX(79);
            aLabel3.setMinSize(87,18);
            aField3=new TextField();
            aField3.setLayoutY(77);
            aField3.setLayoutX(180);
            aField3.setMinSize(152,32);
            aField3.setOnMouseClicked(event -> {
                VirtualKeyboard.changeField(aField3);
            });

            Label tLabel3=new Label("Loan Term :");
            tLabel3.setLayoutY(134);
            tLabel3.setLayoutX(94);
            tLabel3.setMinSize(71,18);
            tField3=new TextField();
            tField3.setPromptText("in Years");
            tField3.setLayoutY(127);
            tField3.setLayoutX(180);
            tField3.setMinSize(152,32);
            tField3.setOnMouseClicked(event -> {
                VirtualKeyboard.changeField(tField3);
            });

            Label iLabel3=new Label("Interest Rate :");
            iLabel3.setLayoutY(190);
            iLabel3.setLayoutX(84);
            iLabel3.setMinSize(82,18);
            iField3=new TextField();
            iField3.setLayoutY(183);
            iField3.setLayoutX(180);
            iField3.setMinSize(152,32);
            iField3.setOnMouseClicked(event -> {
                VirtualKeyboard.changeField(iField3);
            });

            Label payLabel3=new Label("Payment :");
            payLabel3.setLayoutY(241);
            payLabel3.setLayoutX(105);
            payLabel3.setMinSize(59,18);
            payField3=new TextField();
            payField3.setLayoutY(234);
            payField3.setLayoutX(180);
            payField3.setMinSize(152,32);
            payField3.setOnMouseClicked(event -> {
                VirtualKeyboard.changeField(payField3);
            });

            Button btReset3=new Button("Reset");
            btReset3.setMinSize(54,32);
            btReset3.setLayoutX(195);
            btReset3.setLayoutY(368);
            Label payBackLabel=new Label("Pay Back :");
            payBackLabel.setLayoutX(107);
            payBackLabel.setLayoutY(301);
            payBackLabel.setMinSize(62,18);
            payBack3=new MenuButton("Every Month");
            MenuItem m1=new MenuItem("Every Month");
            MenuItem m2=new MenuItem("Every Year");
            payBack3.getItems().addAll(m1,m2);
            payBack3.setLayoutX(179);
            payBack3.setLayoutY(298);
            payBack3.setMinSize(120,28);
            EventHandler<ActionEvent> menuChange= event -> {
                payBack3.setText(((MenuItem)event.getSource()).getText());
            };
            m1.setOnAction(menuChange);
            m2.setOnAction(menuChange);

            //creating piechart
            PieChart pieChart3 = new PieChart();
            pieChart3.setLayoutX(462);
            pieChart3.setLayoutY(417);
            pieChart3.setMaxSize(250, 200);
            pieChart3.setLegendVisible(false);
            pieChart3.setLabelLineLength(3);

            Label results3 = new Label("Results\nLoan Amount:            Rs.\nPeriod of Time:\nInterest Rate:\n" +
                    "No.of Payments(months):\nPayment Amount:       Rs.");
            results3.setMinSize(127, 139);
            results3.setLayoutY(429);
            results3.setLayoutX(150);
            results3.setFont(Font.font("Garamond",18));
            Label resultsViewer3 = new Label("");
            resultsViewer3.setMinSize(127, 139);
            resultsViewer3.setLayoutY(429);
            resultsViewer3.setLayoutX(335);
            resultsViewer3.setFont(Font.font("Garamond",18));
            aField3.setFocusTraversable(false);
            tField3.setFocusTraversable(false);
            iField3.setFocusTraversable(false);
            payField3.setFocusTraversable(false);
            //calculate button
            Button btCalculate3=new Button("Calculate");
            btCalculate3.setMinSize(72,32);
            btCalculate3.setLayoutX(274);
            btCalculate3.setLayoutY(368);
            btCalculate3.setOnAction(event -> {
                String lAmount3=aField3.getText();
                String lTime3=tField3.getText();
                String iValue3=iField3.getText();
                String payAmount3=payField3.getText();
                String menu3=payBack3.getText();

                double interestRate3 = 0;
                double loanAmount3 = 0;
                double loanTerm3=0;
                double paymentAmount3=0;
                pieChart3.setVisible(true);
                //getting textfield inputs and validating them for calculations.
                //passing through methods to calculate
                if(!Objects.equals(lAmount3, "") && !Objects.equals(lTime3, "") && !Objects.equals(iValue3, "")
                        && !Objects.equals(payAmount3, "")){
                    pieChart3.setVisible(false);
                    alert2.show();
                }else if(Objects.equals(lAmount3, "") && Objects.equals(lTime3, "")
                        ||Objects.equals(lAmount3, "") && Objects.equals(iValue3, "")
                        ||Objects.equals(lAmount3, "") && Objects.equals(payAmount3, "")
                        ||Objects.equals(lTime3, "") && Objects.equals(iValue3, "")
                        ||Objects.equals(lTime3, "") && Objects.equals(payAmount3, "")
                        ||Objects.equals(iValue3, "") && Objects.equals(payAmount3, "")){
                    pieChart3.setVisible(false);
                    alert2.show();
                }else if(Objects.equals(payAmount3, "")){
                    interestRate3 = Double.parseDouble(iField3.getText());
                    loanAmount3 = Double.parseDouble(aField3.getText());
                    loanTerm3=Double.parseDouble(tField3.getText());

                    if(menu3.equals("Every Month")){
                        paymentAmount3=CalculatePaymentAmount2(loanAmount3,loanTerm3,interestRate3);
                    }else {
                        paymentAmount3=Math.round((CalculatePaymentAmount2(loanAmount3,loanTerm3,interestRate3)*12)
                                * 100.0) / 100.0;
                    }
                    alert3.setContentText("Payment = Rs."+paymentAmount3);
                    alert3.show();
                }else if(Objects.equals(lTime3, "")){
                     interestRate3 = Double.parseDouble(iField3.getText());
                     loanAmount3 = Double.parseDouble(aField3.getText());
                     paymentAmount3=Double.parseDouble(payField3.getText());
                     loanTerm3=CalculateLoanTerm(loanAmount3,paymentAmount3,interestRate3);
                    alert3.setContentText("Loan Term ="+loanTerm3+" Year(s)");
                    alert3.show();
                }else if(Objects.equals(lAmount3, "")){
                     interestRate3 = Double.parseDouble(iField3.getText());
                    loanTerm3=Double.parseDouble(tField3.getText());
                    paymentAmount3=Double.parseDouble(payField3.getText());
                    loanAmount3=0;
                    if(menu3.equals("Every Month")){
                        loanAmount3 =CalculateLoanAmount(loanTerm3,paymentAmount3,interestRate3);
                    }else {
                        loanAmount3 =CalculateLoanAmount(loanTerm3,paymentAmount3/12,interestRate3);
                    }
                    alert3.setContentText("Loan Amount = Rs."+loanAmount3);
                    alert3.show();
                }else if(Objects.equals(iValue3, "")){
                    alert3.setContentText("Interest rate cannot be left blank");
                    alert3.show();
                }
                double futureValue=paymentAmount3*12*loanTerm3;
                //adding data to piechart with required calculations.
                ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
                        new PieChart.Data("Loan Amount", loanAmount3),
                        new PieChart.Data("Interest", Math.round((futureValue - loanAmount3) * 100.0) / 100.0)
                );
                pieChart3.setData(list);
                for (final PieChart.Data data : pieChart3.getData()) {
                    data.getNode().addEventHandler(MouseEvent.ANY, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            Tooltip.install(data.getNode(), new Tooltip("Rs." + data.getPieValue()));
                        }
                    });
                }

                resultsViewer3.setText("\n" + loanAmount3 + "\n" + loanTerm3 + " Year(s)\n" + interestRate3 + " %\n"
                        + Math.round(loanAmount3/paymentAmount3) + "\n" + paymentAmount3);
                //viewing the results
            });
            getFileHandling3();
            btReset3.setOnAction(event -> {
                try {
                    aField3.setText("");
                    tField3.setText("");
                    iField3.setText("");
                    payField3.setText("");
                    resultsViewer3.setText("");
                    pieChart3.setData(null);
                }catch (NullPointerException e){
                    System.out.println("Throwing NullPointerException");
                }

            });
            Button btBack = new Button("Go Back");
            btBack.setMinSize(66, 32);
            btBack.setLayoutX(24);
            btBack.setLayoutY(577);
            btBack.setOnAction(e -> {
                fileHandling3();
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
            Navigation.getToolbar(toolBar,"loan", stage);
            //setting up anchorpane and stage to run the calculator
            AnchorPane root4 = new AnchorPane();
            root4.getChildren().addAll(star3,aLabel3,aField3,toolBar,tField3,tLabel3,iLabel3,iField3,payLabel3,payField3
                    ,payBack3,btCalculate3,btReset3,payBackLabel,thisPane ,btBack, pieChart3, results3, resultsViewer3);
            Scene scene4=new Scene(root4,714, 618);
            scene4.getStylesheets().add("layouts.css");
            stage.setTitle("Financial Calculator-Loan");

            stage.setScene(scene4);
            stage.show();

        }
    }
}
