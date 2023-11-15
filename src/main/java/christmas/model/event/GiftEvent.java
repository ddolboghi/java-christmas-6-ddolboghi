package christmas.model.event;

import static christmas.util.constant.DiscountConstant.NON_DISCOUNT_COST;
import static christmas.util.rule.GiftEventRule.GIFT_EVENT;

public class GiftEvent implements Event {
    public static final int TOTAL_COST_CRITERIA = 120000;
    private final int totalCost;

    public GiftEvent(int totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String getEventName() {
        return GIFT_EVENT.getEventName();
    }

    @Override
    public int getDiscount() {
        if (isApplied()) {
            return GIFT_EVENT.getDiscountCost();
        }
        return NON_DISCOUNT_COST;
    }

    @Override
    public boolean isApplied() {
        return totalCost >= TOTAL_COST_CRITERIA;
    }
}
