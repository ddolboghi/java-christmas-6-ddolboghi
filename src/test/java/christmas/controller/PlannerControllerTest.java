package christmas.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlannerControllerTest {

    private PlannerController plannerController;

    @BeforeEach
    public void setPlannerController() {
        plannerController = new PlannerController(new InputView(), new OutputView());
    }

    @AfterEach
    public void restoreSettings() {
        Console.close();
    }

    private void inputValue(String value) {
        System.setIn(new ByteArrayInputStream(value.getBytes()));
    }

    @Test
    void 정상적인_방문_날짜를_입력받을_때까지_재입력받는다() {
        String userInputVisitDate = " \n1.23\n0\n32\n25";
        inputValue(userInputVisitDate);

        assertDoesNotThrow(plannerController::preview);
    }
}