package oncall.util.validator;

import java.util.Arrays;

public class WorkerValidator {
    private static final String NAME_SPLITTER = ",";
    private static final int ZERO = 0;
    private static final int MAX_WORKER_NICKNAME_LENGTH = 5;
    private static final int MIN_WORKER_COUNT = 5;
    private static final int MAX_WORKER_COUNT = 35;

    public static void validateWorkers(String worker) {
        String[] workers = worker.split(NAME_SPLITTER);
        validateWorkerDuplication(workers);
        Arrays.stream(workers).forEach(WorkerValidator::validateNickname);
        validateWorkerCount(workers.length);
    }

    public static void validateWorkers(String weekdayWorkers, String holidayWorkers) {
        String[] weekdayWorkersArr = weekdayWorkers.split(NAME_SPLITTER);
        String[] holidayWorkersArr = holidayWorkers.split(NAME_SPLITTER);

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

        if (notContainCount != ZERO) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateNickname(String nickname) {
        if (nickname.length() > MAX_WORKER_NICKNAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateWorkerCount(int workerCount) {
        if (workerCount < MIN_WORKER_COUNT || workerCount > MAX_WORKER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }
}
