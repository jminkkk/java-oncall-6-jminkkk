package oncall.controller;

import static oncall.util.WorkerFactory.createWorkers;

import java.util.List;
import oncall.domain.Calendar;
import oncall.domain.DaysOfTheWeek;
import oncall.domain.EmergencySchedule;
import oncall.util.validator.MonthDayValidator;
import oncall.util.validator.WorkerValidator;
import oncall.view.InputView;
import oncall.view.OutputView;

public class MainController {
    private final ScheduleController scheduleController;

    public MainController(ScheduleController scheduleController) {
        this.scheduleController = scheduleController;
    }

    public void run() {
        String[] monthDays = inputMonthDays().split(",");
        Calendar month = Calendar.of(Integer.parseInt(monthDays[0]));
        DaysOfTheWeek yoil = DaysOfTheWeek.of(monthDays[1]);

        List<String> inputWorkers = inputWorkers();
        List<String> weekdayWorkers = createWorkers(inputWorkers.get(0));
        List<String> holidayWorkers = createWorkers(inputWorkers.get(1));

        EmergencySchedule emergencySchedule = scheduleController.schedule(month, yoil, weekdayWorkers, holidayWorkers);
        OutputView.println();
        OutputView.println(emergencySchedule.toString());
    }

    private String inputMonthDays() {
        OutputView.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        try {
            String monthDay = InputView.input();
            MonthDayValidator.validateMonthDays(monthDay);
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
            WorkerValidator.validateWorkers(weekdayWorkers);
            String holidayWorkers = inputHolidayWorkers();
            WorkerValidator.validateWorkers(holidayWorkers);
            WorkerValidator.validateWorkers(weekdayWorkers, holidayWorkers);
            return List.of(weekdayWorkers, holidayWorkers);
        } catch (IllegalArgumentException e) {
            OutputView.println(e.getMessage());
            return inputWorkers();
        }
    }
}
