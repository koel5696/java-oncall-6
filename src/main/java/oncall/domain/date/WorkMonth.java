package oncall.domain.date;

import java.time.LocalDate;
import java.util.List;

public class WorkMonth {
    private static final List<String> ALLOWED_MONTHS =
            List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");

    private final int workMonth;

    public WorkMonth(String workMonth) {
        this.workMonth = validateWorkMonth(workMonth);
    }

    private int validateWorkMonth(String workMonth) {
        if (!ALLOWED_MONTHS.contains(workMonth)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
        return Integer.parseInt(workMonth);
    }

    public int createLengthOfMonth() {
        return LocalDate.of(2025, workMonth, 1).lengthOfMonth();
    }

    public int getWorkMonth() {
        return workMonth;
    }
}
