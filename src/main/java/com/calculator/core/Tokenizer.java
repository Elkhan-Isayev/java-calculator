package com.calculator.core;

import com.calculator.core.exception.ExpressionException;

import java.util.ArrayList;
import java.util.List;

/**
 * Splits a raw infix expression such as {@code "3 + 4 * (2 - 1)"} into a flat
 * list of tokens: numbers, operator symbols and parentheses.
 *
 * <p>A leading or "post-operator" minus is recognised as a unary negation and
 * emitted as the internal {@link #UNARY_MINUS} marker so the converter can give
 * it the correct (higher, right-associative) precedence. A unary plus is simply
 * dropped because it has no effect.
 */
public final class Tokenizer {

    /** Internal marker for unary negation; never produced by user input directly. */
    public static final String UNARY_MINUS = "~";

    public static final String LEFT_PAREN = "(";
    public static final String RIGHT_PAREN = ")";

    public List<String> tokenize(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new ExpressionException("Expression is empty");
        }

        List<String> tokens = new ArrayList<>();
        int index = 0;
        int length = expression.length();

        while (index < length) {
            char current = expression.charAt(index);

            if (Character.isWhitespace(current)) {
                index++;
            } else if (isNumberStart(current)) {
                index = readNumber(expression, index, tokens);
            } else if (current == '(' || current == ')') {
                tokens.add(String.valueOf(current));
                index++;
            } else if (isOperatorChar(current)) {
                appendOperator(current, tokens);
                index++;
            } else {
                throw new ExpressionException("Unexpected character: '" + current + "'");
            }
        }

        return tokens;
    }

    private void appendOperator(char operator, List<String> tokens) {
        if (operator == '-' && isUnaryContext(tokens)) {
            tokens.add(UNARY_MINUS);
        } else if (operator == '+' && isUnaryContext(tokens)) {
            // Unary plus is a no-op; drop it.
            return;
        } else {
            tokens.add(String.valueOf(operator));
        }
    }

    /**
     * Reads a (multi-digit, optionally decimal) number starting at {@code start}
     * and returns the index of the first character after it.
     */
    private int readNumber(String expression, int start, List<String> tokens) {
        int index = start;
        int length = expression.length();
        boolean dotSeen = false;

        while (index < length) {
            char current = expression.charAt(index);
            if (current == '.') {
                if (dotSeen) {
                    throw new ExpressionException("Number has more than one decimal point");
                }
                dotSeen = true;
            } else if (!Character.isDigit(current)) {
                break;
            }
            index++;
        }

        tokens.add(expression.substring(start, index));
        return index;
    }

    /** A minus/plus is unary when it opens the expression, a group, or follows another operator. */
    private boolean isUnaryContext(List<String> tokens) {
        if (tokens.isEmpty()) {
            return true;
        }
        String previous = tokens.get(tokens.size() - 1);
        return previous.equals(LEFT_PAREN)
                || previous.equals(UNARY_MINUS)
                || Operator.isOperator(previous);
    }

    private boolean isNumberStart(char c) {
        return Character.isDigit(c) || c == '.';
    }

    private boolean isOperatorChar(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
