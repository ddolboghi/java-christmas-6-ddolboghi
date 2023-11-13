package christmas.model;

import static christmas.util.rule.GiftEventRule.GIFT_EVENT_MENU_AMOUNT;
import static christmas.util.rule.GiftEventRule.GIFT_EVENT_NAME;
import static christmas.util.rule.GiftEventRule.NON_GIFT_MENU;

import christmas.model.event.Event;
import christmas.util.Menu;
import java.util.List;

public class Benefit {
    private final List<Event> events;

    public Benefit(List<Event> events) {
        this.events = events;
    }

    public String getGiftMenu() {
        for (Event event : events) {
            return getGiftMenu(event);
        }
        return NON_GIFT_MENU;
    }

    private String getGiftMenu(Event event) {
        if (isAppliedGiftEvent(event)) {
            return String.format(GIFT_EVENT_MENU_AMOUNT, Menu.CHAMPAGNE.getKoreanName());
        }
        return NON_GIFT_MENU;
    }

    private boolean isAppliedGiftEvent(Event event) {
        return event.getEventName().equals(GIFT_EVENT_NAME) && event.isApplied();
    }

    public List<Event> getAppliedEvents() {
        return events.stream()
                .filter(Event::isApplied)
                .toList();
    }
}
