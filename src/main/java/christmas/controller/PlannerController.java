package christmas.controller;

import christmas.model.Badge;
import christmas.model.Benefit;
import christmas.model.EventsLoader;
import christmas.model.GiftMenuLoader;
import christmas.model.Order;
import christmas.util.rule.DiscountEventRule;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PlannerController {
    private int visitDate;
    private final InputView inputView;
    private final OutputView outputView;
    private Order order;
    private Benefit benefit;

    public PlannerController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void preview() {
        outputView.introPlanner();
        setUp();
        showPlanner();
    }

    private String takeVisitDate() {
        do {
            try {
                return inputView.inputVisitDate();
            } catch (IllegalArgumentException e) {
                outputView.outputErrorMessage(e);
            }
        } while (true);
    }

    private Order takeOrder() {
        do {
            try {
                return new Order(inputView.inputOrder());
            } catch (IllegalArgumentException e) {
                outputView.outputErrorMessage(e);
            }
        } while (true);
    }

    private void setUp() {
        visitDate = Integer.parseInt(takeVisitDate());
        order = takeOrder();
        benefit = new Benefit(EventsLoader.loadAllEvents(
                order.getTotalCost(),
                visitDate,
                order.getMenuAmount(DiscountEventRule.WEEKDAY_DISCOUNT_EVENT.getAppliedTarget()),
                order.getMenuAmount(DiscountEventRule.WEEKEND_DISCOUNT_EVENT.getAppliedTarget())));
    }

    private void showPlanner() {
        showPlannerTitle();
        showOrder();
        showTotalCost();
        showGiftMenu();
        showBenefits();
        showTotalDiscount();
        showTotalCostAfterDiscount();
        showGrantedBadge();
    }

    private void showPlannerTitle() {
        outputView.showPlannerTitle(visitDate);
    }

    private void showOrder() {
        outputView.showOrderHistory(order.getOrder());
    }

    private void showTotalCost() {
        outputView.showTotalOrderCost(order.getTotalCost());
    }

    private void showGiftMenu() {
        outputView.showGiftMenu(GiftMenuLoader.getGiftMenu(order.getTotalCost()));
    }

    private void showBenefits() {
        outputView.showBenefits(benefit.getAppliedEvents());
    }

    private void showTotalDiscount() {
        outputView.showTotalDiscount(benefit.getTotalDiscount());
    }

    private void showTotalCostAfterDiscount() {
        outputView.showTotalCostAfterDiscount(order.calculateCostAfterDiscount(benefit.sumDiscountsExceptGiftEvent()));
    }

    private void showGrantedBadge() {
        outputView.showGrantedBadge(Badge.grantBadge(benefit.getTotalDiscount()));
    }
}
