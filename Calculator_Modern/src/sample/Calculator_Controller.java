package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Calculator_Controller implements Initializable {

    private double number1;
    private String operator = "";
    private boolean start = true;

    @FXML
    private Label output;





    @FXML
    private void processNumPad (ActionEvent event){
        if (start){
            output.setText("");
            start = false;
        }

        String value = ((Button)event.getSource()).getText();
        output.setText(output.getText() + value);
    }

    @FXML
    private void processOperator(ActionEvent event){
        if(output.getText().equals("ERROR")){
            return;
        }
        String value = ((Button)event.getSource()).getText();
        if (!value.equals("=")){
            if(!operator.isEmpty()){
                return;
            }
            operator = value;
            number1 = Double.parseDouble(output.getText());
            output.setText("");
        } else {
            if(operator.isEmpty()){
                return;
            }
            if(output.getText().isEmpty()){
                output.setText("ERROR!");
                operator = "";
                start = true;
                return;
            }
            output.setText(calculate(number1, Double.parseDouble(output.getText()), operator));
            operator = "";
            start = true;
        }
    }

    @FXML
    private void clearOutput(ActionEvent event){
        output.setText("0");
        start = true;
        operator = "";
    }

    private String calculate(double number1, double number2, String op){
        switch(op){
            case "+":
                return String.valueOf(number1 + number2);
            case "-":
                return String.valueOf(number1 - number2);
            case "*":
                return String.valueOf(number1 * number2);
            case "/":
                if(number2 == 0.0){
                    return "ERROR";
                } else {
                return String.valueOf(number1 / number2);
                }
            case "%":
                return String.valueOf((number2 == 0.0)? "Error" : number1/100 * number2);
        }
        return "ERROR !";

}

    @FXML
    private void btnPoint() {
        if (output.getText().length() < 9) {
            String lastTxt = "";
            for(int i = output.getText().length() - 1; i >= 0; i--) {
                if("+-*/".contains(output.getText().substring(i, i + 1))) {
                    break;
                } else {
                    lastTxt = output.getText().substring(i, i + 1) + lastTxt;
                }
            }

            if(lastTxt.length() == 0)
                output.setText(output.getText() + "0.");
            else if(!lastTxt.contains("."))
                output.setText(output.getText() + ".");

        }
    }

    @FXML
    private void btnRadical(ActionEvent event){
        double ops = Double.parseDouble(String.valueOf(output.getText()));
        ops = Math.sqrt(ops);
        output.setText(String.valueOf(ops));
    }

    @FXML
    private void btnXlaX (ActionEvent event){
        double ops = Double.parseDouble(String.valueOf(output.getText()));
        ops = Math.pow(ops, ops);
        output.setText(String.valueOf(ops));
    }
    @FXML
    private void btnXla2 (ActionEvent event){
        double ops = Double.parseDouble(String.valueOf(output.getText()));
        ops = (ops *ops);
        output.setText(String.valueOf(ops));
    }
    @FXML
    private void btnXla3 (ActionEvent event){
        double ops = Double.parseDouble(String.valueOf(output.getText()));
        ops = (ops *ops * ops);
        output.setText(String.valueOf(ops));
    }

    @FXML
    private void btnPlusMinus (ActionEvent event){
        double ops = Double.parseDouble(String.valueOf(output.getText()));
        ops = ops * (-1);
        output.setText(String.valueOf(ops));
    }


    @FXML
    private void backspace(ActionEvent event){
        if (output.getText().length()>0){
            StringBuffer sb = new StringBuffer(output.getText());
            sb = sb.deleteCharAt(output.getText().length()-1);
            output.setText(sb.toString());
        }
    }







    @Override
    public void initialize(URL location, ResourceBundle resources) { }
}
