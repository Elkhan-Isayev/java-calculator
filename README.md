# Java Calculator

A desktop calculator built with Java Swing, backed by a small **Reverse Polish
Notation (RPN)** expression engine. You can use it as a clickable GUI or as a
command-line evaluator.

## Features

- Swing GUI with a display and a 5×4 keypad (digits, `.`, parentheses, `C`, backspace, `=`).
- Full infix expression evaluation: operator precedence, parentheses and unary minus.
- Reverse Polish Notation engine (tokenizer → shunting-yard → RPN evaluator).
- Command-line mode for quick, scriptable calculations.
- Unit-tested core (JUnit 5) and a reproducible Maven build.

## Requirements

- Java 11 (LTS) or newer
- Maven 3.6+ (a Maven wrapper, `./mvnw`, is included so a local Maven install is optional)

## Build & test

```bash
./mvnw clean package      # compile, run the test suite, build the jar
./mvnw test               # run only the tests
```

The runnable jar is produced at `target/java-calculator.jar`.

## Run

GUI mode (no arguments):

```bash
java -jar target/java-calculator.jar
```

Command-line mode (pass an expression):

```bash
java -jar target/java-calculator.jar "3 + 4 * (2 - 1)"
# -> 7
```

## Architecture

The project is intentionally split into **single-responsibility calculation
engines**, each of which does exactly one stage of the work and nothing else.
This is the OOP idea at the heart of the design: small, independently testable
collaborators wired together by a thin facade, instead of one class that does
everything.

The pipeline turns a plain string into a number in three stages:

```
 "3 + 4 * (2 - 1)"
        │
        ▼
 ┌──────────────┐   text  ->  tokens
 │  Tokenizer   │   splits the raw string into numbers, operators, parentheses;
 └──────────────┘   detects unary minus
        │
        ▼
 ┌──────────────────────┐   infix  ->  RPN
 │ ShuntingYardConverter│   reorders tokens by precedence/associativity
 └──────────────────────┘   (Dijkstra's shunting-yard algorithm)
        │
        ▼
 ┌──────────────┐   RPN  ->  result
 │ RpnEvaluator │   evaluates postfix with a single value stack
 └──────────────┘
        │
        ▼
      result (double)
```

`ExpressionEvaluator` is the **facade** that owns only the orchestration and
exposes one method, `evaluate(String)`. Each engine can be unit-tested,
replaced or reused on its own.

### Package layout

| Package                       | Responsibility                                                        |
|-------------------------------|----------------------------------------------------------------------|
| `com.calculator`              | `Main` entry point (chooses GUI vs. command-line mode)               |
| `com.calculator.app`          | `Calculator` — application singleton that launches the UI            |
| `com.calculator.core`         | The calculation engines: `Tokenizer`, `ShuntingYardConverter`, `RpnEvaluator`, `ExpressionEvaluator`, `Operator` |
| `com.calculator.core.exception` | `ExpressionException` — domain error for malformed expressions      |
| `com.calculator.ui`           | Swing view (`CalculatorWindow`) and `CalculatorController` (input state) |
| `com.calculator.util`         | `NumberFormatter` — display formatting                               |

The UI is deliberately "dumb": `CalculatorWindow` only renders buttons and
forwards their commands to `CalculatorController`, which holds the input state
and delegates the actual maths to the `core` engines. Swing and the calculation
logic stay fully decoupled, so the engine has no dependency on the UI.

### What is Reverse Polish Notation?

RPN (postfix) places each operator *after* its operands, which removes the need
for parentheses and precedence rules at evaluation time:

| Infix             | RPN           |
|-------------------|---------------|
| `3 + 4`           | `3 4 +`       |
| `3 + 4 * 2`       | `3 4 2 * +`   |
| `(3 + 4) * 2`     | `3 4 + 2 *`   |
| `-5 + 3`          | `5 ~ 3 +`     |

(`~` is the internal marker for unary minus.) RPN is evaluated in a single pass
with one stack, which is why it is a clean target for the converter.

## Project structure

```
java-calculator/
├── pom.xml
├── mvnw / mvnw.cmd
├── src/
│   ├── main/java/com/calculator/...
│   └── test/java/com/calculator/...
└── README.md
```

## Contributing

Contributions are welcome. Please keep the `core` engines free of UI
dependencies and add tests for new behaviour.
