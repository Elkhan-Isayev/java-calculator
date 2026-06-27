package com.calculator.core;

import com.calculator.core.exception.ExpressionException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Evaluates an expression already in Reverse Polish Notation (postfix).
 *
 * <p>The algorithm is a single left-to-right pass over a value stack: operands
 * are pushed, and each operator pops its operands and pushes the result. A
 * well-formed expression leaves exactly one value on the stack.
 */
public final class RpnEvaluator {

    public double evaluate(List<String> rpnTokens) {
        Deque<Double> stack = new ArrayDeque<>();

        for (String token : rpnTokens) {
            if (token.equals(Tokenizer.UNARY_MINUS)) {
                stack.push(-popOperand(stack, token));
            } else if (Operator.isOperator(token)) {
                double right = popOperand(stack, token);
                double left = popOperand(stack, token);
                stack.push(Operator.fromSymbol(token).apply(left, right));
            } else {
                stack.push(parseNumber(token));
            }
        }

        if (stack.size() != 1) {
            throw new ExpressionException("Malformed expression");
        }
        return stack.pop();
    }

    private double popOperand(Deque<Double> stack, String operator) {
        if (stack.isEmpty()) {
            throw new ExpressionException("Missing operand for operator '" + operator + "'");
        }
        return stack.pop();
    }

    private double parseNumber(String token) {
        try {
            return Double.parseDouble(token);
        } catch (NumberFormatException e) {
            throw new ExpressionException("Invalid number: '" + token + "'", e);
        }
    }
}
