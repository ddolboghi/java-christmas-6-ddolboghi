package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class PlannerController {
    private final InputView inputView;
    private final OutputView outputView;

    public PlannerController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void preview() {
        requestVisitDay();
    }

    private void requestVisitDay() {
        while (true) {
            try {
                inputView.inputVisitDate();
            } catch (IllegalArgumentException e) {
                inputView.printErrorMessage(e);
            }
        }
    }
}
