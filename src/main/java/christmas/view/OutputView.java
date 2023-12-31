package christmas.view;

import static christmas.util.constant.DiscountConstant.NON_DISCOUNT_COST;
import static christmas.util.rule.GiftEventRule.GIFT_EVENT;
import static christmas.view.OutputMessage.BENEFIT_AND_DISCOUNT;
import static christmas.view.OutputMessage.INTRODUCTION_OF_PLANNER;
import static christmas.view.OutputMessage.MENU_AND_AMOUNT;
import static christmas.view.OutputMessage.MONETARY_UNIT;
import static christmas.view.OutputMessage.NOT_APPLIED_EVENT;
import static christmas.view.OutputMessage.TITLE_OF_BENEFITS;
import static christmas.view.OutputMessage.TITLE_OF_EVENT_BADGE;
import static christmas.view.OutputMessage.TITLE_OF_GIFT_MENU;
import static christmas.view.OutputMessage.TITLE_OF_ORDER_MENU;
import static christmas.view.OutputMessage.TITLE_OF_PLANNER;
import static christmas.view.OutputMessage.TITLE_OF_TOTAL_COST_AFTER_DISCOUNT;
import static christmas.view.OutputMessage.TITLE_OF_TOTAL_COST_BEFORE_DISCOUNT;
import static christmas.view.OutputMessage.TITLE_OF_TOTAL_DISCOUNT;

import christmas.io.Output;
import christmas.model.event.Event;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    private final DecimalFormat thousandUnitFormat = new DecimalFormat("#,###");
    private final DecimalFormat minusThousandUnitFormat = new DecimalFormat("-#,###");

    public void printErrorMessage(IllegalArgumentException e) {
        Output.writeLine(e.getMessage());
    }

    public void printIntro() {
        Output.writeLine(INTRODUCTION_OF_PLANNER);
    }

    public void printPlannerTitle(int visitDate) {
        Output.writeLine(String.format(TITLE_OF_PLANNER, visitDate));
    }

    public void printOrderHistory(Map<String, Integer> orderHistory) {
        Output.writeLine(TITLE_OF_ORDER_MENU);
        StringBuilder orderHistoryBuilder = new StringBuilder(orderHistory.size());
        for (String menuName : orderHistory.keySet()) {
            orderHistoryBuilder.append(String.format(MENU_AND_AMOUNT, menuName, orderHistory.get(menuName)));
        }
        Output.writeLine(orderHistoryBuilder.toString().trim());
    }

    public void printTotalOrderCost(int totalCost) {
        Output.writeLine(TITLE_OF_TOTAL_COST_BEFORE_DISCOUNT);
        Output.writeLine(String.format(MONETARY_UNIT, thousandUnitFormat.format(totalCost)));
    }

    public void printGiftMenu(Event giftEvent) {
        Output.writeLine(TITLE_OF_GIFT_MENU);
        String giftMenu = NOT_APPLIED_EVENT;
        if (giftEvent.isApplied()) {
            giftMenu = String.format(MENU_AND_AMOUNT, GIFT_EVENT.getGiftMenu(), GIFT_EVENT.getGiftAmount());
        }
        Output.writeLine(giftMenu);
    }

    public void printBenefits(Map<String, Integer> appliedEvents) {
        Output.writeLine(TITLE_OF_BENEFITS);
        String benefits = NOT_APPLIED_EVENT;
        if (!appliedEvents.isEmpty()) {
            benefits = createBenefits(appliedEvents);
        }
        Output.writeLine(benefits);
    }

    private String createBenefits(Map<String, Integer> appliedEvents) {
        StringBuilder benefitBuilder = new StringBuilder(appliedEvents.size());
        for (String event : appliedEvents.keySet()) {
            benefitBuilder.append(
                    String.format(BENEFIT_AND_DISCOUNT, event,
                            minusThousandUnitFormat.format(appliedEvents.get(event))));
        }
        return benefitBuilder.toString().trim();
    }

    public void printTotalDiscount(int totalDiscount) {
        Output.writeLine(TITLE_OF_TOTAL_DISCOUNT);
        Output.writeLine(getPrintTotalDiscount(totalDiscount));
    }

    private String getPrintTotalDiscount(int totalDiscount) {
        if(totalDiscount == NON_DISCOUNT_COST) {
            return String.format(MONETARY_UNIT, totalDiscount);
        }
        return String.format(MONETARY_UNIT, minusThousandUnitFormat.format(totalDiscount));
    }

    public void printTotalCostAfterDiscount(int totalCostAfterDiscount) {
        Output.writeLine(TITLE_OF_TOTAL_COST_AFTER_DISCOUNT);
        Output.writeLine(String.format(MONETARY_UNIT, thousandUnitFormat.format(totalCostAfterDiscount)));
    }

    public void printGrantedBadge(String grantedBadge) {
        Output.writeLine(TITLE_OF_EVENT_BADGE);
        Output.writeLine(grantedBadge);
    }
}
