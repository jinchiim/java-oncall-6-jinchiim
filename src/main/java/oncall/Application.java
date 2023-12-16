package oncall;


import oncall.controller.OnCallController;
import oncall.view.input.InputView;
import oncall.view.output.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        new OnCallController(inputView, outputView).run();
    }
}