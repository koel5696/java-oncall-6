package oncall.domain.date;

import java.util.Arrays;

public enum Holiday {
    JANUARY_ONE(1, 1),
    MARCH_ONE(3, 1),
    MAY_FIVE(5, 5),
    JUNE_SIX(6, 6),
    AUGUST_FIFTY(8, 15),
    OCTOBER_THREE(10, 3),
    OCTOBER_NINE(10, 9),
    DECEMBER_TWENTY_FIVE(12, 25);

    private final int month;
    private final int day;

    Holiday(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public static boolean isHoliday(int month, int day) {
        for (Holiday holiday : Holiday.values()) {
            if (holiday.month == month && holiday.day == day) {
                return true;
            }
        }
        return false;
    }

    public static Holiday findHoliday(int month, int day) {
        return Arrays.stream(values())
                .filter(h -> h.month == month && h.day == day)
                .findFirst()
                .orElse(null);
    }
}
