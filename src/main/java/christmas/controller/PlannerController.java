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
        inputVisitDate();
        inputOrder();
    }

    private void inputVisitDate() {
        boolean isValidate;
        do {
            try {
                String userInputVisitDate = inputView.inputVisitDate();
                isValidate = true;
            } catch (IllegalArgumentException e) {
                outputView.outputErrorMessage(e);
                isValidate = false;
            }
        } while (!isValidate);
    }

    private void inputOrder() {
        boolean isValidate;
        do {
            try {
                String userInputOrder = inputView.inputOrder();
                isValidate = true;
            } catch (IllegalArgumentException e) {
                outputView.outputErrorMessage(e);
                isValidate = false;
            }
        } while (!isValidate);
    }
}
