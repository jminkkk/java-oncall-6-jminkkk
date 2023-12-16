package oncall.controller;

import static oncall.util.WorkerFactory.createWorkers;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.Calendar;
import oncall.domain.DayWorker;
import oncall.domain.DaysOfTheWeek;
import oncall.domain.EmergencySchedule;
import oncall.util.Validator;
import oncall.view.InputView;
import oncall.view.OutputView;

public class MainController {
    public void run() {
        String[] monthDays = inputMonthDays().split(",");
        Calendar month = Calendar.of(Integer.parseInt(monthDays[0]));
        DaysOfTheWeek yoil = DaysOfTheWeek.of(monthDays[1]);

        List<String> inputWorkers = inputWorkers();
        List<String> weekdayWorkers = createWorkers(inputWorkers.get(0));
        List<String> holidayWorkers = createWorkers(inputWorkers.get(1));

        EmergencySchedule emergencySchedule = createEmergencySchedule(month, yoil, weekdayWorkers, holidayWorkers);
        OutputView.println();
        OutputView.println(emergencySchedule.toString());
    }

    private EmergencySchedule createEmergencySchedule(Calendar month, DaysOfTheWeek yoil, List<String> weekdayWorkers,
                                                      List<String> holidayWorkers) {
        int weekDayIndex = 0;
        int holidayIndex = 0;
        int yoilIndex = yoil.getIndex();

        List<DayWorker> workers = new ArrayList<>();
        int lastDay = month.getLastDay();

        for (int i = 0; i < lastDay; i++) {

            DaysOfTheWeek newYoil = DaysOfTheWeek.of(yoilIndex++);
            if (newYoil.isHoliday() || month.getHolidays().contains(i + 1)) {
                DayWorker dayWorker = new DayWorker(true, holidayWorkers.get(holidayIndex));
                workers.add(dayWorker);
                holidayIndex = (holidayIndex + 1) % holidayWorkers.size();
            } else {
                DayWorker dayWorker = new DayWorker(false, weekdayWorkers.get(weekDayIndex));
                workers.add(dayWorker);
                weekDayIndex = (weekDayIndex + 1) % weekdayWorkers.size();
            }

            yoilIndex = yoilIndex % 7;
        }

        List<DayWorker> finalWorkers = changeContinuousWorker(month, workers);
        return new EmergencySchedule(month, yoil, finalWorkers);
    }

    private List<DayWorker> changeContinuousWorker(Calendar month, List<DayWorker> workers) {
        for (int i = 0; i < workers.size() - 1; i++) {
            if (workers.get(i).equals(workers.get(i + 1))) {
                System.out.println(workers.get(i) + "!!!");
                DayWorker changeWorker = workers.get(i + 1);

                // 바꿀만한 얘 찾기
                for (int j = i + 2; j < workers.size(); j++) {
                    if ((changeWorker.isHolidayWorker() && workers.get(j).isHolidayWorker())
                            || (!changeWorker.isHolidayWorker() && !workers.get(j).isHolidayWorker())) {
                        System.out.println(i + 1 + " " + workers.get(j));
                        workers.add(i + 1, workers.get(j));
                        workers.remove(j + 1);
                        break;
                    }
                }
            }
        }
        return workers;
    }

    private String inputMonthDays() {
        OutputView.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        try {
            String monthDay = InputView.input();
            Validator.validateMonthDays(monthDay);
            return monthDay;
        } catch (NumberFormatException e) {
            OutputView.println();
            OutputView.println("[ERROR] 요일은 숫자만 가능합니다. 다시 입력해주세요.");
        } catch (IllegalArgumentException e) {
            OutputView.println();
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
            OutputView.println();
            OutputView.println(e.getMessage());
            return inputWorkers();
        }
    }
}
