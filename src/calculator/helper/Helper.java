package calculator.helper;

/*
*   This class assist other classes via static methods
* */

import java.util.Arrays;

public class Helper {
    public static final String startErrorMessage = "Critical error, please try again.";

    public static void showMessage(String str) {
        System.out.println(str);
    }

    public static void showMessage(String[] str) {
        System.out.println(Arrays.toString(str));
    }
}
