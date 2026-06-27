package com.calculator.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberFormatterTest {

    @Test
    @DisplayName("prints whole numbers without a decimal part")
    void wholeNumbers() {
        assertEquals("4", NumberFormatter.format(4.0));
        assertEquals("-7", NumberFormatter.format(-7.0));
    }

    @Test
    @DisplayName("keeps fractional values and strips trailing zeros")
    void fractionalNumbers() {
        assertEquals("2.5", NumberFormatter.format(2.5));
        assertEquals("0.1", NumberFormatter.format(0.1));
    }

    @Test
    @DisplayName("renders non-finite values as an error")
    void nonFinite() {
        assertEquals("Error", NumberFormatter.format(Double.NaN));
        assertEquals("Error", NumberFormatter.format(Double.POSITIVE_INFINITY));
    }
}
