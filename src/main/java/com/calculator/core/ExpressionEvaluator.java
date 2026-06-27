package com.calculator.core;

import java.util.List;

/**
 * High-level facade over the calculation pipeline. It wires the three
 * single-responsibility engines together so callers only deal with a plain
 * infix string:
 *
 * <pre>
 *   infix String
 *      -> {@link Tokenizer}            (text       -> tokens)
 *      -> {@link ShuntingYardConverter} (infix      -> RPN)
 *      -> {@link RpnEvaluator}          (RPN        -> result)
 * </pre>
 *
 * Each stage is independently testable and replaceable; this class owns only the
 * orchestration.
 */
public final class ExpressionEvaluator {

    private final Tokenizer tokenizer;
    private final ShuntingYardConverter converter;
    private final RpnEvaluator rpnEvaluator;

    public ExpressionEvaluator() {
        this(new Tokenizer(), new ShuntingYardConverter(), new RpnEvaluator());
    }

    /** Constructor for dependency injection, mainly useful in tests. */
    public ExpressionEvaluator(Tokenizer tokenizer,
                               ShuntingYardConverter converter,
                               RpnEvaluator rpnEvaluator) {
        this.tokenizer = tokenizer;
        this.converter = converter;
        this.rpnEvaluator = rpnEvaluator;
    }

    /** Evaluates an infix expression, e.g. {@code "3 + 4 * (2 - 1)"}. */
    public double evaluate(String infixExpression) {
        List<String> tokens = tokenizer.tokenize(infixExpression);
        List<String> rpn = converter.toRpn(tokens);
        return rpnEvaluator.evaluate(rpn);
    }

    /** Converts an infix expression into its Reverse Polish Notation form. */
    public List<String> toRpn(String infixExpression) {
        return converter.toRpn(tokenizer.tokenize(infixExpression));
    }
}
