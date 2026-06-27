package com.calculator.core;

import com.calculator.core.exception.ExpressionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShuntingYardConverterTest {

    private final Tokenizer tokenizer = new Tokenizer();
    private final ShuntingYardConverter converter = new ShuntingYardConverter();

    private List<String> rpn(String infix) {
        return converter.toRpn(tokenizer.tokenize(infix));
    }

    @Test
    @DisplayName("honours multiplication-over-addition precedence")
    void precedence() {
        assertEquals(List.of("3", "4", "2", "*", "+"), rpn("3 + 4 * 2"));
    }

    @Test
    @DisplayName("honours parentheses over precedence")
    void parentheses() {
        assertEquals(List.of("3", "4", "+", "2", "*"), rpn("(3 + 4) * 2"));
    }

    @Test
    @DisplayName("treats binary operators as left-associative")
    void leftAssociative() {
        assertEquals(List.of("8", "4", "-", "2", "-"), rpn("8 - 4 - 2"));
    }

    @Test
    @DisplayName("places the unary minus after its operand")
    void unaryMinus() {
        assertEquals(List.of("5", Tokenizer.UNARY_MINUS), rpn("-5"));
        assertEquals(List.of("3", "2", Tokenizer.UNARY_MINUS, "*"), rpn("3 * -2"));
    }

    @Test
    @DisplayName("rejects unbalanced parentheses")
    void unbalancedParentheses() {
        assertThrows(ExpressionException.class, () -> rpn("(3 + 4"));
        assertThrows(ExpressionException.class, () -> rpn("3 + 4)"));
    }
}
