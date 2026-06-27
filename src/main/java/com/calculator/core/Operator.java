package com.calculator.core;

import com.calculator.core.exception.ExpressionException;

import java.util.function.DoubleBinaryOperator;

/**
 * The set of supported binary arithmetic operators together with the metadata
 * the {@link ShuntingYardConverter} needs: a textual symbol, a precedence and
 * an associativity. All binary operators in this calculator are left-associative.
 */
public enum Operator {

    ADD("+", 1, Double::sum),
    SUBTRACT("-", 1, (left, right) -> left - right),
    MULTIPLY("*", 2, (left, right) -> left * right),
    DIVIDE("/", 2, (left, right) -> {
        if (right == 0) {
            throw new ExpressionException("Division by zero");
        }
        return left / right;
    });

    private final String symbol;
    private final int precedence;
    private final DoubleBinaryOperator operation;

    Operator(String symbol, int precedence, DoubleBinaryOperator operation) {
        this.symbol = symbol;
        this.precedence = precedence;
        this.operation = operation;
    }

    public String symbol() {
        return symbol;
    }

    public int precedence() {
        return precedence;
    }

    /** Applies the operator to its two operands. */
    public double apply(double left, double right) {
        return operation.applyAsDouble(left, right);
    }

    /** Returns {@code true} if the token matches one of the known operator symbols. */
    public static boolean isOperator(String token) {
        for (Operator operator : values()) {
            if (operator.symbol.equals(token)) {
                return true;
            }
        }
        return false;
    }

    /** Resolves an operator by its symbol, or throws if it is unknown. */
    public static Operator fromSymbol(String symbol) {
        for (Operator operator : values()) {
            if (operator.symbol.equals(symbol)) {
                return operator;
            }
        }
        throw new ExpressionException("Unknown operator: " + symbol);
    }
}
