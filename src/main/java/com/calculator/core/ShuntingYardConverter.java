package com.calculator.core;

import com.calculator.core.exception.ExpressionException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Converts a list of infix tokens into Reverse Polish Notation (postfix) using
 * Dijkstra's shunting-yard algorithm.
 *
 * <p>Precedence: unary minus &gt; {@code * /} &gt; {@code + -}. Binary operators
 * are left-associative; the unary minus is right-associative.
 */
public final class ShuntingYardConverter {

    private static final int UNARY_PRECEDENCE = 3;

    public List<String> toRpn(List<String> infixTokens) {
        List<String> output = new ArrayList<>();
        Deque<String> operators = new ArrayDeque<>();

        for (String token : infixTokens) {
            if (Operator.isOperator(token)) {
                drainHigherPrecedence(token, operators, output);
                operators.push(token);
            } else if (token.equals(Tokenizer.UNARY_MINUS)) {
                // Right-associative: only pop operators of strictly higher precedence,
                // and nothing outranks the unary minus, so we just push it.
                operators.push(token);
            } else if (token.equals(Tokenizer.LEFT_PAREN)) {
                operators.push(token);
            } else if (token.equals(Tokenizer.RIGHT_PAREN)) {
                drainUntilLeftParen(operators, output);
            } else {
                // Operand (number literal).
                output.add(token);
            }
        }

        while (!operators.isEmpty()) {
            String operator = operators.pop();
            if (operator.equals(Tokenizer.LEFT_PAREN)) {
                throw new ExpressionException("Unbalanced parentheses");
            }
            output.add(operator);
        }

        return output;
    }

    private void drainHigherPrecedence(String operator, Deque<String> operators, List<String> output) {
        int currentPrecedence = precedenceOf(operator);
        while (!operators.isEmpty()) {
            String top = operators.peek();
            if (top.equals(Tokenizer.LEFT_PAREN)) {
                break;
            }
            // Left-associative binary operators also pop equal-precedence operators.
            if (precedenceOf(top) < currentPrecedence) {
                break;
            }
            output.add(operators.pop());
        }
    }

    private void drainUntilLeftParen(Deque<String> operators, List<String> output) {
        boolean matched = false;
        while (!operators.isEmpty()) {
            String top = operators.pop();
            if (top.equals(Tokenizer.LEFT_PAREN)) {
                matched = true;
                break;
            }
            output.add(top);
        }
        if (!matched) {
            throw new ExpressionException("Unbalanced parentheses");
        }
    }

    private int precedenceOf(String token) {
        if (token.equals(Tokenizer.UNARY_MINUS)) {
            return UNARY_PRECEDENCE;
        }
        return Operator.fromSymbol(token).precedence();
    }
}
