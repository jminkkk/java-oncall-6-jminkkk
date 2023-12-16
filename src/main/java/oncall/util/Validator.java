package oncall.util;

public class Validator {
    public static void validateMonthDays(String monthDays) {
        String[] arr = monthDays.split(",");
        int month = Integer.parseInt(arr[0]);
        String day = arr[1];
    }

    private static void validateMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }
}
