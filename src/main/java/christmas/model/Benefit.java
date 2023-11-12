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

    public String getPresentMenu() {
        for (Event event : events) {
            if (event.getEventName().equals(PRESENTATION_EVENT_NAME) && event.isApplied()) {
                return String.format(PRESENTATION_EVENT_MENU_AMOUNT, Menu.CHAMPAGNE.getKoreanName());
            }
        }
        return NON_PRESENT_MENU;
    }
}
