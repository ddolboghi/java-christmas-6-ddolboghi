package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public abstract class RequestController {
    protected final InputView inputView;
    protected final OutputView outputView;

    public RequestController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public String inputRequest() {
        do {
            try {
                return input();
            } catch (IllegalArgumentException e) {
                outputView.outputErrorMessage(e);
            }
        } while (true);
    }

    public abstract String input();
}
