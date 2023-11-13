package christmas.model;

import static christmas.util.rule.PresentationEventRule.NON_PRESENT_MENU;
import static christmas.util.rule.PresentationEventRule.PRESENTATION_EVENT_MENU_AMOUNT;
import static christmas.util.rule.PresentationEventRule.PRESENTATION_EVENT_NAME;

import christmas.model.event.Event;
import christmas.util.Menu;
import java.util.List;

public class Benefit {
    private final List<Event> events;

    public Benefit(List<Event> events) {
        this.events = events;
    }

    public String getGiftMenu() {
        String giftMenu = null;
        for (Event event : events) {
            giftMenu = getGiftMenu(event);
        }
        return giftMenu;
    }

    private String getGiftMenu(Event event) {
        if (isAppliedGiftEvent(event)) {
            return String.format(PRESENTATION_EVENT_MENU_AMOUNT, Menu.CHAMPAGNE.getKoreanName());
        }
        return NON_PRESENT_MENU;
    }

    private boolean isAppliedGiftEvent(Event event) {
        return event.getEventName().equals(PRESENTATION_EVENT_NAME) && event.isApplied();
    }
}
