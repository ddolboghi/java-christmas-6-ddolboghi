package christmas.view;

import static christmas.view.OutputMessage.INTRODUCTION_OF_PLANNER;
import static christmas.view.OutputMessage.MENU_AND_AMOUNT;
import static christmas.view.OutputMessage.MONETARY_UNIT;
import static christmas.view.OutputMessage.TITLE_OF_GIFT_MENU;
import static christmas.view.OutputMessage.TITLE_OF_ORDER_MENU;
import static christmas.view.OutputMessage.TITLE_OF_PLANNER;
import static christmas.view.OutputMessage.TITLE_OF_TOTAL_COST_BEFORE_DISCOUNT;

import christmas.io.Output;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    private final DecimalFormat thousandUnitFormat = new DecimalFormat("#,###");

    public void outputErrorMessage(IllegalArgumentException e) {
        Output.writeLine(e.getMessage());
    }

    public void introPlanner() {
        Output.writeLine(INTRODUCTION_OF_PLANNER);
    }

    public void showPlannerTitle(int visitDate) {
        Output.writeLine(String.format(TITLE_OF_PLANNER, visitDate));
    }

    public void showOrderHistory(Map<String, Integer> orderHistory) {
        Output.writeLine(TITLE_OF_ORDER_MENU);
        StringBuilder orderHistoryBuilder = new StringBuilder(orderHistory.size());
        for (String menuName : orderHistory.keySet()) {
            orderHistoryBuilder.append(String.format(MENU_AND_AMOUNT, menuName, orderHistory.get(menuName)));
        }
        Output.writeLine(orderHistoryBuilder.toString().trim());
    }

    public void showTotalOrderCost(int totalCost) {
        Output.writeLine(TITLE_OF_TOTAL_COST_BEFORE_DISCOUNT);
        Output.writeLine(String.format(MONETARY_UNIT, thousandUnitFormat.format(totalCost)));
    }

    public void showGiftMenu(String presentationMenu) {
        Output.writeLine(TITLE_OF_GIFT_MENU);
        Output.writeLine(presentationMenu);
    }
}
