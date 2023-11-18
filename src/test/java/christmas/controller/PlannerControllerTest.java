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
        String userInputWrongVisitDate =
                " " + LINE_SEPARATOR + "1.23"
                        + LINE_SEPARATOR + "0"
                        + LINE_SEPARATOR + "32"
                        + LINE_SEPARATOR + "31"
                        + LINE_SEPARATOR + "티본스테이크-1";
        inputValue(userInputWrongVisitDate);

        assertDoesNotThrow(plannerController::preview);
    }

    @Test
    void 잘못된_주문메뉴_또는_개수_0을_입력하면_정상적인_값을_입력받을_때까지_재입력받는다() {
        String userInputWrongMenuAndAmount =
                "25" + LINE_SEPARATOR + "티본스테이크"
                        + LINE_SEPARATOR + "티본스테이크-0"
                        + LINE_SEPARATOR + "티본스테이크1"
                        + LINE_SEPARATOR + "티본스테이크:1"
                        + LINE_SEPARATOR + "티본스테이크-1";
        inputValue(userInputWrongMenuAndAmount);

        assertDoesNotThrow(plannerController::preview);
    }

    @Test
    void 정상적인_방문날짜와_주문을_입력하면_방문날짜와_주문메뉴를_출력한다() {
        String userInput = " 25" + LINE_SEPARATOR + "티본스테이크-1,해산물파스타-1,레드와인-1,초코케이크-1";
        inputValue(userInput);

        plannerController.preview();

        assertThat(getOutput()).contains("25", "티본스테이크 1개", "해산물파스타 1개", "레드와인 1개", "초코케이크 1개");
    }

    @Test
    void 할인_전_총주문_금액을_출력한다() {
        String userInput = "25" + LINE_SEPARATOR + "아이스크림-10";
        inputValue(userInput);

        plannerController.preview();

        assertThat(getOutput()).contains("50,000원");
    }

    @ParameterizedTest
    @CsvSource(value = {"25:아이스크림-10:없음", "25:티본스테이크-1,해산물파스타-1,레드와인-1,초코케이크-1:샴페인 1개"}, delimiter = ':')
    void 증정_메뉴를_출력한다(String userInputVisitDate, String userInputOrder, String giftMenu) {
        inputValue(userInputVisitDate + LINE_SEPARATOR + userInputOrder);

        plannerController.preview();

        assertThat(getOutput()).contains("<증정 메뉴>" + LINE_SEPARATOR + giftMenu);
    }

    @Test
    void 적용된_이벤트만_혜택_내역에_출력한다() {
        String userInput = "25" + LINE_SEPARATOR + "티본스테이크-1,해산물파스타-1,레드와인-1,초코케이크-1";//165000원
        inputValue(userInput);

        plannerController.preview();

        assertThat(getOutput()).contains(
                "크리스마스 디데이 할인: -3,400", "평일 할인: -2,023원", "특별 할인: -1,000원", "증정 이벤트: -25,000원");
    }

    @Test
    void 적용된_이벤트가_하나도_없으면_없음을_출력한다() {
        String userInput = "30" + LINE_SEPARATOR + "초코케이크-1,제로콜라-1";
        inputValue(userInput);

        plannerController.preview();

        assertThat(getOutput()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
    }

    @ParameterizedTest
    @CsvSource(value = {"25:티본스테이크-1,해산물파스타-1,레드와인-1,초코케이크-1:-31,423원", "30:초코케이크-1,제로콜라-1:0원"}, delimiter = ':')
    void 총혜택_금액을_출력한다(String userInputVisitDate, String userInputOrder, String printTotalDiscount) {
        inputValue(userInputVisitDate + LINE_SEPARATOR + userInputOrder);

        plannerController.preview();

        assertThat(getOutput()).contains("<총혜택 금액>" + LINE_SEPARATOR + printTotalDiscount);
    }

    @ParameterizedTest
    @CsvSource(value = {"25:티본스테이크-1,해산물파스타-1,레드와인-1,초코케이크-1:158,577원", "30:아이스크림-10:50,000원"}, delimiter = ':')
    void 할인_후_예상_결제_금액을_출력한다(String userInputVisitDate, String userInputOrder, String printTotalDiscount) {
        inputValue(userInputVisitDate + LINE_SEPARATOR + userInputOrder);

        plannerController.preview();

        assertThat(getOutput()).contains("<할인 후 예상 결제 금액>" + LINE_SEPARATOR + printTotalDiscount);
    }

    @ParameterizedTest
    @CsvSource(value = {"25:티본스테이크-1,해산물파스타-1,레드와인-1,초코케이크-1:산타", "30:초코케이크-1,제로콜라-1:없음"}, delimiter = ':')
    void 부여된_배지를_출력한다(String userInputVisitDate, String userInputOrder, String printBadge) {
        inputValue(userInputVisitDate + LINE_SEPARATOR + userInputOrder);

        plannerController.preview();

        assertThat(getOutput()).contains("<12월 이벤트 배지>" + LINE_SEPARATOR + printBadge);
    }
}