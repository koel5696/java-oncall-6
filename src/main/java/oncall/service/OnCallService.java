package oncall.service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import oncall.domain.date.Day;
import oncall.domain.date.StartDayOfWeek;
import oncall.domain.date.WorkCalender;
import oncall.domain.date.WorkMonth;
import oncall.domain.workers.Workers;

public class OnCallService {

    public WorkCalender createCalender(WorkMonth workMonth, String startDayOfWeek) {
        List<Day> calender = new ArrayList<>();
        int lengthOfMonth = workMonth.createLengthOfMonth();
        DayOfWeek start = StartDayOfWeek.createWorkMonth(startDayOfWeek);

        for (int i = 0; i < lengthOfMonth; i++) {
            DayOfWeek currentDay = start.plus(i);
            calender.add(new Day(workMonth.getWorkMonth(), i + 1, currentDay));
        }

        return new WorkCalender(calender, workMonth);
    }

    public Map<Day, String> onCall(WorkCalender workCalender, Workers week, Workers Holiday) {
        Map<Day, String> result = new LinkedHashMap<>();

        List<String> weekWorkers = week.getWorkers();
        List<String> holidayWorkers = Holiday.getWorkers();

        int weekIndex = 0;
        int holidayIndex = 0;
        String previousWorker = "";

        for (Day day : workCalender.getCalender()) {
            String todayWorker;

            if (day.isHoliday()) {
                todayWorker = findTodayWorker(holidayWorkers, holidayIndex, previousWorker);
                holidayIndex++;
            } else {
                todayWorker = findTodayWorker(weekWorkers, weekIndex, previousWorker);
                weekIndex++;
            }
            previousWorker = todayWorker;
            result.put(day, todayWorker);
        }
        return result;
    }

    private String findTodayWorker(List<String> workers, int index, String preWorker) {
        int currentIdx = index % workers.size(); // 순환 구조
        int nextIdx = (index + 1) % workers.size();

        String currentWorker = workers.get(currentIdx);
        if (currentWorker.equals(preWorker)) {
            Collections.swap(workers, currentIdx, nextIdx);
            return workers.get(currentIdx);
        }
        return currentWorker;
    }

}
