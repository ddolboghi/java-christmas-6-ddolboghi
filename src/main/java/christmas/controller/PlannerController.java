package christmas.controller;

import christmas.model.Order;
import christmas.model.PlannerManager;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PlannerController {
    private final InputView inputView;
    private final OutputView outputView;
    private PlannerManager plannerManager;
    private Order order;

    public PlannerController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void preview() {
        outputView.introPlanner();
        saveVisitDate();
        saveOrder();
        showPlannerTitle();
        showOrder();
        showTotalCost();
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

    private void saveVisitDate() {
        plannerManager = new PlannerManager(takeVisitDate());
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

    private void saveOrder() {
        order = takeOrder();
    }

    private void showPlannerTitle() {
        outputView.showPlannerTitle(plannerManager.getVisitDate());
    }

    private void showOrder() {
        outputView.showOrderHistory(order.getOrder());
    }

    private void showTotalCost() {
        outputView.showTotalOrderCost(order.getTotalCost());
    }
}
