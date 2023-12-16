package oncall.controller;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.Calendar;
import oncall.domain.DayWorker;
import oncall.domain.DaysOfTheWeek;
import oncall.domain.EmergencySchedule;

public class ScheduleController {
    private int weekDayIndex = 0;
    private int holidayIndex = 0;
    public EmergencySchedule schedule(Calendar month, DaysOfTheWeek yoil, List<String> weekdayWorkers,
                                      List<String> holidayWorkers) {
        int yoilIndex = yoil.getIndex();
        List<DayWorker> workers = new ArrayList<>();
        int lastDay = month.getLastDay();

        for (int i = 0; i < lastDay; i++) {
            DaysOfTheWeek newYoil = DaysOfTheWeek.of(yoilIndex++);
            workers.add(assignWorker(month, newYoil, weekdayWorkers, holidayWorkers, i + 1));
            yoilIndex = yoilIndex % 7;
        }

        List<DayWorker> finalWorkers = changeContinuousWorker(workers);
        return new EmergencySchedule(month, yoil, finalWorkers);
    }

    private DayWorker assignWorker(Calendar month, DaysOfTheWeek newYoil, List<String> weekdayWorkers,
                                   List<String> holidayWorkers, int day) {
        if (newYoil.isWeekend() || month.getHolidays().contains(day)) {
            holidayIndex = holidayIndex % holidayWorkers.size();
            return new DayWorker(true, holidayWorkers.get(holidayIndex++));
        }

        weekDayIndex = weekDayIndex % weekdayWorkers.size();
        return new DayWorker(false, weekdayWorkers.get(weekDayIndex++));
    }

    private List<DayWorker> changeContinuousWorker(List<DayWorker> workers) {
        for (int i = 0; i < workers.size() - 1; i++) {
            if (workers.get(i).equals(workers.get(i + 1))) {
                int changeIndex = findChangeWorkerIndex(workers, i + 1);
                workers.add(i + 1, workers.get(changeIndex));
                workers.remove(changeIndex + 1);
                break;
            }
        }
        return workers;
    }

    private int findChangeWorkerIndex(List<DayWorker> workers, int index) {
        DayWorker changeWorker = workers.get(index);
        for (int i = index + 1; i < workers.size(); i++) {
            if ((changeWorker.isHolidayWorker() && workers.get(i).isHolidayWorker())
                    || (!changeWorker.isHolidayWorker() && !workers.get(i).isHolidayWorker())) {
                return i;
            }
        }
        return -1;
    }
}
