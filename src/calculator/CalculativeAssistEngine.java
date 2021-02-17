package calculator;

import calculator.helper.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CalculativeAssistEngine implements ActionListener, WindowListener {
    private String selectedOperation = " ";
    private double firstNumber = 0;
    private double secondNumber = 0;
    private TextField textField;

    public CalculativeAssistEngine() {

    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    private void calculateResult() {
        switch (selectedOperation) {
            case "+":
                textField.setText(Double.toString(firstNumber + secondNumber));
                secondNumber = 0;
                firstNumber = firstNumber + secondNumber;
                selectedOperation = " ";
                break;
            case "-":
                textField.setText(Double.toString(firstNumber - secondNumber));
                secondNumber = 0;
                firstNumber = firstNumber - secondNumber;
                selectedOperation = " ";
                break;
            case "X":
                textField.setText(Double.toString(firstNumber * secondNumber));
                secondNumber = 0;
                firstNumber = firstNumber * secondNumber;
                selectedOperation = " ";
                break;
            case "/":
                textField.setText(Double.toString(firstNumber / secondNumber));
                secondNumber = 0;
                firstNumber = firstNumber / secondNumber;
                selectedOperation = " ";
                break;
            default: Helper.showMessage(Helper.startErrorMessage); break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            String buttonText = ((JButton) e.getSource()).getText();
            switch (buttonText) {
                //  Addition
                case "+":
                    selectedOperation = "+";
                    textField.setText("0");
                    break;
                //  Subtraction
                case "-":
                    selectedOperation = "-";
                    textField.setText("0");
                    break;
                //  Multiplication
                case "X":
                    selectedOperation = "X";
                    textField.setText("0");
                    break;
                //  Division
                case "/":
                    selectedOperation = "/";
                    textField.setText("0");
                    break;
                //  Equality
                case "=":
                    calculateResult();
                    break;
                //  Clean
                case "C":
                    firstNumber = 0;
                    secondNumber = 0;
                    selectedOperation = " ";
                    textField.setText("0");
                    break;
                //  0 1 2 3 4 5 6 7 8 9
                default:
                    if (selectedOperation == " ") {
                        firstNumber = firstNumber * 10 + Double.parseDouble(buttonText);
                        textField.setText(Double.toString(firstNumber));
                    }
                    else {
                        System.out.println(secondNumber);
                        System.out.println(Double.parseDouble(buttonText));
                        secondNumber = secondNumber * 10 + Double.parseDouble(buttonText);
                        textField.setText(Double.toString(secondNumber));
                    }
                    break;
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
