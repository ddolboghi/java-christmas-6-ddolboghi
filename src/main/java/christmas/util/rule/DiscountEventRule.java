package christmas.util.rule;

public enum DiscountEventRule {
    WEEKDAY_DISCOUNT_EVENT("평일 할인", 2023, "dessert"),
    WEEKEND_DISCOUNT_EVENT("주말 할인", 2023, "main"),
    SPECIAL_DISCOUNT_EVENT("특별 할인", 1000, "total cost");

    private final String eventName;
    private final int discountCost;
    private final String appliedTarget;

    DiscountEventRule(String eventName, int discountCost, String appliedTarget) {
        this.eventName = eventName;
        this.discountCost = discountCost;
        this.appliedTarget = appliedTarget;
    }

    public String getEventName() {
        return eventName;
    }

    public int getDiscountCost() {
        return discountCost;
    }

    public String getAppliedTarget() {
        return appliedTarget;
    }
}
