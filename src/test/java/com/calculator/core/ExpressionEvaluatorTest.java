package com.calculator.core;

import com.calculator.core.exception.ExpressionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpressionEvaluatorTest {

    private final ExpressionEvaluator evaluator = new ExpressionEvaluator();

    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource({
            "'1 + 1', 2",
            "'3 + 4 * 2', 11",
            "'(3 + 4) * 2', 14",
            "'10 / 4', 2.5",
            "'8 - 4 - 2', 2",
            "'2 * 3 + 4 * 5', 26",
            "'-5 + 3', -2",
            "'3 * -2', -6",
            "'-(2 + 3)', -5",
            "'2 * (3 + (4 - 1))', 12",
            "'3.5 + 1.5', 5",
            "'100', 100"
    })
    @DisplayName("evaluates full infix expressions end to end")
    void evaluatesExpressions(String expression, double expected) {
        assertEquals(expected, evaluator.evaluate(expression), 1e-9);
    }

    @Test
    @DisplayName("reports division by zero")
    void divisionByZero() {
        assertThrows(ExpressionException.class, () -> evaluator.evaluate("5 / 0"));
    }

    @Test
    @DisplayName("reports malformed input")
    void malformedInput() {
        assertThrows(ExpressionException.class, () -> evaluator.evaluate("3 +"));
        assertThrows(ExpressionException.class, () -> evaluator.evaluate("(1 + 2"));
    }
}
