package christmas.view;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {
    private InputView inputView;
    private ByteArrayOutputStream outputStreamCaptor;
    private PrintStream standardOut;

    @BeforeEach
    public void setUp() {
        inputView = new InputView();

        standardOut = System.out;
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void restoreSettings() {
        Console.close();
        System.setOut(standardOut);
    }

    private void inputValue(String value) {
        System.setIn(new ByteArrayInputStream(value.getBytes()));
    }

    private String getOutput() {
        return outputStreamCaptor.toString();
    }
    @Nested
    class 방문_날짜_입력시_{
        @ParameterizedTest
        @ValueSource(strings = {" ", "\n", "\r", "\t"})
        void 비었거나_공백이면_예외를_발생시킨다(String userInputVisitDate) {
            inputValue(userInputVisitDate);

            assertThatIllegalArgumentException().isThrownBy(inputView::inputVisitDate);
        }

    }
}