package oncall.domain;

import java.util.List;

public class EmergencySchedule {
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
                yoil += "(휴일)";
            }

            sb.append(month.getMonth()).append("월 ")
                    .append(i + 1).append("일 ")
                    .append(yoil + " ")
                    .append(workers.get(i)).append("\n");
            firstYoilIndex++;
        }
        return sb.toString();
    }
}
