package christmas.util.rule;

public enum GiftEventRule {
    GIFT_EVENT("증정 이벤트", 25000, "샴페인", 1);

    private final String eventName;
    private final int discountCost;
    private final String giftMenu;
    private final int giftAmount;

    GiftEventRule(String eventName, int discountCost, String giftMenu, int giftAmount) {
        this.eventName = eventName;
        this.discountCost = discountCost;
        this.giftMenu = giftMenu;
        this.giftAmount = giftAmount;
    }

    public String getEventName() {
        return eventName;
    }

    public int getDiscountCost() {
        return discountCost;
    }

    public String getGiftMenu() {
        return giftMenu;
    }

    public int getGiftAmount() {
        return giftAmount;
    }
}
