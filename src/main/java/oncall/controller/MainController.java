package oncall.controller;

import java.util.List;
import oncall.util.Validator;
import oncall.view.InputView;
import oncall.view.OutputView;

public class MainController {
    public void run() {
        String[] monthDays = inputMonthDays().split(",");
        int month = Integer.parseInt(monthDays[0]);
        String day = monthDays[1];

        List<String> inputWorkers = inputWorkers();
        String weekdayWorkers = inputWorkers.get(0);
        String holidayWorkers = inputWorkers.get(1);

        OutputView.println();
    }

    private String inputMonthDays() {
        OutputView.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        try {
            String monthDay = InputView.input();
            Validator.validateMonthDays(monthDay);
            return monthDay;
        } catch (NumberFormatException e) {
            OutputView.println("[ERROR] 요일은 숫자만 가능합니다. 다시 입력해주세요.");
        } catch (IllegalArgumentException e) {
            OutputView.println("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
        return inputMonthDays();
    }

    private String inputWeekdayWorkers() {
        OutputView.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        return InputView.input();
    }

    private String inputHolidayWorkers() {
        OutputView.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        return InputView.input();
    }

    private List<String> inputWorkers() {
        try {
            String weekdayWorkers = inputWeekdayWorkers();
            String holidayWorkers = inputHolidayWorkers();
            Validator.validateWorkers(weekdayWorkers, holidayWorkers);
            return List.of(weekdayWorkers, holidayWorkers);
        } catch (IllegalArgumentException e) {
            OutputView.println(e.getMessage());
            return inputWorkers();
        }
    }
}
