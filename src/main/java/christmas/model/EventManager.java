package christmas.model;

import static christmas.util.rule.DiscountEventRule.WEEKDAY_DISCOUNT_EVENT;
import static christmas.util.rule.DiscountEventRule.WEEKEND_DISCOUNT_EVENT;
import static christmas.util.rule.GiftEventRule.GIFT_EVENT_MENU_AMOUNT;
import static christmas.util.rule.GiftEventRule.NON_GIFT_MENU;

import christmas.model.event.ChristmasDDayDiscountEvent;
import christmas.model.event.Event;
import christmas.model.event.GiftEvent;
import christmas.model.event.SpecialDiscountEvent;
import christmas.model.event.WeekdayDiscountEvent;
import christmas.model.event.WeekendDiscountEvent;
import christmas.util.Menu;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventManager {
    private final int visitDate;
    private final OrderManager orderManager;
    private final Event giftEvent;
    private final Benefit benefit;

    public EventManager(OrderManager orderManager, int visitDate) {
        this.visitDate = visitDate;
        this.orderManager = orderManager;
        this.giftEvent = new GiftEvent(orderManager.getTotalCost());
        this.benefit = new Benefit(getAllEvents());
    }

    public String getGiftMenu() {
        if (giftEvent.isApplied()) {
            return String.format(GIFT_EVENT_MENU_AMOUNT, Menu.CHAMPAGNE.getKoreanName());
        }
        return NON_GIFT_MENU;
    }

    public Map<String, Integer> getBenefits() {
        if (benefit.getAppliedEvents().isEmpty()) {
            return Collections.emptyMap();
        }
        return benefit.getAppliedEvents().stream()
                .collect(Collectors.toMap(Event::getEventName, Event::getDiscount, (a, b) -> b));
    }

    private List<Event> getAllEvents() {
        return Stream.of(
                new ChristmasDDayDiscountEvent(visitDate),
                new WeekdayDiscountEvent(visitDate,
                        orderManager.getMenuAmount(WEEKDAY_DISCOUNT_EVENT.getAppliedTarget())),
                new WeekendDiscountEvent(visitDate,
                        orderManager.getMenuAmount(WEEKEND_DISCOUNT_EVENT.getAppliedTarget())),
                new SpecialDiscountEvent(visitDate),
                giftEvent
        ).toList();
    }
}
