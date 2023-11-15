package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class VisitDateRequestController extends RequestController {

    public VisitDateRequestController(InputView inputView, OutputView outputView) {
        super(inputView, outputView);
    }

    @Override
    public String input() {
        return inputView.inputVisitDate();
    }
}
