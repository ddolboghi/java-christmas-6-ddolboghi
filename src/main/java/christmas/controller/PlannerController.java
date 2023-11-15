package christmas.controller;

import christmas.model.Badge;
import christmas.model.Benefit;
import christmas.model.Events;
import christmas.model.Order;
import christmas.model.event.GiftEvent;
import christmas.util.rule.DiscountEventRules;
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

    private void setUp() {
        visitDate = Integer.parseInt(visitDateRequest());
        order = new Order(orderRequest());
        benefit = new Benefit(Events.list(
                order.getTotalCost(),
                visitDate,
                order.getMenuAmount(DiscountEventRules.WEEKDAY_DISCOUNT_EVENT.getAppliedTarget()),
                order.getMenuAmount(DiscountEventRules.WEEKEND_DISCOUNT_EVENT.getAppliedTarget())
        ));
    }

    private String visitDateRequest() {
        RequestController visitDateRequestController = new VisitDateRequestController(inputView, outputView);
        return visitDateRequestController.inputRequest();
    }

    private String orderRequest() {
        RequestController orderRequestController = new OrderRequestController(inputView, outputView);
        return orderRequestController.inputRequest();
    }

    private void showPlanner() {
        int totalCost = order.getTotalCost();
        int totalDiscount = benefit.getTotalDiscount();
        outputView.showPlannerTitle(visitDate);
        outputView.showOrderHistory(order.getOrder());
        outputView.showTotalOrderCost(totalCost);
        outputView.showGiftMenu(new GiftEvent(totalCost));
        outputView.showBenefits(benefit.getAppliedEvents());
        outputView.showTotalDiscount(totalDiscount);
        outputView.showTotalCostAfterDiscount(order.getTotalCostAfterDiscount(benefit.sumDiscountsExceptGiftEvent()));
        outputView.showGrantedBadge(Badge.grantBadge(totalDiscount));
    }
}
