package oncall.view.input.validate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oncall.view.input.error.InputError;
import oncall.view.input.error.InputIllegalArgumentException;

public class WorkerValidator {

    public List<String> validate(String inputValue) {
        validateNotBlank(inputValue);
        List<String> workers = divideToList(inputValue);
        validateNotBlankContainList(workers);
        validateNotOverMaxLengthList(workers);
        validateNoDuplicateWorkers(workers);
        validateTotalLength(workers);

        return workers;
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

    private void validateNotBlankContainList(List<String> inputValue) {
        if (hasBlank(inputValue)) {
            throw new InputIllegalArgumentException(InputError.INPUT_IS_BLANK);
        }
    }

    private boolean hasBlank(List<String> inputValue) {
        return inputValue.stream()
                .anyMatch(String::isBlank);
    }

    private void validateNotOverMaxLengthList(List<String> inputValue) {
        if (isOverLengthName(inputValue)) {
            throw new InputIllegalArgumentException(InputError.NOT_POSSIBLE_INPUT_RANGE);
        }
    }

    private boolean isOverLengthName(List<String> inputValue) {
        return inputValue.stream()
                .anyMatch(input -> input.length() < 1 || input.length() > 5);
    }

    private void validateNoDuplicateWorkers(List<String> workers) {
        Set<String> uniqueWorkers = new HashSet<>(workers);

        if (uniqueWorkers.size() != workers.size()) {
            throw new InputIllegalArgumentException(InputError.NOT_POSSIBLE_INPUT_RANGE);
        }
    }

    private void validateTotalLength(List<String> inputValue) {
        if (inputValue.size() < 5 || inputValue.size() > 35) {
            throw new InputIllegalArgumentException(InputError.NOT_POSSIBLE_INPUT_RANGE);
        }
    }
}
