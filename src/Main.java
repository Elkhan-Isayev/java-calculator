import calculator.Calculator;
import calculator.helper.Helper;

public class Main {
    public static void main(String[] args) {
        //  Check terminal arguments if they exist
        if(args.length > 0) {
            Helper.showMessage(args);
            Helper.showMessage(Helper.startErrorMessage);
            return;
        }
        //  Create and run calculator
        final Calculator calculator = Calculator.getCalculator();
        calculator.run();
    }
}
