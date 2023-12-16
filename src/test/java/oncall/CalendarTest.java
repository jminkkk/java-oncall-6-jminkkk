package oncall;

import static org.assertj.core.api.Assertions.assertThat;

import oncall.domain.Calendar;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalendarTest {
    @ParameterizedTest
    @CsvSource({"JANUARY, 1", "MARCH, 1", "MAY, 5", "JUNE, 6", "AUGUST, 15", "OCTOBER, 3", "OCTOBER, 9", "DECEMBER, 25"})
    void holiday(Calendar calendar, int holiday) {
        assertThat(calendar.getHolidays()).contains(holiday);
    }
}
