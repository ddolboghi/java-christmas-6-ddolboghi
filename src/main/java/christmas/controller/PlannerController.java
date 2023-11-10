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
        inputOrderMenu();
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

    private void inputOrderMenu() {
        boolean isValidate;
        do {
            try {
                String userInputOrderMenu = inputView.inputOrderMenu();
                isValidate = true;
            } catch (IllegalArgumentException e) {
                outputView.outputErrorMessage(e);
                isValidate = false;
            }
        } while (!isValidate);
    }
}
