package christmas.model;

import static christmas.util.constant.DiscountConstant.NON_DISCOUNT_COST;
import static christmas.util.rule.GiftEventRule.GIFT_EVENT;

import christmas.model.event.Event;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Benefit {
    private final List<Event> events;

    public Benefit(List<Event> events) {
        this.events = events;
    }

    public Map<String, Integer> getAppliedEvents() {
        return events.stream()
                .filter(event -> event.getDiscount() > NON_DISCOUNT_COST)
                .collect(Collectors.toMap(Event::getEventName, Event::getDiscount, (a, b) -> b));
    }

    public int getTotalDiscount() {
        return events.stream()
                .mapToInt(Event::getDiscount)
                .sum();
    }

    public int sumDiscountsExceptGiftEvent() {
        return events.stream()
                .filter(event -> !event.getEventName().equals(GIFT_EVENT.getEventName()))
                .mapToInt(Event::getDiscount)
                .sum();
    }
}
