package christmas.model;

import christmas.model.event.ChristmasDDayDiscountEvent;
import christmas.model.event.Event;
import christmas.model.event.GiftEvent;
import christmas.model.event.SpecialDiscountEvent;
import christmas.model.event.WeekdayDiscountEvent;
import christmas.model.event.WeekendDiscountEvent;
import java.util.List;
import java.util.stream.Stream;

public class Events {

    public static List<Event> list(int totalCost, int visitDate, int weekdayDiscountCategory,
                                     int weekendDiscountCategory) {
        return Stream.of(
                new GiftEvent(totalCost),
                new ChristmasDDayDiscountEvent(visitDate),
                new WeekdayDiscountEvent(visitDate, weekdayDiscountCategory),
                new WeekendDiscountEvent(visitDate, weekendDiscountCategory),
                new SpecialDiscountEvent(visitDate)
        ).toList();
    }
}
