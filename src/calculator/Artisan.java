package calculator;

import calculator.helper.Helper;

import javax.swing.*;
import java.awt.*;

public class Artisan {
    //  Window
    private final Window window;
    private final CalculativeAssistEngine calculativeAssistEngine;

    public Artisan(Window window, CalculativeAssistEngine calculativeAssistEngine) {
        this.window = window;
        this.calculativeAssistEngine = calculativeAssistEngine;
        drawInterface();
    }

    private void drawButton(String symbol, int location_x, int location_y) {
        JButton button = new JButton(symbol);
        button.setHorizontalTextPosition(JLabel.CENTER);
        button.setFont(Helper.impactFont);
        button.setSize(60, 60);
        button.setLocation(location_x, location_y);
        button.setVisible(true);
        button.addActionListener(calculativeAssistEngine);
        window.add(button);
    }

    private void drawTextField() {
        TextField textField = new TextField();
        textField.setFont(Helper.arialFont);
        textField.setSize(300, 60);
        textField.setLocation(25, 25);
        textField.setVisible(true);
        calculativeAssistEngine.setTextField(textField);
        window.add(textField);
    }

    private void drawInterface() {
        //  Text field
        drawTextField();
        //  Number buttons
        drawButton("0", 105, 350);
        drawButton("1", 25, 275);
        drawButton("2", 105, 275);
        drawButton("3", 185, 275);
        drawButton("4", 25, 200);
        drawButton("5", 105, 200);
        drawButton("6", 185, 200);
        drawButton("7", 25, 125);
        drawButton("8", 105, 125);
        drawButton("9", 185, 125);
        //  Operation buttons
        drawButton("C", 25, 350);
        drawButton("=", 185, 350);
        drawButton("+", 265, 350);
        drawButton("-", 265, 275);
        drawButton("X", 265, 200);
        drawButton("/", 265, 125);
    }
}
