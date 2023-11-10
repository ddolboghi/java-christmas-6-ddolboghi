package christmas.controller;

import christmas.validator.VisitDateValidator;
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
        requestVisitDate();
    }

    private void requestVisitDate() {
        boolean isValidate;
        do {
            String userInputVisitDate = inputView.inputVisitDate();
            try {
                VisitDateValidator.validateBlank(userInputVisitDate);
                VisitDateValidator.validateNumeric(userInputVisitDate);
                VisitDateValidator.validateDateRange(userInputVisitDate);
                isValidate = true;
            } catch (IllegalArgumentException e) {
                outputView.outputErrorMessage(e);
                isValidate = false;
            }
        } while (!isValidate);
    }
}
