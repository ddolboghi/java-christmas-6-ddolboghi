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

class EventManagerTest {
    @Mock
    private OrderManager orderManager;
    private EventManager eventManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @CsvSource({"120000, 샴페인 1개", "10000, 없음"})
    void 증정이벤트_적용여부에따라_증정메뉴를_반환한다(int totalCost, String giftMenu) {
        when(orderManager.getTotalCost()).thenReturn(totalCost);
        eventManager = new EventManager(orderManager, 31);

        assertThat(eventManager.getGiftMenu()).isEqualTo(giftMenu);
    }

    @Test
    void 적용되는_이벤트들을_반환한다() {
        //given
        when(orderManager.getTotalCost()).thenReturn(60000);
        when(orderManager.getMenuAmount("dessert")).thenReturn(1);
        when(orderManager.getMenuAmount("main")).thenReturn(1);
        eventManager = new EventManager(orderManager, 31);

        Map<String, Integer> appliedEvents = new HashMap<>();
        appliedEvents.put("평일 할인", 2023);
        appliedEvents.put("특별 할인", 1000);

        //when
        Map<String, Integer> benefits = eventManager.getBenefits();

        //then
        assertThat(benefits).isEqualTo(appliedEvents);
    }

    @Test
    void 적용되는_이벤트가_하나도_없으면_빈_혜택내역을_반환한다() {
        //given
        when(orderManager.getTotalCost()).thenReturn(18000);
        when(orderManager.getMenuAmount("dessert")).thenReturn(1);
        when(orderManager.getMenuAmount("main")).thenReturn(0);
        eventManager = new EventManager(orderManager, 30);

        //when
        Map<String, Integer> benefits = eventManager.getBenefits();

        //then
        assertTrue(benefits.isEmpty());
    }
}