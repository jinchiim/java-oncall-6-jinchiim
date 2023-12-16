package oncall.view.input.error;

public class InputIllegalArgumentException extends IllegalArgumentException{

    private static final String ERROR_SYMBOL = "[ERROR] ";

    public InputIllegalArgumentException(InputError inputError) {
        super(ERROR_SYMBOL + inputError.message);
    }

    public InputIllegalArgumentException(InputError inputError, Object... args) {
        super(String.format(ERROR_SYMBOL + inputError.message, args));
    }
}
