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
    void 이벤트들중_적용되는_이벤트만_가져온다() {
        List<Event> events = createEvents(10000, 25, 1, 1);
        List<Event> appliedEvents = List.of(
                new SpecialDiscountEvent(25),
                new WeekdayDiscountEvent(25, 1),
                new ChristmasDDayDiscountEvent(25)
        );
        Benefit benefit = createBenefit(events);

        assertThat(benefit.getAppliedEvents()).isEqualTo(appliedEvents);
    }

    @Test
    void 적용되는_이벤트가_하나도_없으면_빈_배열을_반환한다() {
        List<Event> events = createEvents(55000, 28, 0, 1);
        Benefit benefit = createBenefit(events);
        List<Event> emptyAppliedEvents = new ArrayList<>();

        List<Event> appliedEvents = benefit.getAppliedEvents();

        assertThat(appliedEvents).isEqualTo(emptyAppliedEvents);
    }

    @ParameterizedTest
    @CsvSource({"8000, 25, 0, 0, 4400", "55000, 28, 0, 1, 0"})
    void 총혜택_금액을_계산한다(int totalCost, int visitDate, int weekdayMenuAmount, int weekendMenuAmount, int totalDiscounts) {
        List<Event> events = createEvents(totalCost, visitDate, weekdayMenuAmount, weekendMenuAmount);
        Benefit benefit = createBenefit(events);

        assertThat(benefit.sumDiscounts()).isEqualTo(totalDiscounts);
    }
}