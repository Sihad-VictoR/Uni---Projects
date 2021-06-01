import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

public class Navigation {
    //In this class ,we are having toolbar with buttons to navigate inside calculators
    static void getToolbar(ToolBar bar, String text, Stage primaryStage){
        Calculator stage=new Calculator();
        bar.setMinSize(714,60);
        Button button1 = new Button("Compound Interest Savings");
        button1.setMinSize(163,43);
        button1.setOnAction(e -> {
            SavingsCalculator.fileHandling2();
            LoanCalculator.fileHandling3();
            MortgageCalculator.fileHandling4();
            CISavingsCalculator scene2=new CISavingsCalculator();
            try {
                scene2.start(primaryStage);
            } catch (Exception exception) {
                System.out.println("Sorry Class Not Found");
            }
        });
        Button button2 = new Button("Savings");
        button2.setMinSize(163,43);
        button2.setOnAction(e -> {
            CISavingsCalculator.fileHandling();
            LoanCalculator.fileHandling3();
            MortgageCalculator.fileHandling4();
            SavingsCalculator scene3=new SavingsCalculator();
            try {
                scene3.start(primaryStage);
            } catch (Exception exception) {
                System.out.println("Sorry Class Not Found");
            }
        });
        Button button3 = new Button("Loan");
        button3.setOnAction(e -> {
            LoanCalculator scene1=new LoanCalculator();
            CISavingsCalculator.fileHandling();
            SavingsCalculator.fileHandling2();
            MortgageCalculator.fileHandling4();
            try {
                scene1.start(primaryStage);
            } catch (Exception exception) {
                System.out.println("Sorry Class Not Found");
            }
        });

        button3.setMinSize(163,43);
        Button button4 = new Button("Mortgage");
        button4.setMinSize(163,43);
        button4.setOnAction(e -> {
            CISavingsCalculator.fileHandling();
            SavingsCalculator.fileHandling2();
            LoanCalculator.fileHandling3();
            MortgageCalculator scene4=new MortgageCalculator();
            try {
                scene4.start(primaryStage);
            } catch (Exception exception) {
                System.out.println("Sorry Class Not Found");
            }
        });

        switch (text) {
            case "compound":
                button1.setDisable(true);
                break;
            case "savings":
                button2.setDisable(true);
                break;
            case "loan":
                button3.setDisable(true);
                break;
            case "mortgage":
                button4.setDisable(true);
                break;
        }
        bar.getItems().addAll(button1,button2,button3,button4);
    }
}
