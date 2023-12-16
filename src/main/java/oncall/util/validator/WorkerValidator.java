package oncall.util.validator;

import java.util.Arrays;

public class WorkerValidator {
    public static void validateWorkers(String weekdayWorkers, String holidayWorkers) {
        String[] weekdayWorkersArr = weekdayWorkers.split(",");
        String[] holidayWorkersArr = holidayWorkers.split(",");

        validateWorkerDuplication(weekdayWorkersArr);
        validateWorkerDuplication(holidayWorkersArr);
        validateWeekdayToHoliday(weekdayWorkersArr, holidayWorkersArr);
        Arrays.stream(weekdayWorkersArr).forEach(WorkerValidator::validateNickname);
        Arrays.stream(holidayWorkersArr).forEach(WorkerValidator::validateNickname);
        validateWorkerCount(weekdayWorkersArr.length);
        validateWorkerCount(holidayWorkersArr.length);
    }
    private static void validateWorkerDuplication(String[] workers) {
        int workerCount = (int) Arrays.stream(workers)
                .distinct()
                .count();

        if (workerCount != workers.length) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.(입력받은 이름 중 중복이 존재하는 경우)");
        }
    }

    private static void validateWeekdayToHoliday(String[] weekdayWorkersArr, String[] holidayWorkersArr) {
        if (weekdayWorkersArr.length != holidayWorkersArr.length) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.(평일 순번, 휴일 순번 입력 개수가 다른 경우)");
        }

        int notContainCount = (int) Arrays.stream(weekdayWorkersArr)
                .filter(worker -> !Arrays.asList(holidayWorkersArr).contains(worker))
                .count();
        if (notContainCount != 0) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.(평일 순번, 휴일 순번에 각각 1회 편성되지 않은 경우)");
        }
    }

    private static void validateNickname(String nickname) {
        if (nickname.length() > 5) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.(닉네임 길이가 5자를 초과하는 경우)");
        }
    }

    private static void validateWorkerCount(int workerCount) {
        if (workerCount < 5 || workerCount > 35) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.(근무자 수가 5명 미만 또는 35명 초과인 경우)");
        }
    }
}
