package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class OrderRequestController extends RequestController {

    public OrderRequestController(InputView inputView, OutputView outputView) {
        super(inputView, outputView);
    }

    @Override
    public String input() {
        return inputView.inputOrder();
    }
}
