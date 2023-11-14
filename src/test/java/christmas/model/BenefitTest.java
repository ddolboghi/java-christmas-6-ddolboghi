package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.model.event.ChristmasDDayDiscountEvent;
import christmas.model.event.Event;
import christmas.model.event.GiftEvent;
import christmas.model.event.SpecialDiscountEvent;
import christmas.model.event.WeekdayDiscountEvent;
import christmas.model.event.WeekendDiscountEvent;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BenefitTest {

    private Benefit createBenefit(List<Event> events) {
        return new Benefit(events);
    }

    private List<Event> createEvents(int totalCost, int visitDate, int weekdayMenuAmount, int weekendMenuAmount) {
        return List.of(
                new GiftEvent(totalCost),
                new SpecialDiscountEvent(visitDate),
                new WeekdayDiscountEvent(visitDate, weekdayMenuAmount),
                new WeekendDiscountEvent(visitDate, weekendMenuAmount),
                new ChristmasDDayDiscountEvent(visitDate)
        );
    }

    @Test
    void 받은_이벤트들중_할인금액이_있는_이벤트만_반환한다() {
        List<Event> events = createEvents(142000, 3, 2, 2);
        Benefit benefit = createBenefit(events);
        Set<String> appliedEventsName = Set.of(
                "크리스마스 디데이 할인",
                "평일 할인",
                "특별 할인",
                "증정 이벤트"
        );

        Map<String, Integer> appliedEvents = benefit.getAppliedEvents();

        assertThat(appliedEvents.keySet()).containsAll(appliedEventsName);
    }

    @Test
    void 받은_이벤트들_모두_할인금액이_없으면_빈_목록을_반환한다() {
        List<Event> events = createEvents(55000, 28, 0, 1);
        Benefit benefit = createBenefit(events);

        Map<String, Integer> appliedEvents = benefit.getAppliedEvents();

        assertTrue(appliedEvents.isEmpty());
    }

    @ParameterizedTest
    @CsvSource({"8000, 25, 0, 0, 4400", "55000, 28, 0, 1, 0"})
    void 총혜택_금액을_계산한다(
            int totalCost,
            int visitDate,
            int weekdayMenuAmount,
            int weekendMenuAmount,
            int totalDiscounts
    ) {
        List<Event> events = createEvents(totalCost, visitDate, weekdayMenuAmount, weekendMenuAmount);
        Benefit benefit = createBenefit(events);

        assertThat(benefit.getTotalDiscount()).isEqualTo(totalDiscounts);
    }

    @ParameterizedTest
    @CsvSource({"158000, 25, 0, 2, 4400", "55000, 28, 0, 1, 0"})
    void 증정이벤트를_제외한_총혜택_금액을_계산한다(
            int totalCost,
            int visitDate,
            int weekdayMenuAmount,
            int weekendMenuAmount,
            int totalDiscounts
    ) {
        List<Event> events = createEvents(totalCost, visitDate, weekdayMenuAmount, weekendMenuAmount);
        Benefit benefit = createBenefit(events);

        assertThat(benefit.sumDiscountsExceptGiftEvent()).isEqualTo(totalDiscounts);
    }
}