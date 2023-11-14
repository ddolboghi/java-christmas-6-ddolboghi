package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GiftMenuLoaderTest {

    @ParameterizedTest
    @CsvSource({"120000, 샴페인 1개", "119999, 없음"})
    void 증정이벤트_적용여부에따라_증정메뉴를_반환한다(int totalCost, String giftMenu) {
        assertThat(GiftMenuLoader.getGiftMenu(totalCost)).isEqualTo(giftMenu);
    }
}