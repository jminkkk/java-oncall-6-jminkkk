package oncall.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputView {
    public static String input() {
        return readLine();
    }

    public static int inputNumber() {
        String input = readLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return inputNumber();
        }
    }
}
