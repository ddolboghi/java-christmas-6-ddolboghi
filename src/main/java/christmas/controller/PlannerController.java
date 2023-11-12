package christmas.controller;

import christmas.model.OrderManager;
import christmas.model.PlannerManager;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PlannerController {
    private final InputView inputView;
    private final OutputView outputView;
    private PlannerManager plannerManager;
    private OrderManager orderManager;

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
        String visitDate = takeVisitDate();
        orderManager = takeOrder();
        plannerManager = new PlannerManager(visitDate, orderManager.getTotalCost());
    }

    private void showPlanner() {
        showPlannerTitle();
        showOrder();
        showTotalCost();
        showPresentationMenu();
    }

    private void showPlannerTitle() {
        outputView.showPlannerTitle(plannerManager.getVisitDate());
    }

    private void showOrder() {
        outputView.showOrderHistory(orderManager.getOrder());
    }

    private void showTotalCost() {
        outputView.showTotalOrderCost(orderManager.getTotalCost());
    }

    private void showPresentationMenu() {
        outputView.showPresentationMenu(plannerManager.getPresentationMenu());
    }
}
