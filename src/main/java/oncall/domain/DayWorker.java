package oncall.domain;

import java.util.Objects;

public class DayWorker {
    private final boolean isHolidayWorker;
    private final String name;

    public DayWorker(boolean isHolidayWorker, String name) {
        this.isHolidayWorker = isHolidayWorker;
        this.name = name;
    }

    public boolean isHolidayWorker() {
        return isHolidayWorker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DayWorker dayWorker = (DayWorker) o;
        return Objects.equals(name, dayWorker.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
