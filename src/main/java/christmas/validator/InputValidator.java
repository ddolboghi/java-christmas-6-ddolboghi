package christmas.validator;

import java.util.regex.Pattern;

public class InputValidator {
    private static final String COMMON_ERROR_MESSAGE = "[ERROR] 공백이 아닌 문자를 입력해주세요.\n";
    private static final String VISIT_DATE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.\n";
    private static final String ORDER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.\n";
    private static final int MONTH_START_DATE = 1;
    private static final int MONTH_END_DATE = 31;

    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");
    private static final Pattern MENU_PATTERN = Pattern.compile("([가-힣]+-[1-9]\\d*,)*([가-힣]+-[1-9]\\d*)$");

    public static void validateVisitDate(String userInputVisitDate) {
        validateBlank(userInputVisitDate);
        validateNumeric(userInputVisitDate);
        validateDateRange(Integer.parseInt(userInputVisitDate));
    }

    public static void validateOrder(String userInputOrder) {
        validateBlank(userInputOrder);
        validateOrderFormat(userInputOrder);
    }

    private static void validateBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(COMMON_ERROR_MESSAGE);
        }
    }

    private static void validateNumeric(String userInputVisitDate) {
        if (!NUMERIC_PATTERN.matcher(userInputVisitDate).matches()) {
            throw new IllegalArgumentException(VISIT_DATE_ERROR_MESSAGE);
        }
    }

    private static void validateDateRange(int parsedVisitDate) {
        if (!isInRange(parsedVisitDate)) {
            throw new IllegalArgumentException(VISIT_DATE_ERROR_MESSAGE);
        }
    }

    private static boolean isInRange(int parsedVisitDate) {
        return (MONTH_START_DATE <= parsedVisitDate) && (MONTH_END_DATE >= parsedVisitDate);
    }

    private static void validateOrderFormat(String userInputOrder) {
        if (!MENU_PATTERN.matcher(userInputOrder).matches()) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
    }
}
