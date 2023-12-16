package oncall.controller;

import java.util.List;
import java.util.function.Supplier;
import oncall.domain.DayOff;
import oncall.domain.date.DateCalculator;
import oncall.domain.date.dto.DateDto;
import oncall.domain.schedular.Scheduler;
import oncall.domain.worker.dto.WorkerDto;
import oncall.util.formatter.ScheduleFormatter;
import oncall.view.input.InputView;
import oncall.view.output.OutputView;

public class OnCallController {

    private final InputView inputView;
    private final OutputView outputView;

    public OnCallController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        DateDto dateChoice = receiveValidatedInput(inputView::getMonthAndDate);
        WorkerDto workerDto = receiveValidatedInput(inputView::getWorkerNames);

        List<String> dates = new DateCalculator().getDates(dateChoice.date(), dateChoice.month().getEndDate());
        List<Integer> dayOffWeeks = DayOff.getDayOffByMonth(dateChoice.month());

        Scheduler scheduler = new Scheduler(workerDto.weekDayWorker(), workerDto.weekendWorker());

        List<String> scheduleResult = scheduler.getSchedule(dates, dayOffWeeks);
        List<String> formattedResult = new ScheduleFormatter().format(dateChoice.month(), dates, scheduleResult,
                dayOffWeeks);

        outputView.printSchedule(formattedResult);
    }

    private <T> T receiveValidatedInput(Supplier<T> inputSupplier) {
        try {
            return inputSupplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
            return receiveValidatedInput(inputSupplier);
        }
    }
}
