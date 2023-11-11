package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Menu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderTest {

    @AfterEach
    public void restoreSettings() {
        Console.close();
    }

    @ParameterizedTest
    @ValueSource(strings = {"제육-10", "돈까스-9", "국밥-7"})
    void 메뉴판에_없는_메뉴가_있으면_예외를_발생시킨다(String userInputOrder) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Order(userInputOrder));
    }

    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-1", "레드와인-1", "샴페인-1"})
    void 메뉴가_음료_뿐이면_예외를_발생시킨다(String userInputOrder) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Order(userInputOrder));
    }

    @Test
    void 중복된_메뉴가_있으면_예외를_발생시킨다() {
        String userInputOrder = "시저샐러드-1,시저샐러드-1";

        assertThatIllegalArgumentException().isThrownBy(() -> new Order(userInputOrder));
    }

    @ParameterizedTest
    @ValueSource(strings = {"시저샐러드-0", "시저샐러드-21", "시저샐러드-30000000000"})
    void 개별_메뉴의_개수가_1보다_작거나_20보다_크면_예외를_발생시킨다(String userInputOrder) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Order(userInputOrder));
    }

    @Test
    void 전체_메뉴의_개수가_20보다_크면_예외를_발생시킨다() {
        String userInputOrder = "해산물파스타-7,레드와인-7,초코케이크-7";

        assertThatIllegalArgumentException().isThrownBy(() -> new Order(userInputOrder));
    }

    @Nested
    class 정상적인_주문이면 {
        private final String userInputOrder = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        private final Order order = new Order(userInputOrder);

        @Test
        void 주문_내역을_만든다() {
            assertThat(order.getOrder()).isEqualTo(createOrder());
        }

        @Test
        void 총주문_금액을_구한다() {
            int totalCost = 142000;

            assertThat(order.getTotalCost()).isEqualTo(totalCost);
        }

        @Test
        void 디저트_개수를_구한다() {
            int dessertAmount = 2;

            assertThat(order.getDessertAmount()).isEqualTo(dessertAmount);
        }

        @Test
        void 메인메뉴_개수를_구한다() {
            int mainAmount = 2;

            assertThat(order.getMainAmount()).isEqualTo(mainAmount);
        }

        private Map<Menu, Integer> createOrder() {
            return splitOrder().stream()
                    .map(this::splitMenuAndAmount)
                    .collect(Collectors.toMap(
                            menuAndAmount -> Menu.findMenuByKoreanName(menuAndAmount.get(0)),
                            menuAndAmount -> Integer.parseInt(menuAndAmount.get(1))
                    ));
        }

        private List<String> splitOrder() {
            return Arrays.stream(userInputOrder.split(","))
                    .map(String::trim)
                    .toList();
        }

        private List<String> splitMenuAndAmount(String eachOrder) {
            return new ArrayList<>(Arrays.asList(eachOrder.split("-")));
        }
    }
}