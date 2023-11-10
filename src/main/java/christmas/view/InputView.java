package christmas.view;

import christmas.io.Input;
import christmas.io.Output;
import christmas.validator.InputValidator;

public class InputView {
    private static final String ASK_VISIT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ASK_ORDER_MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public String inputVisitDate() {
        Output.writeLine(ASK_VISIT_DATE);
        String userInputVisitDate = Input.readLine();
        InputValidator.validateVisitDate(userInputVisitDate);
        return userInputVisitDate;
    }

    public String inputOrderMenu() {
        Output.writeLine(ASK_ORDER_MENU);
        String userInputOrderMenu = Input.readLine();
        InputValidator.validateOrderMenu(userInputOrderMenu);
        return userInputOrderMenu;
    }
}
