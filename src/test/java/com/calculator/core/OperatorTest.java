package com.calculator.core;

import com.calculator.core.exception.ExpressionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperatorTest {

    @Test
    @DisplayName("resolves operators by symbol")
    void resolvesBySymbol() {
        assertEquals(Operator.ADD, Operator.fromSymbol("+"));
        assertEquals(Operator.DIVIDE, Operator.fromSymbol("/"));
    }

    @Test
    @DisplayName("recognises known and unknown symbols")
    void recognisesSymbols() {
        assertTrue(Operator.isOperator("*"));
        assertFalse(Operator.isOperator("9"));
    }

    @Test
    @DisplayName("multiplication outranks addition")
    void precedence() {
        assertTrue(Operator.MULTIPLY.precedence() > Operator.ADD.precedence());
    }

    @Test
    @DisplayName("guards against division by zero")
    void divisionByZero() {
        assertThrows(ExpressionException.class, () -> Operator.DIVIDE.apply(1, 0));
    }
}
