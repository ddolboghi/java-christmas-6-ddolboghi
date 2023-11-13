package christmas.model.event;

import static christmas.util.constant.DiscountEventConst.NON_DISCOUNT_COST;
import static christmas.util.rule.PresentationEventRule.PRESENTATION_EVENT_NAME;
import static christmas.util.rule.PresentationEventRule.TOTAL_COST_CRITERIA;

import christmas.util.Menu;

public class GiftEvent implements Event {
    private final int totalCost;

    public GiftEvent(int totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String getEventName() {
        return PRESENTATION_EVENT_NAME;
    }

    @Override
    public int getDiscount() {
        if (isApplied()) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return NON_DISCOUNT_COST;
    }

    @Override
    public boolean isApplied() {
        return totalCost >= TOTAL_COST_CRITERIA;
    }
}
