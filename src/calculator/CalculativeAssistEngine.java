package calculator;

import calculator.helper.Helper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CalculativeAssistEngine implements ActionListener, WindowListener {

    public CalculativeAssistEngine() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            String a = ((JButton) e.getSource()).getText();
            Helper.showMessage(a);
            switch (((JButton) e.getSource()).getText()) {
                case "+":break;
                case "-":break;
                case "X":break;
                case "/":break;
                case "=":break;
                case "C":break;
                default: break;
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
