package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class EventsTest {
    @Mock
    private OrderManager orderManager;
    private Events events;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 적용되는_이벤트들을_반환한다() {
        //given
        when(orderManager.getTotalCost()).thenReturn(60000);
        when(orderManager.getMenuAmount("dessert")).thenReturn(1);
        when(orderManager.getMenuAmount("main")).thenReturn(1);
        events = new Events(orderManager, 31);

        Map<String, Integer> appliedEvents = new HashMap<>();
        appliedEvents.put("평일 할인", 2023);
        appliedEvents.put("특별 할인", 1000);

        //when then
        assertThat(events.getBenefits()).isEqualTo(appliedEvents);
    }

    @Test
    void 적용되는_이벤트가_하나도_없으면_빈_혜택내역을_반환한다() {
        //given
        when(orderManager.getTotalCost()).thenReturn(18000);
        when(orderManager.getMenuAmount("dessert")).thenReturn(1);
        when(orderManager.getMenuAmount("main")).thenReturn(0);
        events = new Events(orderManager, 30);

        //when then
        assertTrue(events.getBenefits().isEmpty());
    }

    @Test
    void 총혜택_금액을_계산한다() {
        when(orderManager.getTotalCost()).thenReturn(158000);
        when(orderManager.getMenuAmount("dessert")).thenReturn(0);
        when(orderManager.getMenuAmount("main")).thenReturn(2);
        events = new Events(orderManager, 25);

        assertThat(events.getTotalDiscount()).isEqualTo(29400);
    }

    @Test
    void 할인_후_예상_결제_금액을_계산한다() {
        when(orderManager.getTotalCost()).thenReturn(158000);
        when(orderManager.getMenuAmount("dessert")).thenReturn(0);
        when(orderManager.getMenuAmount("main")).thenReturn(2);
        events = new Events(orderManager, 25);

        assertThat(events.calculateTotalCostAfterDiscount()).isEqualTo(153600);
    }

    @ParameterizedTest
    @CsvSource({"158000, 0, 2, 25, 산타", "50000, 5, 1, 31, 트리", "45000, 3, 0, 26, 별", "55000, 0, 1, 28, 없음"})
    void 총혜택_금액에따라_배지를_부여한다(int totalCost, int dessertAmount, int mainAmount, int visitDate, String badge) {
        when(orderManager.getTotalCost()).thenReturn(totalCost);
        when(orderManager.getMenuAmount("dessert")).thenReturn(dessertAmount);
        when(orderManager.getMenuAmount("main")).thenReturn(mainAmount);
        events = new Events(orderManager, visitDate);

        assertThat(events.grantBadge()).isEqualTo(badge);
    }
}