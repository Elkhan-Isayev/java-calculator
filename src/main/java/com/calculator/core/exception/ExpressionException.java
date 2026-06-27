package com.calculator.core.exception;

/**
 * Raised when an expression cannot be tokenized, converted or evaluated,
 * for example because of unbalanced parentheses or a malformed number.
 */
public class ExpressionException extends RuntimeException {

    public ExpressionException(String message) {
        super(message);
    }

    public ExpressionException(String message, Throwable cause) {
        super(message, cause);
    }
}
