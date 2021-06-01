import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class VirtualKeyboard {
    //in this class we have the onscreen keyboard.
    private static Button point,zero,one,two,three,four,five,six,seven,eight,nine,clearAll,clear,minus;
    //setting functions to each button in the keyboard
    static void changeField(TextField fields){
        fields.setEditable(false);
        minus.setDisable(true);
        minus.setDisable(fields != SavingsCalculator.payField2);
        point.setDisable(fields.getText().contains("."));
        point.setOnAction(e->{
            String oldValue =fields.getText();
            String set=".";
            fields.setText(oldValue+set);
            if(fields.getText().contains(".")){
                point.setDisable(true);
            }
        });
        zero.setOnAction(e->{
            String oldValue =fields.getText();
            String set="0";
            fields.setText(oldValue+set);
        });
        one.setOnAction(e->{
            String oldValue =fields.getText();
            String set="1";
            fields.setText(oldValue+set);
        });
        two.setOnAction(e->{
            String oldValue =fields.getText();
            String set="2";
            fields.setText(oldValue+set);
        });
        three.setOnAction(e->{
            String oldValue =fields.getText();
            String set="3";
            fields.setText(oldValue+set);
        });
        four.setOnAction(e->{
            String oldValue =fields.getText();
            String set="4";
            fields.setText(oldValue+set);
        });
        five.setOnAction(e->{
            String oldValue =fields.getText();
            String set="5";
            fields.setText(oldValue+set);
        });
        six.setOnAction(e->{
            String oldValue =fields.getText();
            String set="6";
            fields.setText(oldValue+set);
        });
        seven.setOnAction(e->{
            String oldValue =fields.getText();
            String set="7";
            fields.setText(oldValue+set);
        });
        eight.setOnAction(e->{
            String oldValue =fields.getText();
            String set="8";
            fields.setText(oldValue+set);
        });
        nine.setOnAction(e->{
            String oldValue =fields.getText();
            String set="9";
            fields.setText(oldValue+set);
        });
        minus.setOnAction(event -> {
            String oldValue =fields.getText();
            if(Objects.equals(oldValue, "")){
                fields.setText("-");
            }
        });
        clearAll.setOnAction(e->{
            String set="";
            fields.setText(set);
            if(!fields.getText().contains(".")){
                point.setDisable(false);
            }
        });
        clear.setOnAction(e->{
            String oldValue =fields.getText();
            String set="";
            if(oldValue==null||oldValue.length()==0){
                set="";
            }else{
                set=(oldValue.substring(0, oldValue.length() - 1));
            }
            fields.setText(set);
            if(!fields.getText().contains(".")){
                point.setDisable(false);
            }
        });
    }
    //designing the keyboard inside a layout(pane)
    static Pane myPane(Pane keyboard){
        point= new Button(".");
        point.setLayoutX(91);
        point.setLayoutY(284);
        point.setMinSize(55,59);

        zero=new Button("0");
        zero.setLayoutX(19);
        zero.setLayoutY(284);
        zero.setMinSize(55,59);

        one=new Button("1");
        one.setLayoutX(20);
        one.setLayoutY(64);
        one.setMinSize(55,59);

        two=new Button("2");
        two.setLayoutX(90);
        two.setLayoutY(64);
        two.setMinSize(55,59);

        three=new Button("3");
        three.setLayoutX(161);
        three.setLayoutY(64);
        three.setMinSize(55,59);

        four=new Button("4");
        four.setLayoutX(20);
        four.setLayoutY(138);
        four.setMinSize(55,59);

        five=new Button("5");
        five.setLayoutX(91);
        five.setLayoutY(138);
        five.setMinSize(55,59);

        six=new Button("6");
        six.setLayoutX(162);
        six.setLayoutY(138);
        six.setMinSize(55,59);

        seven=new Button("7");
        seven.setLayoutX(20);
        seven.setLayoutY(215);
        seven.setMinSize(55,59);

        eight=new Button("8");
        eight.setLayoutX(91);
        eight.setLayoutY(215);
        eight.setMinSize(55,59);

        nine=new Button("9");
        nine.setLayoutX(162);
        nine.setLayoutY(215);
        nine.setMinSize(55,59);

        clear=new Button("<-");
        clear.setLayoutX(222);
        clear.setLayoutY(64);
        clear.setMinSize(54,124);

        clearAll=new Button("C");
        clearAll.setLayoutX(222);
        clearAll.setLayoutY(194);
        clearAll.setMinSize(54,150);

        minus=new Button("-");
        minus.setLayoutX(162);
        minus.setLayoutY(284);
        minus.setMinSize(52,59);
        //adding the buttons to pane so that it can be called in required places.
        keyboard=new Pane();
        keyboard.setId("keyboardPane");
        keyboard.setLayoutX(370);
        keyboard.setLayoutY(36);
        keyboard.setMinSize(298,310);
        keyboard.getChildren().addAll(clear,point,one,two,three,four,five,six,seven,eight,nine,zero,clearAll,minus);
        keyboard.getStylesheets().add("keyboard.css");
        return keyboard;
    }
}