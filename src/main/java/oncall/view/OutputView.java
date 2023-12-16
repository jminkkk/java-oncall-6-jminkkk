package oncall.view;

import baseball.util.ExceptionMessage;
import baseball.util.PrintMessage;

public class OutputView {
    public static void println() {
        System.out.println();
    }
    public static void println(String message) {
        System.out.println(message);
    }
    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(PrintMessage printMessage) {
        println(printMessage.getMessage());
    }
    public static void println(ExceptionMessage exceptionMessage) {
        println(exceptionMessage.getMessage());
    }
}
