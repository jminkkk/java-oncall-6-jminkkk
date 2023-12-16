package oncall.domain;

import java.util.List;

public class EmergencySchedule {
    private static final String MONTH = "월 ";
    private static final String DAY = "일 ";
    private static final String HOLIDAY = "(휴일)";
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";
    private final Calendar month;
    private final DaysOfTheWeek firstYoil;
    private final List<DayWorker> workers;

    public EmergencySchedule(Calendar month, DaysOfTheWeek firstYoil, List<DayWorker> workers) {
        this.month = month;
        this.firstYoil = firstYoil;
        this.workers = workers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int firstYoilIndex = firstYoil.getIndex();
        for (int i = 0; i < month.getLastDay(); i++) {
            String yoil = DaysOfTheWeek.of(firstYoilIndex % 7).toString();

            if (month.getHolidays().contains(i + 1)) {
                yoil += HOLIDAY;
            }

            sb.append(month.getMonth()).append(MONTH)
                    .append(i + 1).append(DAY)
                    .append(yoil).append(SPACE)
                    .append(workers.get(i)).append(NEW_LINE);
            firstYoilIndex++;
        }
        return sb.toString();
    }
}
