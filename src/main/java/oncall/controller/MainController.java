package oncall.controller;

import oncall.util.Validator;
import oncall.view.InputView;
import oncall.view.OutputView;

public class MainController {
    public void run() {
        OutputView.println();
    }

    private String inputMonthDays() {
        OutputView.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        try {
            InputView.input();
            Validator.validateMonthDays(InputView.input());
        } catch (NumberFormatException e) {
            OutputView.println("[ERROR] 요일은 숫자만 가능합니다. 다시 입력해주세요.");
        } catch (IllegalArgumentException e) {
            OutputView.println("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
        return inputMonthDays();
    }
}
