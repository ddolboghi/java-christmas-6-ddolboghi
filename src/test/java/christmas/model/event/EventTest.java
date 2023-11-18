package christmas.model.event;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EventTest {

    @Nested
    class ChristmasDDayDiscountEventTest {

        @ParameterizedTest
        @CsvSource({"1, true", "25, true", "26, false", "31, false"})
        void 방문날짜에따라_이벤트가_적용되는지_판단한다(int visitDate, boolean isAppliedResult) {
            Event christmasDDayDiscountEvent = new ChristmasDDayDiscountEvent(visitDate);

            assertThat(christmasDDayDiscountEvent.isApplied()).isEqualTo(isAppliedResult);
        }

        @ParameterizedTest
        @CsvSource({"1, 1000", "10, 1900", "25, 3400", "26, 0"})
        void 방문날짜에따라_할인금액을_계산한다(int visitDate, int discount) {
            Event christmasDDayDiscountEvent = new ChristmasDDayDiscountEvent(visitDate);

            assertThat(christmasDDayDiscountEvent.getDiscount()).isEqualTo(discount);
        }
    }

    @Nested
    class WeekdayDiscountEventTest {

        @ParameterizedTest
        @CsvSource({"3, true", "7, true", "10, true", "14, true", "17, true", "21, true", "24, true", "28, true",
                "31, true", "1, false", "2, false"})
        void 방문날짜에따라_이벤트가_적용되는지_판단한다(int visitDate, boolean isAppliedResult) {
            Event weekdayDiscountEvent = new WeekdayDiscountEvent(visitDate, 10);

            assertThat(weekdayDiscountEvent.isApplied()).isEqualTo(isAppliedResult);
        }

        @ParameterizedTest
        @CsvSource({"7, 1, 2023", "7, 0, 0", "8, 1, 0", "8, 0, 0"})
        void 방문날짜와_평일할인_대상_메뉴의_주문개수에_따라_할인금액을_계산한다(int visitDate, int menuAmount, int discount) {
            Event weekdayDiscountEvent = new WeekdayDiscountEvent(visitDate, menuAmount);

            assertThat(weekdayDiscountEvent.getDiscount()).isEqualTo(discount);
        }
    }

    @Nested
    class WeekendDiscountEventTest {

        @ParameterizedTest
        @CsvSource({"1, true", "30, true", "3, false", "10, false", "17, false", "24, false", "31, false",
                "7, false", "14, false", "21, false", "28, false"})
        void 방문날짜에따라_이벤트가_적용되는지_판단한다(int visitDate, boolean isAppliedResult) {
            Event weekendDiscountEvent = new WeekendDiscountEvent(visitDate, 10);

            assertThat(weekendDiscountEvent.isApplied()).isEqualTo(isAppliedResult);
        }

        @ParameterizedTest
        @CsvSource({"1, 1, 2023", "1, 0, 0", "3, 1, 0", "3, 0, 0"})
        void 방문날짜와_주말할인_대상_메뉴의_주문개수에_따라_할인금액을_계산한다(int visitDate, int menuAmount, int discount) {
            Event weekendDiscountEvent = new WeekendDiscountEvent(visitDate, menuAmount);

            assertThat(weekendDiscountEvent.getDiscount()).isEqualTo(discount);
        }
    }

    @Nested
    class SpecialDiscountEventTest {

        @ParameterizedTest
        @CsvSource({"3, true", "10, true", "17, true", "24, true", "31, true", "25, true",
                "2, false", "4, false", "11, false", "18, false", "26, false"})
        void 방문날짜에따라_이벤트가_적용되는지_판단한다(int visitDate, boolean isAppliedResult) {
            Event specialDiscountEvent = new SpecialDiscountEvent(visitDate);

            assertThat(specialDiscountEvent.isApplied()).isEqualTo(isAppliedResult);
        }

        @ParameterizedTest
        @CsvSource({"3, 1000", "4, 0"})
        void 방문날짜에_따라_할인금액을_계산한다(int visitDate, int discount) {
            Event specialDiscountEvent = new SpecialDiscountEvent(visitDate);

            assertThat(specialDiscountEvent.getDiscount()).isEqualTo(discount);
        }
    }

    @Nested
    class GiftEventTest {

        @ParameterizedTest
        @CsvSource({"120000, true", "119999, false"})
        void 총주문_금액에따라_이벤트가_적용되는지_판단한다(int totalCost, boolean isAppliedResult) {
            Event giftEvent = new GiftEvent(totalCost);

            assertThat(giftEvent.isApplied()).isEqualTo(isAppliedResult);
        }

        @ParameterizedTest
        @CsvSource({"120000, 25000", "119999, 0"})
        void 총주문_금액에따라_할인금액을_계산한다(int totalCost, int discount) {
            Event giftEvent = new GiftEvent(totalCost);

            assertThat(giftEvent.getDiscount()).isEqualTo(discount);
        }
    }
}