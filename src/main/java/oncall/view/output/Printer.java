package oncall.view.output;

public interface Printer {

    void printMessage(Output output);

    void printfMessage(Output output, Object... args);

    void printError(IllegalArgumentException e);
}