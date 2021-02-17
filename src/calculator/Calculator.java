package calculator;


//  Alert: singleton used
public class Calculator {
    private static Calculator calculator;

    private Calculator() {}

    public void run() {
        //  Create layout window
        final Window window = new Window();
        //  Create operational engine
        final CalculativeAssistEngine calculativeAssistEngine = new CalculativeAssistEngine();
        //  Create artisan that draw UI part
        new Artisan(window, calculativeAssistEngine);
    }

    public static synchronized Calculator getCalculator() {
        if(calculator == null) {
            calculator = new Calculator();
        }
        return calculator;
    }
}


