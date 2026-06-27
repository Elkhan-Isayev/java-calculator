package com.calculator.app;

import com.calculator.ui.CalculatorWindow;

import javax.swing.SwingUtilities;

/**
 * Application entry object. Kept as a singleton (only one calculator instance
 * makes sense per process) and responsible for launching the Swing UI on the
 * Event Dispatch Thread.
 */
public final class Calculator {

    private static Calculator instance;

    private Calculator() {
    }

    public static synchronized Calculator getInstance() {
        if (instance == null) {
            instance = new Calculator();
        }
        return instance;
    }

    public void run() {
        SwingUtilities.invokeLater(() -> new CalculatorWindow().setVisible(true));
    }
}
