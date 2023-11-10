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
        @ValueSource(strings = {"0", "32"})
        void 날짜_범위_밖의_숫자면_예외를_발생시킨다(String userInputVisitDate) {
            inputValue(userInputVisitDate);

            assertThatIllegalArgumentException().isThrownBy(inputView::inputVisitDate);
        }

        @ParameterizedTest
        @ValueSource(strings = {"1", "15", "31"})
        void 정상적인_날짜면_숫자를_반환한다(String userInputVisitDate) {
            inputValue(userInputVisitDate);
            int parsedVisitDate = Integer.parseInt(userInputVisitDate);

            assertThat(inputView.inputVisitDate()).isEqualTo(parsedVisitDate);
        }
    }
}