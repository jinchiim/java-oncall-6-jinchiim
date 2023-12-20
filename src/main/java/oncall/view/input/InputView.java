package oncall.view.input;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import oncall.domain.date.dto.DateDto;
import oncall.domain.worker.dto.WorkerDto;
import oncall.view.input.validate.MonthAndDateValidator;
import oncall.view.input.validate.WorkerValidator;

public class InputView implements Reader {

    @Override
    public String readLine() {
        return Console.readLine();
    }

    public DateDto getMonthAndDate() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        return new MonthAndDateValidator().validate(readLine());
    }

    public WorkerDto getWorkerNames() {
        WorkerValidator workerValidator = new WorkerValidator();

        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        List<String> weekDayWorker = workerValidator.validate(readLine());

        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        List<String> weekendWorker = workerValidator.validate(readLine());

        return new WorkerDto(weekDayWorker, weekendWorker);
    }
}