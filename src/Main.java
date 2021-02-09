import calculator.Calculator;
import calculator.helper.Helper;

public class Main {
    public static void main(String[] args) {
        // Get terminal arguments if they exist
        if(args.length > 0) {
            Helper.showMessage(args);
        }

        /*
        *   Create calculator
        *   Run calculator and get starting status
        *   If it returns false, we represent error message
        * */
        final Calculator calculator = new Calculator();
        final boolean calculatorStatus = calculator.run();
        if(!calculatorStatus) {
            Helper.showMessage(Helper.startErrorMessage);
        }
    }
}
