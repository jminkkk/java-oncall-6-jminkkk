package oncall.util.validator;

import java.util.List;

public class MonthDayValidator {
    private static final String MONTH_DAY_SPLITTER = " ";
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;
    public static void validateMonthDays(String monthDays) {
        String[] arr = monthDays.split(MONTH_DAY_SPLITTER);
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
        if (month < MIN_MONTH || month > MAX_MONTH) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }
}
