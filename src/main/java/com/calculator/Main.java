package com.calculator;

import com.calculator.app.Calculator;
import com.calculator.core.ExpressionEvaluator;
import com.calculator.util.NumberFormatter;

/**
 * Program entry point.
 *
 * <ul>
 *   <li>No arguments: launches the Swing calculator UI.</li>
 *   <li>One or more arguments: joins them into an infix expression, evaluates it
 *       through the Reverse Polish Notation engine and prints the result
 *       (handy for scripting, e.g. {@code java -jar java-calculator.jar "3 + 4 * 2"}).</li>
 * </ul>
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            evaluateFromCommandLine(String.join(" ", args));
            return;
        }
        Calculator.getInstance().run();
    }

    private static void evaluateFromCommandLine(String expression) {
        try {
            double result = new ExpressionEvaluator().evaluate(expression);
            System.out.println(NumberFormatter.format(result));
        } catch (RuntimeException ex) {
            System.err.println("Error: " + ex.getMessage());
            System.exit(1);
        }
    }
}
