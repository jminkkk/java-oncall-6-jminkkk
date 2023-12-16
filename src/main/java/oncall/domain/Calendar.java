package oncall.domain;

import java.util.Arrays;
import java.util.List;

public enum Calendar {
    JANUARY(1, 31, List.of(1)),
    FEBRUARY(2, 28, List.of()),
    MARCH(3, 31, List.of(1)),
    APRIL(4, 30, List.of()),
    MAY(5, 31, List.of(5)),
    JUNE(6, 30, List.of(6)),
    JULY(7, 31, List.of()),
    AUGUST(8, 31, List.of(15)),
    SEPTEMBER(9, 30, List.of()),
    OCTOBER(10, 31, List.of(3, 9)),
    NOVEMBER(11, 30, List.of()),
    DECEMBER(12, 31, List.of(25));
    private final int month;
    private final int lastDay;
    private final List<Integer> holidays;

    Calendar(int month, int lastDay, List<Integer> holidays) {
        this.month = month;
        this.lastDay = lastDay;
        this.holidays = holidays;
    }

    public int getMonth() {
        return month;
    }

    public int getLastDay() {
        return lastDay;
    }

    public List<Integer> getHolidays() {
        return holidays;
    }

    public static Calendar of(int month) {
        return Arrays.stream(Calendar.values())
                .filter(calendar -> calendar.month == month)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 달이 존재하지 않습니다."));
    }
}
