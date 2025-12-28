package oncall.domain.date;

import java.time.DayOfWeek;
import java.util.Map;

public class StartDayOfWeek {
    private static final Map<String, DayOfWeek> dayOfWeeks = Map.of(
            "월", DayOfWeek.MONDAY,
            "화", DayOfWeek.TUESDAY,
            "수", DayOfWeek.WEDNESDAY,
            "목", DayOfWeek.THURSDAY,
            "금", DayOfWeek.FRIDAY,
            "토", DayOfWeek.SATURDAY,
            "일", DayOfWeek.SUNDAY
    );

    public static DayOfWeek createWorkMonth(String startDay) {
        DayOfWeek startDayOfWeek = dayOfWeeks.get(startDay);
        if (startDayOfWeek == null) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
        return startDayOfWeek;
    }
}
