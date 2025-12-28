package oncall.domain.date;

import java.util.List;

public class WorkCalender {
    private final WorkMonth workMonth;
    private final List<Day> calender;

    public WorkCalender(List<Day> calender, WorkMonth workMonth) {
        this.calender = calender;
        this.workMonth = workMonth;
    }

    public List<Day> getCalender() {
        return List.copyOf(calender);
    }

    public WorkMonth getWorkMonth() {
        return workMonth;
    }
}
