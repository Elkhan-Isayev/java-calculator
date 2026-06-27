package com.calculator.core;

import com.calculator.core.exception.ExpressionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RpnEvaluatorTest {

    private final RpnEvaluator evaluator = new RpnEvaluator();

    @Test
    @DisplayName("evaluates a postfix expression")
    void evaluatesPostfix() {
        // 3 4 2 * +  ==  3 + 4 * 2  ==  11
        assertEquals(11.0, evaluator.evaluate(List.of("3", "4", "2", "*", "+")));
    }

    @Test
    @DisplayName("applies the unary minus token")
    void appliesUnaryMinus() {
        assertEquals(-5.0, evaluator.evaluate(List.of("5", Tokenizer.UNARY_MINUS)));
    }

    @Test
    @DisplayName("rejects a missing operand")
    void rejectsMissingOperand() {
        assertThrows(ExpressionException.class, () -> evaluator.evaluate(List.of("3", "+")));
    }

    @Test
    @DisplayName("rejects leftover operands")
    void rejectsLeftoverOperands() {
        assertThrows(ExpressionException.class, () -> evaluator.evaluate(List.of("3", "4")));
    }
}
