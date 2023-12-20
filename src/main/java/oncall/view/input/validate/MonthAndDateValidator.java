package oncall.view.input.validate;

import java.util.Arrays;
import java.util.List;
import oncall.domain.Month;
import oncall.domain.date.dto.DateDto;
import oncall.view.input.error.InputError;
import oncall.view.input.error.InputIllegalArgumentException;

public class MonthAndDateValidator {

    private static final List<String> weeks = List.of("일", "월", "화", "수", "목", "금", "토");

    public DateDto validate(String inputValue) {
        validateNotBlank(inputValue);
        List<String> value = divideToList(inputValue);
        return toDto(value);
    }

    private void validateNotBlank(String inputValue) {
        if (inputValue.isBlank()) {
            throw new InputIllegalArgumentException(InputError.INPUT_IS_BLANK);
        }
    }

    private List<String> divideToList(String inputValue) {
        return Arrays.stream(inputValue.split(","))
                .map(String::trim)
                .toList();
    }

    private DateDto toDto(List<String> startMonthAndDate) {
        Month month = Month.getMonthByNumber(startMonthAndDate.get(0));
        validateWeeks(startMonthAndDate.get(1));

        return new DateDto(month, startMonthAndDate.get(1));
    }

    private void validateWeeks(String selectWeek) {
        if (!weeks.contains(selectWeek)) {
            throw new InputIllegalArgumentException(InputError.NOT_POSSIBLE_INPUT_RANGE);
        }
    }
}
