package com.calculator.ui;

import com.calculator.core.ExpressionEvaluator;
import com.calculator.core.exception.ExpressionException;
import com.calculator.util.NumberFormatter;

import javax.swing.JTextField;

/**
 * Owns the calculator's input state and bridges the Swing keypad to the
 * {@link ExpressionEvaluator}. The view (buttons) only forwards commands here;
 * all behaviour lives in this controller, which keeps the UI classes dumb.
 */
public class CalculatorController {

    public static final String CLEAR = "C";
    public static final String BACKSPACE = "⌫"; // ⌫
    public static final String EQUALS = "=";

    private static final String DISPLAY_ON_ERROR = "Error";

    private final JTextField display;
    private final ExpressionEvaluator evaluator;
    private final StringBuilder expression = new StringBuilder();

    /** True right after "=", so the next digit starts a fresh expression. */
    private boolean resultShown = false;

    public CalculatorController(JTextField display) {
        this(display, new ExpressionEvaluator());
    }

    public CalculatorController(JTextField display, ExpressionEvaluator evaluator) {
        this.display = display;
        this.evaluator = evaluator;
        render();
    }

    /** Entry point invoked by every keypad button. */
    public void handle(String command) {
        switch (command) {
            case CLEAR:
                clear();
                break;
            case BACKSPACE:
                backspace();
                break;
            case EQUALS:
                evaluate();
                break;
            default:
                input(command);
                break;
        }
    }

    private void input(String token) {
        if (resultShown) {
            // After a result: a digit/group starts over, an operator continues from it.
            if (isOperatorOrClosing(token)) {
                resultShown = false;
            } else {
                expression.setLength(0);
                resultShown = false;
            }
        }
        expression.append(token);
        render();
    }

    private void clear() {
        expression.setLength(0);
        resultShown = false;
        render();
    }

    private void backspace() {
        if (resultShown) {
            clear();
            return;
        }
        if (expression.length() > 0) {
            expression.setLength(expression.length() - 1);
        }
        render();
    }

    private void evaluate() {
        if (expression.length() == 0) {
            return;
        }
        try {
            double result = evaluator.evaluate(expression.toString());
            String formatted = NumberFormatter.format(result);
            expression.setLength(0);
            expression.append(formatted);
            resultShown = true;
            display.setText(formatted);
        } catch (ExpressionException | ArithmeticException ex) {
            expression.setLength(0);
            resultShown = true;
            display.setText(DISPLAY_ON_ERROR);
        }
    }

    private void render() {
        display.setText(expression.length() == 0 ? "0" : expression.toString());
    }

    private boolean isOperatorOrClosing(String token) {
        return token.equals("+") || token.equals("-")
                || token.equals("*") || token.equals("/")
                || token.equals(")");
    }
}
