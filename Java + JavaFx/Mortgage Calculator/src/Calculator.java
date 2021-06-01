import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Calculator extends Application {
    public  static Stage window;
    Scene scene1;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //setting up primary stage
        window=primaryStage;
        //inserting buttons in the main view to select calculator
        Button bt1=new Button();
        bt1.setOnAction(e -> {
            LoanCalculator scene1=new LoanCalculator();
            try {
                scene1.start(primaryStage);
            } catch (Exception exception) {
                System.out.println("Sorry Class Not Found");
            }
        });
        bt1.setId("bt1");
        bt1.setLayoutX(94);
        bt1.setLayoutY(139);
        bt1.setMinSize(220,54);

        Button bt2=new Button();
        bt2.setOnAction(e -> {
            CISavingsCalculator scene2=new CISavingsCalculator();
            try {
                scene2.start(primaryStage);
            } catch (Exception exception) {
                System.out.println("Sorry Class Not Found");
            }
        });
        bt2.setId("bt2");
        bt2.setLayoutX(94);
        bt2.setLayoutY(206);
        bt2.setMinSize(220,54);

        Button bt3=new Button();
        bt3.setOnAction(e -> {
            SavingsCalculator scene3=new SavingsCalculator();
            try {
                scene3.start(primaryStage);
            } catch (Exception exception) {
                System.out.println("Sorry Class Not Found");
            }
        });
        bt3.setLayoutX(94);
        bt3.setLayoutY(275);
        bt3.setMinSize(220,54);
        bt3.setId("bt3");
        Button bt4=new Button();

        bt4.setOnAction(e -> {
            MortgageCalculator scene4=new MortgageCalculator();
            try {
                scene4.start(primaryStage);
            } catch (Exception exception) {
                System.out.println("Sorry Class Not Found");
            }
        });
        bt4.setId("bt4");
        bt4.setLayoutY(348);
        bt4.setLayoutX(94);
        bt4.setLayoutY(348);
        bt4.setMinSize(220,54);
        //setting button focus off
        bt1.setFocusTraversable(false);
        bt2.setFocusTraversable(false);
        bt3.setFocusTraversable(false);
        bt4.setFocusTraversable(false);
        //introducing help button and creating anew window to that.
        Button help=new Button("?");
        help.setLayoutY(565);
        help.setLayoutX(300);
        help.setMinSize(52,35);
        help.setId("help");
        help.setOnAction(event -> {
            Image image2 = new Image("help.png");
            ImageView help2=new ImageView(image2);

            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().add(help2);

            Scene secondScene = new Scene(secondaryLayout, 700, 500);

            // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Help");
            newWindow.setScene(secondScene);
            newWindow.getIcons().add(new Image(Calculator.class.getResourceAsStream("helplogo.png")) {});

            // Set position of second window, related to primary window.
            newWindow.setX(primaryStage.getX() + 200);
            newWindow.setY(primaryStage.getY() + 100);

            newWindow.show();
        });

        //adding logo to the file
        Image image = new Image("Logo.png");
        ImageView logo=new ImageView(image);
        logo.setLayoutX(85);
        logo.setLayoutY(220);
        logo.setFitHeight(150);
        logo.setFitWidth(200);
        Label name=new Label("Financial Calculator");
        name.setFont(Font.font("Comic Sans MS",25));
        name.setLayoutX(70);
        name.setLayoutY(370);
        name.setMinSize(214,36);
        Pane left=new Pane();
        left.setMinSize(331,620);
        left.setId("leftPane");
        left.getChildren().addAll(name,logo);
        Pane right=new Pane();
        right.setId("rightPane");
        right.setMinSize(390,620);
        right.setLayoutX(324);
        right.getChildren().addAll(bt1,bt3,bt4,bt2,help);
        //inserting an anchor pane and adding all elements required in the main page to it and adding css to it.
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(left,right);
        scene1=new Scene(root,700, 618);
        scene1.getStylesheets().add("main.css");
        window.getIcons().add(new Image(Calculator.class.getResourceAsStream("Logo.png")) {}); //Place on the Icon
        window.setResizable(false);
        window.setOnCloseRequest(event -> {
            CISavingsCalculator.fileHandling();
            SavingsCalculator.fileHandling2();
            LoanCalculator.fileHandling3();
            MortgageCalculator.fileHandling4();
        });

        window.setScene(scene1);

        window.setTitle("Financial Calculator");
        window.show();
    }


}
