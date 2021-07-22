package calculator;

import calculator.helper.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculativeAssistEngine implements ActionListener {
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

    private void setTextFieldNumber(double number) {
        final String result = (number) % 1 == 0
                ? Integer.toString((int) (number))
                : Double.toString(number);
        textField.setText(result);
    }

    private void calculateResult() {
        switch (selectedOperation) {
            case "+": {
                setTextFieldNumber(firstNumber + secondNumber);
                firstNumber = firstNumber + secondNumber;
                break;
            }
            case "-": {
                setTextFieldNumber(firstNumber - secondNumber);
                firstNumber = firstNumber - secondNumber;
                break;
            }
            case "X": {
                setTextFieldNumber(firstNumber * secondNumber);
                firstNumber = firstNumber * secondNumber;
                break;
            }
            case "/": {
                setTextFieldNumber(firstNumber / secondNumber);
                firstNumber = firstNumber / secondNumber;
                break;
            }
            default: {
                Helper.showMessage(Helper.startErrorMessage);
                resetCalculator();
                break;
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
                        firstNumber = firstNumber * 10 + Double.parseDouble(buttonText);
                        setTextFieldNumber(firstNumber);
                    }
                    else {
                        secondNumber = secondNumber * 10 + Double.parseDouble(buttonText);
                        setTextFieldNumber(secondNumber);
                    }
                    break;
            }
        }
    }
}
