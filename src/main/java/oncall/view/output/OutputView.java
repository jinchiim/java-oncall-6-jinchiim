package oncall.view.output;

import java.util.List;

public class OutputView implements Printer {

    @Override
    public void printMessage(Output output) {
        System.out.println(output.message);
    }

    @Override
    public void printfMessage(Output output, Object... args) {
        System.out.printf(output.message, args);
    }

    @Override
    public void printError(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public void printSchedule(List<String> formattedResult) {
        for (String result : formattedResult) {
            System.out.println(result);
        }
    }
}
