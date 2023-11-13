package christmas.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlannerControllerTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private PlannerController plannerController;
    private ByteArrayOutputStream outputStreamCaptor;
    private PrintStream standardOut;

    @BeforeEach
    public void setPlannerController() {
        plannerController = new PlannerController(new InputView(), new OutputView());

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

    @Test
    void 잘못된_방문_날짜를_입력하면_정상적인_값을_입력받을_때까지_재입력받는다() {
        String userInputWrongVisitDate = " \n1.23\n0\n32\n25\n티본스테이크-1";
        inputValue(userInputWrongVisitDate);

        assertDoesNotThrow(plannerController::preview);
    }

    @Test
    void 잘못된_주문메뉴와_개수를_입력하면_정상적인_값을_입력받을_때까지_재입력받는다() {
        String userInputWrongMenuAndAmount = " 25\n티본스테이크\n티본스테이크-0\n티본스테이크1\n티본스테이크 1\n티본스테이크:1\n티본스테이크-1";
        inputValue(userInputWrongMenuAndAmount);

        assertDoesNotThrow(plannerController::preview);
    }

    @Test
    void 정상적인_방문날짜와_주문을_입력하면_방문날짜와_주문메뉴를_출력한다() {
        String userInput = " 25\n티본스테이크-1,해산물파스타-1,레드와인-1,초코케이크-1";
        inputValue(userInput);

        plannerController.preview();

        assertThat(getOutput()).contains("25", "티본스테이크 1개", "해산물파스타 1개", "레드와인 1개", "초코케이크 1개");
    }

    @Test
    void 할인_전_총주문_금액을_출력한다() {
        String userInput = "25\n아이스크림-10";
        inputValue(userInput);

        plannerController.preview();

        assertThat(getOutput()).contains("50,000원");
    }

    @Test
    void 증정_이벤트_대상이_아니면_증정_메뉴에_없음을_출력한다() {
        String userInput = "25\n아이스크림-10";
        inputValue(userInput);

        plannerController.preview();

        assertThat(getOutput()).contains("<증정 메뉴>" + LINE_SEPARATOR + "없음");
    }

    @Test
    void 증정_이벤트_대상이면_증정_메뉴와_개수를_출력한다() {
        String userInput = "25\n티본스테이크-1,해산물파스타-1,레드와인-1,초코케이크-1";
        inputValue(userInput);

        plannerController.preview();

        assertThat(getOutput()).contains("<증정 메뉴>" + LINE_SEPARATOR + "샴페인 1개");
    }

    @Test
    void 이벤트들중_적용된_이벤트를_출력한다() {
        String userInput = "25\n티본스테이크-1,해산물파스타-1,레드와인-1,초코케이크-1";//165000원
        inputValue(userInput);

        plannerController.preview();

        assertThat(getOutput()).contains(
                "크리스마스 디데이 할인: -3,400", "평일 할인: -2,023원", "특별 할인: -1,000원", "증정 이벤트: -25,000원");
    }

    @Test
    void 적용된_이벤트가_없으면_없음을_출력한다() {
        String userInput = "30\n초코케이크-1,제로콜라-1";
        inputValue(userInput);

        plannerController.preview();

        assertThat(getOutput()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
    }

    @ParameterizedTest
    @CsvSource(value = {"25:티본스테이크-1,해산물파스타-1,레드와인-1,초코케이크-1:-31,423원", "30:초코케이크-1,제로콜라-1:0원"}, delimiter = ':')
    void 총혜택_금액을_출력한다(String userInputVisitDate, String userInputOrder, String printTotalDiscount) {
        inputValue(userInputVisitDate + "\n" + userInputOrder);

        plannerController.preview();

        assertThat(getOutput()).contains(printTotalDiscount);
    }

    @Test
    void 할인_후_예상_결제_금액을_출력한다() {
        String userInput = "25\n티본스테이크-1,해산물파스타-1,레드와인-1,초코케이크-1";//165000원
        inputValue(userInput);

        plannerController.preview();

        assertThat(getOutput()).contains("<할인 후 예상 결제 금액>" + LINE_SEPARATOR + "158,577원");
    }

    @ParameterizedTest
    @CsvSource(value = {"25:티본스테이크-1,해산물파스타-1,레드와인-1,초코케이크-1:산타", "30:초코케이크-1,제로콜라-1:없음"}, delimiter = ':')
    void 부여된_배지를_출력한다(String userInputVisitDate, String userInputOrder, String badge) {
        inputValue(userInputVisitDate + "\n" + userInputOrder);

        plannerController.preview();

        assertThat(getOutput()).contains(badge);
    }
}