package christmas.model;

import static christmas.util.rule.DiscountEventRule.WEEKDAY_DISCOUNT_EVENT;
import static christmas.util.rule.DiscountEventRule.WEEKEND_DISCOUNT_EVENT;

import christmas.model.event.Event;
import christmas.model.event.PresentationEvent;
import christmas.model.event.SpecialDiscountEvent;
import christmas.model.event.WeekdayDiscountEvent;
import christmas.model.event.WeekendDiscountEvent;
import java.util.List;
import java.util.stream.Stream;

public class EventManager {
    private final int visitDate;
    private final OrderManager orderManager;
    private final Benefit benefit;

    public EventManager(OrderManager orderManager, int visitDate) {
        this.visitDate = visitDate;
        this.orderManager = orderManager;
        benefit = new Benefit(getAllEvents());
    }

    public String getPresentMenu() {
        return benefit.getPresentMenu();
    }

    private List<Event> getAllEvents() {
        return Stream.of(
                new WeekdayDiscountEvent(visitDate,
                        orderManager.getMenuAmount(WEEKDAY_DISCOUNT_EVENT.getAppliedTarget())),
                new WeekendDiscountEvent(visitDate,
                        orderManager.getMenuAmount(WEEKEND_DISCOUNT_EVENT.getAppliedTarget())),
                new SpecialDiscountEvent(visitDate),
                new PresentationEvent(orderManager.getTotalCost())
        ).toList();
    }
}
