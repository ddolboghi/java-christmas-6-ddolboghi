package christmas.util.rule;

public enum ChristmasDDayDiscountEventRule {
    CHRISTMAS_EVENT("크리스마스 디데이 할인", 1, 25, 1000, 100);

    private final String eventName;
    private final int startDate;
    private final int endDate;
    private final int defaultDiscountCost;
    private final int unitDiscountCost;

    ChristmasDDayDiscountEventRule(String eventName, int startDate, int endDate, int defaultDiscountCost, int unitDiscountCost) {
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.defaultDiscountCost = defaultDiscountCost;
        this.unitDiscountCost = unitDiscountCost;
    }

    public String getEventName() {
        return eventName;
    }

    public int getStartDate() {
        return startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public int getDefaultDiscountCost() {
        return defaultDiscountCost;
    }

    public int getUnitDiscountCost() {
        return unitDiscountCost;
    }
}
