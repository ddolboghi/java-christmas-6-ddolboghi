package christmas.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {
    private InputView inputView;

    @BeforeEach
    public void setUp() {
        inputView = new InputView();
    }

    @AfterEach
    public void restoreSettings() {
        Console.close();
    }

    private void inputValue(String value) {
        System.setIn(new ByteArrayInputStream(value.getBytes()));
    }

    @Nested
    class 방문_날짜_입력시_ {

        @ParameterizedTest
        @ValueSource(strings = {" ", "\n", "\r", "\t"})
        void 비었거나_공백이면_예외를_발생시킨다(String userInputVisitDate) {
            inputValue(userInputVisitDate);

            assertThatIllegalArgumentException().isThrownBy(inputView::inputVisitDate);
        }

        @ParameterizedTest
        @ValueSource(strings = {"1.23", "1 23", "-1000", "테스트"})
        void 숫자_이외의_문자면_예외를_발생시킨다(String userInputVisitDate) {
            inputValue(userInputVisitDate);

            assertThatIllegalArgumentException().isThrownBy(inputView::inputVisitDate);
        }

        @ParameterizedTest
        @ValueSource(strings = {"0", "32", "30000000000000000"})
        void 날짜_범위_밖의_숫자면_예외를_발생시킨다(String userInputVisitDate) {
            inputValue(userInputVisitDate);

            assertThatIllegalArgumentException().isThrownBy(inputView::inputVisitDate);
        }

        @ParameterizedTest
        @ValueSource(strings = {"1", "15", "31"})
        void 정상적인_날짜면_해당_값을_반환한다(String userInputVisitDate) {
            inputValue(userInputVisitDate);

            assertThat(inputView.inputVisitDate()).isEqualTo(userInputVisitDate);
        }
    }

    @Nested
    class 주문메뉴와_개수_입력_시 {

        @ParameterizedTest
        @ValueSource(strings = {" ", "\n", "\r", "\t"})
        void 비었거나_공백이면_예외를_발생시킨다(String userInputOrderMenu) {
            inputValue(userInputOrderMenu);

            assertThatIllegalArgumentException().isThrownBy(inputView::inputOrder);
        }

        @ParameterizedTest
        @ValueSource(strings = {"바비큐립", "바비큐립1", "바비큐립 1", "바비큐립=1", "바비큐립:1"})
        void 입력형식에_맞지_않으면_예외를_발생시킨다(String userInputOrderMenu) {
            inputValue(userInputOrderMenu);

            assertThatIllegalArgumentException().isThrownBy(inputView::inputOrder);
        }

        @ParameterizedTest
        @ValueSource(strings = {"바비큐립-10", "해산물파스타-2,레드와인-1,초코케이크-1"})
        void 정상적인_형식이면_해당_값을_반환한다(String userInputOrderMenu) {
            inputValue(userInputOrderMenu);

            assertThat(inputView.inputOrder()).isEqualTo(userInputOrderMenu);
        }
    }
}