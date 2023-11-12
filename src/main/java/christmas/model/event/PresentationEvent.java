package christmas.model.event;

import static christmas.util.events.PresentationEventRule.DISCOUNT_OF_NOT_APPLIED_PRESENTATION_EVENT;
import static christmas.util.events.PresentationEventRule.NON_PRESENTATION_EVENT_MENU;
import static christmas.util.events.PresentationEventRule.PRESENTATION_EVENT_MENU_AMOUNT;
import static christmas.util.events.PresentationEventRule.PRESENTATION_EVENT_NAME;
import static christmas.util.events.PresentationEventRule.TOTAL_COST_CRITERIA;

import christmas.util.Menu;

public class PresentationEvent implements Event {
    private final int totalCost;

    public PresentationEvent(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getEventName() {
        return PRESENTATION_EVENT_NAME;
    }

    public int discount() {
        if (isApplied()) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return DISCOUNT_OF_NOT_APPLIED_PRESENTATION_EVENT;
    }

    public boolean isApplied() {
        return totalCost >= TOTAL_COST_CRITERIA;
    }

    public String presentMenu() {
        if (isApplied()) {
            return String.format(PRESENTATION_EVENT_MENU_AMOUNT, Menu.CHAMPAGNE.getKoreanName());
        }
        return NON_PRESENTATION_EVENT_MENU;
    }
}
