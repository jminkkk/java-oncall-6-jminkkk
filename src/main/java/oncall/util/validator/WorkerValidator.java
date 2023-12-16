package oncall.util.validator;

import java.util.Arrays;

public class WorkerValidator {
    public static void validateWorkers(String worker) {
        String[] workers = worker.split(",");
        validateWorkerDuplication(workers);
        Arrays.stream(workers).forEach(WorkerValidator::validateNickname);
        validateWorkerCount(workers.length);
    }
    public static void validateWorkers(String weekdayWorkers, String holidayWorkers) {
        String[] weekdayWorkersArr = weekdayWorkers.split(",");
        String[] holidayWorkersArr = holidayWorkers.split(",");

        validateWeekdayToHoliday(weekdayWorkersArr, holidayWorkersArr);
    }
    private static void validateWorkerDuplication(String[] workers) {
        int workerCount = (int) Arrays.stream(workers)
                .distinct()
                .count();

        if (workerCount != workers.length) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateWeekdayToHoliday(String[] weekdayWorkersArr, String[] holidayWorkersArr) {
        if (weekdayWorkersArr.length != holidayWorkersArr.length) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }

        int notContainCount = (int) Arrays.stream(weekdayWorkersArr)
                .filter(worker -> !Arrays.asList(holidayWorkersArr).contains(worker))
                .count();

        if (notContainCount != 0) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateNickname(String nickname) {
        if (nickname.length() > 5) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateWorkerCount(int workerCount) {
        if (workerCount < 5 || workerCount > 35) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }
}
