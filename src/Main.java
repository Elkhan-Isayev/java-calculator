import calculator.Calculator;
import calculator.helper.Helper;

public class Main {
    public static void main(String[] args) {
        //  Get terminal arguments if they exist
        if(args.length > 0) {
            Helper.showMessage(args);
        }
        //  Create and run calculator
        final Calculator calculator = new Calculator();
        calculator.run();
    }
}
