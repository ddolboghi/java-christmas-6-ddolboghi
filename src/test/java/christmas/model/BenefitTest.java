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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BenefitTest {
    private final Event giftEvent;
    private final Event specialDiscountEvent;
    private final Event weekdayDisCountEvent;
    private final Event weekendDiscountEvent;
    private final Event christmasDDayDiscountEvent;

    public BenefitTest() {
        this.giftEvent = new GiftEvent(10000);//이벤트 적용 안됨
        this.specialDiscountEvent = new SpecialDiscountEvent(25);
        this.weekdayDisCountEvent = new WeekdayDiscountEvent(25, 1);
        this.weekendDiscountEvent = new WeekendDiscountEvent(25, 1);//이벤트 적용 안됨
        this.christmasDDayDiscountEvent = new ChristmasDDayDiscountEvent(25);
    }

    private Benefit createBenefit(List<Event> events) {
        return new Benefit(events);
    }

    private List<Event> allEventsProvider() {
        return List.of(
                giftEvent,
                specialDiscountEvent,
                weekdayDisCountEvent,
                weekendDiscountEvent,
                christmasDDayDiscountEvent
        );
    }

    private List<Event> appliedEventsProvider() {
        return List.of(
                specialDiscountEvent,
                weekdayDisCountEvent,
                christmasDDayDiscountEvent
        );
    }

    @Test
    void 적용되는_이벤트가_하나도_없으면_증정메뉴는_없다() {
        List<Event> events = new ArrayList<>();
        Benefit benefit = createBenefit(events);

        assertThat(benefit.getGiftMenu()).isEqualTo("없음");
    }

    @ParameterizedTest
    @CsvSource({"10000, 없음", "120000, 샴페인 1개"})
    void 증정이벤트_적용여부에따라_증정메뉴를_반환한다(int totalCost, String giftMenu) {
        List<Event> events = List.of(new GiftEvent(totalCost), new SpecialDiscountEvent(25));
        Benefit benefit = createBenefit(events);

        assertThat(benefit.getGiftMenu()).isEqualTo(giftMenu);
    }

    @Test
    void 이벤트들중_적용되는_이벤트만_가져온다() {
        List<Event> events = allEventsProvider();
        List<Event> appliedEvents = appliedEventsProvider();
        Benefit benefit = createBenefit(events);

        assertThat(benefit.getAppliedEvents()).isEqualTo(appliedEvents);
    }
}