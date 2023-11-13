package christmas.model;

import static christmas.util.constant.DiscountEventConst.NON_DISCOUNT_COST;
import static christmas.util.rule.GiftEventRule.GIFT_EVENT_NAME;

import christmas.model.event.Event;
import java.util.List;
import java.util.Objects;

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

    public int sumDiscounts() {
        return events.stream()
                .mapToInt(Event::getDiscount)
                .sum();
    }

    public int sumDiscountsExceptGiftEvent() {
        return events.stream()
                .filter(event -> !Objects.equals(event.getEventName(), GIFT_EVENT_NAME))
                .mapToInt(Event::getDiscount)
                .sum();
    }
}
