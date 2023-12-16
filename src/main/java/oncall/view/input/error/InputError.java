package oncall.view.input.error;

public enum InputError {

    INPUT_IS_BLANK("입력한 값이 빈 칸 입니다."),
    NOT_POSSIBLE_INPUT_RANGE("허용되지 않은 범위의 입력입니다.");

    final String message;

    InputError(String message) {
        this.message = message;
    }
}
