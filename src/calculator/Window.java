package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Window extends JFrame implements WindowListener, ActionListener {
    private TextField textField = new TextField();
    //  Create btns
    private final JButton btn_7 = new JButton("7");
    private final JButton btn_8 = new JButton("8");
    private final JButton btn_9 = new JButton("9");
    private final JButton btn_10 = new JButton("10");


    public Window() {
        //  Set empty layout manager
        setLayout(null);
        setSize(400, 600);
        setVisible(true);
        //  Set spawn location
        setLocation(100, 100);

        //  Create arial font
        Font arialFont = new Font("Arial", Font.BOLD, 40);
        //  Create text field
        textField.setFont(arialFont);
        textField.setSize(330, 60);
        textField.setLocation(25, 25);
        textField.setVisible(true);
        add(textField);
        //  Create button 9
        btn_9.setFont(arialFont);
        btn_9.setSize(60, 60);
        btn_9.setLocation(105, 225);
        btn_9.setVisible(true);
        btn_9.addActionListener(this);
        add(btn_9);
        //  Create button 7
        btn_7.setFont(arialFont);
        btn_7.setSize(60, 60);
        btn_7.setLocation(25, 225);
        btn_7.setVisible(true);
        btn_7.addActionListener(this);
        add(btn_7);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
//        if (btn_7.equals(e.getSource())) {
//            System.out.println("ok");
//        }
        if(e.getSource() instanceof JButton) {
            String a = ((JButton) e.getSource()).getText();
            System.out.println(a);
//            String btnText = (JButton)e.getText();
        }

//        System.out.println(e);
//        textField.setText(e.getSource().getText());
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
