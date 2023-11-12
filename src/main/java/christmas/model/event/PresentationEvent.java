package christmas.model.event;

import static christmas.util.rule.PresentationEventRule.PRESENTATION_EVENT_NAME;
import static christmas.util.rule.PresentationEventRule.TOTAL_COST_CRITERIA;

import christmas.util.Menu;

public class PresentationEvent implements Event {
    private final int totalCost;

    public PresentationEvent(int totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String getEventName() {
        return PRESENTATION_EVENT_NAME;
    }

    @Override
    public int getDiscount() {
        return Menu.CHAMPAGNE.getPrice();
    }

    @Override
    public boolean isApplied() {
        return totalCost >= TOTAL_COST_CRITERIA;
    }
}
