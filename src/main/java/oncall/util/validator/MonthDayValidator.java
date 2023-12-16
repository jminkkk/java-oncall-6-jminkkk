package oncall.util.validator;

import java.util.List;

public class MonthDayValidator {
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
}
