package com.calculator.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Formats {@code double} results for display: whole numbers are shown without a
 * decimal part, fractional numbers are rounded to a sane number of digits with
 * trailing zeros stripped.
 */
public final class NumberFormatter {

    private static final int MAX_FRACTION_DIGITS = 10;

    private NumberFormatter() {
    }

    public static String format(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            return "Error";
        }
        if (value == Math.rint(value) && !Double.isInfinite(value)) {
            return Long.toString((long) value);
        }
        BigDecimal rounded = BigDecimal.valueOf(value)
                .setScale(MAX_FRACTION_DIGITS, RoundingMode.HALF_UP)
                .stripTrailingZeros();
        return rounded.toPlainString();
    }
}
