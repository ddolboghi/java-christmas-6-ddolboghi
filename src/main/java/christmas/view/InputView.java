package christmas.view;

import static christmas.view.OutputMessage.ASK_VISIT_DATE;
import static christmas.view.OutputMessage.TAKE_ORDER;

import christmas.io.Input;
import christmas.io.Output;
import christmas.validator.InputValidator;

public class InputView {

    public String inputVisitDate() {
        Output.writeLine(ASK_VISIT_DATE);
        String userInputVisitDate = Input.readLine();
        InputValidator.validateVisitDate(userInputVisitDate);
        return userInputVisitDate;
    }

    public String inputOrder() {
        Output.writeLine(TAKE_ORDER);
        String userInputOrder = Input.readLine();
        InputValidator.validateOrder(userInputOrder);
        return userInputOrder;
    }
}
