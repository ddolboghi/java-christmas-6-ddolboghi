package christmas.controller;

import christmas.model.Events;
import christmas.model.OrderManager;
import christmas.model.GiftEventLoader;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PlannerController {
    private int visitDate;
    private final InputView inputView;
    private final OutputView outputView;
    private OrderManager orderManager;
    private Events events;

    public PlannerController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void preview() {
        outputView.introPlanner();
        setManager();
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

    private OrderManager takeOrder() {
        do {
            try {
                return new OrderManager(inputView.inputOrder());
            } catch (IllegalArgumentException e) {
                outputView.outputErrorMessage(e);
            }
        } while (true);
    }

    private void setManager() {
        visitDate = Integer.parseInt(takeVisitDate());
        orderManager = takeOrder();
        events = new Events(orderManager, visitDate);
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
        outputView.showOrderHistory(orderManager.getOrder());
    }

    private void showTotalCost() {
        outputView.showTotalOrderCost(orderManager.getTotalCost());
    }

    private void showGiftMenu() {
        outputView.showGiftMenu(GiftEventLoader.getGiftMenu(orderManager.getTotalCost()));
    }

    private void showBenefits() {
        outputView.showBenefits(events.getBenefits());
    }

    private void showTotalDiscount() {
        outputView.showTotalDiscount(events.getTotalDiscount());
    }

    private void showTotalCostAfterDiscount() {
        outputView.showTotalCostAfterDiscount(events.calculateTotalCostAfterDiscount());
    }

    private void showGrantedBadge() {
        outputView.showGrantedBadge(events.grantBadge());
    }
}
