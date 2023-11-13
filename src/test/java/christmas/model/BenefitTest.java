package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.event.ChristmasDDayDiscountEvent;
import christmas.model.event.Event;
import christmas.model.event.GiftEvent;
import christmas.model.event.SpecialDiscountEvent;
import christmas.model.event.WeekdayDiscountEvent;
import christmas.model.event.WeekendDiscountEvent;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class BenefitTest {

    private Benefit createBenefit(List<Event> events) {
        return new Benefit(events);
    }

    @Test
    void 이벤트들중_적용되는_이벤트만_가져온다() {
        Event giftEvent = new GiftEvent(10000);//이벤트 적용 안됨
        Event specialDiscountEvent = new SpecialDiscountEvent(25);
        Event weekdayDisCountEvent = new WeekdayDiscountEvent(25, 1);
        Event weekendDiscountEvent = new WeekendDiscountEvent(25, 1);//이벤트 적용 안됨
        Event christmasDDayDiscountEvent = new ChristmasDDayDiscountEvent(25);
        List<Event> events = List.of(
                giftEvent,
                specialDiscountEvent,
                weekdayDisCountEvent,
                weekendDiscountEvent,
                christmasDDayDiscountEvent
        );
        List<Event> appliedEvents = List.of(
                specialDiscountEvent,
                weekdayDisCountEvent,
                christmasDDayDiscountEvent
        );
        Benefit benefit = createBenefit(events);

        assertThat(benefit.getAppliedEvents()).isEqualTo(appliedEvents);
    }

    @Test
    void 적용되는_이벤트가_하나도_없으면_빈_배열을_반환한다() {
        Event giftEvent = new GiftEvent(10000);
        Event specialDiscountEvent = new SpecialDiscountEvent(1);
        Event weekdayDisCountEvent = new WeekdayDiscountEvent(1, 1);
        Event weekendDiscountEvent = new WeekendDiscountEvent(25, 1);
        Event christmasDDayDiscountEvent = new ChristmasDDayDiscountEvent(26);
        List<Event> events = List.of(
                giftEvent,
                specialDiscountEvent,
                weekdayDisCountEvent,
                weekendDiscountEvent,
                christmasDDayDiscountEvent
        );
        Benefit benefit = createBenefit(events);
        List<Event> emptyAppliedEvents = new ArrayList<>();

        List<Event> appliedEvents = benefit.getAppliedEvents();

        assertThat(appliedEvents).isEqualTo(emptyAppliedEvents);
    }
}