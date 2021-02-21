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

    public CalculativeAssistEngine() {}

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    private void resetCalculator() {
        firstNumber = 0;
        secondNumber = 0;
        selectedOperation = " ";
        textField.setText("0");
    }

    private void setOperation(String operation) {
        selectedOperation = operation;
        secondNumber = 0;
        textField.setText("0");
    }

    private void calculateResult() {
        switch (selectedOperation) {
            case "+" -> {
                final String result = (firstNumber + secondNumber) % 1 == 0
                        ? Integer.toString((int) (firstNumber + secondNumber))
                        : Double.toString(firstNumber + secondNumber);
                textField.setText(result);
                firstNumber = firstNumber + secondNumber;
            }
            case "-" -> {
                final String result = (firstNumber - secondNumber) % 1 == 0
                        ? Integer.toString((int) (firstNumber - secondNumber))
                        : Double.toString(firstNumber - secondNumber);
                textField.setText(result);
                firstNumber = firstNumber - secondNumber;
            }
            case "X" -> {
                final String result = (firstNumber * secondNumber) % 1 == 0
                        ? Integer.toString((int) (firstNumber * secondNumber))
                        : Double.toString(firstNumber * secondNumber);
                textField.setText(result);
                firstNumber = firstNumber * secondNumber;
            }
            case "/" -> {
                final String result = (firstNumber / secondNumber) % 1 == 0
                        ? Integer.toString((int) (firstNumber / secondNumber))
                        : Double.toString(firstNumber / secondNumber);
                textField.setText(result);
                firstNumber = firstNumber / secondNumber;
            }
            default -> {
                Helper.showMessage(Helper.startErrorMessage);
                resetCalculator();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            String buttonText = ((JButton) e.getSource()).getText();
            switch (buttonText) {
                //  Addition
                case "+":
                    setOperation("+");
                    break;
                //  Subtraction
                case "-":
                    setOperation("-");
                    break;
                //  Multiplication
                case "X":
                    setOperation("X");
                    break;
                //  Division
                case "/":
                    setOperation("/");
                    break;
                //  Equality
                case "=":
                    calculateResult();
                    break;
                //  Clean
                case "C":
                    resetCalculator();
                    break;
                //  0 1 2 3 4 5 6 7 8 9
                default:
                    if (selectedOperation.equals(" ")) {
                        /*
                         * try catch   isNumber
                         */
                        firstNumber = firstNumber * 10 + Double.parseDouble(buttonText);
                        textField.setText(Double.toString(firstNumber));
                    }
                    else {
                        /*
                         * try catch   isNumber
                         */
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
