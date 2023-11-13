package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.event.Event;
import christmas.model.event.GiftEvent;
import christmas.model.event.SpecialDiscountEvent;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BenefitTest {

    private Benefit createBenefit(List<Event> events) {
        return new Benefit(events);
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
}