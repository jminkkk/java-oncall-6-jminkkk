package oncall.domain;

import java.util.Arrays;

public enum DaysOfTheWeek {
    MONDAY(0, "월", false),
    TUESDAY(1, "화", false),
    WEDNESDAY(2, "수", false),
    THURSDAY(3, "목", false),
    FRIDAY(4, "금", false),
    SATURDAY(5, "토", true),
    SUNDAY(6, "일", true);
    private final int index;
    private final String day;
    private final boolean isHoliday;

    DaysOfTheWeek(int index, String day, boolean isHoliday) {
        this.index = index;
        this.day = day;
        this.isHoliday = isHoliday;
    }

    public int getIndex() {
        return index;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    @Override
    public String toString() {
        return day;
    }

    public static DaysOfTheWeek of(String monthDay) {
        return Arrays.stream(DaysOfTheWeek.values())
                .filter(yoil -> yoil.day.equals(monthDay))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 요일이 존재하지 않습니다."));
    }

    public static DaysOfTheWeek of(int index) {
        return Arrays.stream(DaysOfTheWeek.values())
                .filter(yoil -> yoil.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 요일이 존재하지 않습니다."));
    }
}
