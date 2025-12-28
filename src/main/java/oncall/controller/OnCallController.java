package oncall.controller;

import java.util.List;
import java.util.Map;
import oncall.domain.date.Day;
import oncall.domain.date.WorkCalender;
import oncall.domain.date.WorkMonth;
import oncall.domain.workers.Workers;
import oncall.service.OnCallService;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OnCallController {
    private final InputView inputView;
    private final OutputView outputView;
    private final OnCallService onCallService;

    public OnCallController(OutputView outputView, InputView inputView, OnCallService onCallService) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.onCallService = onCallService;
    }

    public void run() {
        WorkCalender workCalender = readEmergencyDate();
        Workers weekWorkers = new Workers(readWorkers("평일"));
        Workers holidayWorkers = new Workers(readWorkers("휴일"));
        weekWorkers.compareWorker(holidayWorkers);
        Map<Day, String> result = onCallService.onCall(workCalender, weekWorkers, holidayWorkers);
        outputView.printResult(result, workCalender.getWorkMonth());
    }

    private WorkCalender readEmergencyDate() {
        while (true) {
            try {
                String[] date = inputView.inputDate().split(",");
                return onCallService.createCalender(new WorkMonth(date[0]), date[1]);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<String> readWorkers(String day) {
        while (true) {
            try {
                String[] workers = inputView.inputWeekWorkers(day).split(",");
                return List.of(workers);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

}
