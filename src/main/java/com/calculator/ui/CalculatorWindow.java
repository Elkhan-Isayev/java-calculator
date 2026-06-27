package com.calculator.ui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * The Swing view: a read-only display on top of a 5x4 keypad. It is purely
 * presentation — every button forwards its command to {@link CalculatorController},
 * which holds all of the behaviour.
 */
public class CalculatorWindow extends JFrame {

    private static final Font DISPLAY_FONT = new Font("Arial", Font.BOLD, 36);
    private static final Font BUTTON_FONT = new Font("Arial", Font.PLAIN, 22);

    /**
     * Keypad layout. Where the visible glyph differs from the command sent to the
     * controller, the pair is "glyph:command" (e.g. the "×" button sends "*").
     */
    private static final String[][] KEYPAD = {
            {"C", "(", ")", "⌫"},
            {"7", "8", "9", "÷:/"},
            {"4", "5", "6", "×:*"},
            {"1", "2", "3", "−:-"},
            {"0", ".", "=", "+"},
    };

    public CalculatorWindow() {
        super("Calculator");

        JTextField display = createDisplay();
        CalculatorController controller = new CalculatorController(display);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));
        add(display, BorderLayout.NORTH);
        add(createKeypad(controller), BorderLayout.CENTER);

        setSize(340, 460);
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
    }

    private JTextField createDisplay() {
        JTextField display = new JTextField("0");
        display.setEditable(false);
        display.setFocusable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(DISPLAY_FONT);
        display.setBackground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        return display;
    }

    private JPanel createKeypad(CalculatorController controller) {
        JPanel keypad = new JPanel(new GridLayout(KEYPAD.length, KEYPAD[0].length, 6, 6));
        keypad.setBorder(BorderFactory.createEmptyBorder(0, 8, 8, 8));
        for (String[] row : KEYPAD) {
            for (String key : row) {
                keypad.add(createButton(key, controller));
            }
        }
        return keypad;
    }

    private JButton createButton(String key, CalculatorController controller) {
        int separator = key.indexOf(':');
        String label = separator < 0 ? key : key.substring(0, separator);
        String command = separator < 0 ? key : key.substring(separator + 1);

        JButton button = new JButton(label);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.addActionListener(event -> controller.handle(command));
        return button;
    }
}
