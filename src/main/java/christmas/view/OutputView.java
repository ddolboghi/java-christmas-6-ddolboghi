package christmas.view;

import christmas.io.Output;

public class OutputView {
    private static final String INTRODUCTION_OF_PLANNER = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String TITLE_OF_PLANNER = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String TITLE_OF_ORDER_MENU = "<주문 메뉴>";
    private static final String TITLE_OF_TOTAL_COST_BEFORE_DISCOUNT = "<할인 전 총주문 금액>";
    private static final String TITLE_OF_PRESENT_MENU = "<증정 메뉴>";
    private static final String TITLE_OF_BENEFITS = "<혜택 내역>";
    private static final String TITLE_OF_TOTAL_BENEFITS_SUM = "<총혜택 금액>";
    private static final String TITLE_OF_TOTAL_COST_AFTER_DISCOUNT = "<할인 후 예상 결제 금액>";
    private static final String TITLE_OF_EVENT_BADGE = "<12월 이벤트 배지>";

    public void outputErrorMessage(IllegalArgumentException e) {
        Output.writeLine(e.getMessage());
    }
}
