package oncall.util;

import java.util.Arrays;
import java.util.List;

public class Validator {
    public static void validateMonthDays(String monthDays) {
        String[] arr = monthDays.split(",");
        int month = Integer.parseInt(arr[0]);
        validateMonth(month);
        String day = arr[1];
        validateDay(day);
    }

    private static void validateDay(String day) {
        List<String> days = List.of("월", "화", "수", "목", "금", "토", "일");
        if (!days.contains(day)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateWorkers(String weekdayWorkers, String holidayWorkers) {
        String[] weekdayWorkersArr = weekdayWorkers.split(",");
        String[] holidayWorkersArr = holidayWorkers.split(",");

        validateWorkerDuplication(weekdayWorkersArr);
        validateWorkerDuplication(holidayWorkersArr);
    }
    private static void validateWorkerDuplication(String[] workers) {
        int workerCount = (int) Arrays.stream(workers)
                .distinct()
                .count();

        if (workerCount != workers.length) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }
}
