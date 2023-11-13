package christmas.model;

import static christmas.util.constant.DiscountEventConst.NON_DISCOUNT_COST;

import christmas.model.event.Event;
import java.util.List;

public class Benefit {
    private final List<Event> events;

    public Benefit(List<Event> events) {
        this.events = events;
    }

    public List<Event> getAppliedEvents() {
        return events.stream()
                .filter(event -> event.getDiscount() > NON_DISCOUNT_COST)
                .toList();
    }
}
