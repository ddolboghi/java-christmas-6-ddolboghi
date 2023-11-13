package christmas.controller;

import christmas.model.EventManager;
import christmas.model.OrderManager;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PlannerController {
    private int visitDate;
    private final InputView inputView;
    private final OutputView outputView;
    private OrderManager orderManager;
    private EventManager eventManager;

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
        eventManager = new EventManager(orderManager, visitDate);
    }

    private void showPlanner() {
        showPlannerTitle();
        showOrder();
        showTotalCost();
        showGiftMenu();
        showBenefits();
        showTotalDiscount();
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
        outputView.showGiftMenu(eventManager.getGiftMenu());
    }

    private void showBenefits() {
        outputView.showBenefits(eventManager.getBenefits());
    }

    private void showTotalDiscount() {
        outputView.showTotalDiscount(eventManager.getTotalDiscount());
    }
}
