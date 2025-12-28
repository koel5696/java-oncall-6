package oncall.domain.date;

import java.time.DayOfWeek;

public class Day {
    private final int day;
    private final DayOfWeek dayOfWeek;
    private final Holiday holiday;

    public Day(int month, int day, DayOfWeek dayOfWeek) {
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.holiday = Holiday.findHoliday(month, day);
    }

    public boolean isHoliday() {
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY || holiday != null;
    }

    public boolean specialHoliday() {
        return holiday != null;
    }


    public int getDay() {
        return day;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
