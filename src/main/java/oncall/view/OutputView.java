package oncall.view;

import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;
import oncall.domain.date.Day;
import oncall.domain.date.WorkMonth;

public class OutputView {

    public void printResult(Map<Day, String> result, WorkMonth workMonth) {
        int month = workMonth.getWorkMonth();
        result.forEach((day, name) -> {
            int date = day.getDay();
            String korDay = day.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);
            if (day.specialHoliday()) {
                System.out.println(month + "월 " + date + "일 " + korDay + "(휴일) " + name);
            } else {
                System.out.println(month + "월 " + date + "일 " + korDay + " " + name);
            }
        });
    }


    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
