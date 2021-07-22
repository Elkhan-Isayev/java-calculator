package calculator.helper;

/*
*   This class assist other classes via static methods
* */


import java.awt.*;
import java.util.Arrays;

public class    Helper {
    //  Create fonts
    public static final Font arialFont = new Font("Arial", Font.BOLD, 40);
    public static final Font impactFont = new Font("Impact", Font.BOLD, 40);
    //  Create default err message
    public static final String startErrorMessage = "Critical error, please try again.";

    public static void showMessage(String str) {
        System.out.println(str);
    }

    public static void showMessage(String[] str) {
        System.out.println(Arrays.toString(str));
    }
}
