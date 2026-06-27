package com.calculator.core;

import com.calculator.core.exception.ExpressionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TokenizerTest {

    private final Tokenizer tokenizer = new Tokenizer();

    @Test
    @DisplayName("splits numbers, operators and parentheses")
    void splitsBasicExpression() {
        assertEquals(
                List.of("3", "+", "4", "*", "(", "2", "-", "1", ")"),
                tokenizer.tokenize("3 + 4 * (2 - 1)"));
    }

    @Test
    @DisplayName("keeps multi-digit and decimal numbers intact")
    void keepsMultiDigitAndDecimals() {
        assertEquals(List.of("12", "+", "3.5"), tokenizer.tokenize("12+3.5"));
    }

    @Test
    @DisplayName("recognises a leading minus as unary")
    void leadingMinusIsUnary() {
        assertEquals(List.of(Tokenizer.UNARY_MINUS, "5"), tokenizer.tokenize("-5"));
    }

    @Test
    @DisplayName("recognises a post-operator minus as unary")
    void postOperatorMinusIsUnary() {
        assertEquals(
                List.of("3", "*", Tokenizer.UNARY_MINUS, "2"),
                tokenizer.tokenize("3 * -2"));
    }

    @Test
    @DisplayName("drops a unary plus")
    void unaryPlusIsDropped() {
        assertEquals(List.of("5"), tokenizer.tokenize("+5"));
    }

    @Test
    @DisplayName("rejects an empty expression")
    void rejectsEmpty() {
        assertThrows(ExpressionException.class, () -> tokenizer.tokenize("   "));
    }

    @Test
    @DisplayName("rejects unknown characters")
    void rejectsUnknownCharacter() {
        assertThrows(ExpressionException.class, () -> tokenizer.tokenize("3 % 2"));
    }

    @Test
    @DisplayName("rejects a number with two decimal points")
    void rejectsDoubleDecimal() {
        assertThrows(ExpressionException.class, () -> tokenizer.tokenize("3.1.4"));
    }
}
