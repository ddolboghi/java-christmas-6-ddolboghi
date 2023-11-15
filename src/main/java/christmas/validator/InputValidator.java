package christmas.validator;

import static christmas.util.constant.ValidatorConst.COMPARE_EQUAL_VALUE_RESULT;
import static christmas.validator.ErrorMessage.COMMON_ERROR_MESSAGE;
import static christmas.validator.ErrorMessage.ORDER_ERROR_MESSAGE;
import static christmas.validator.ErrorMessage.PREFIX;
import static christmas.validator.ErrorMessage.VISIT_DATE_ERROR_MESSAGE;

import java.math.BigInteger;
import java.util.regex.Pattern;

public class InputValidator {

    private static final BigInteger MONTH_START_DATE = new BigInteger("1");
    private static final BigInteger MONTH_END_DATE = new BigInteger("31");
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");
    private static final Pattern ORDER_PATTERN = Pattern.compile("([가-힣]+-[1-9]\\d*,)*([가-힣]+-[1-9]\\d*)$");

    public static void validateVisitDate(String userInputVisitDate) {
        validateBlank(userInputVisitDate);
        validateNumeric(userInputVisitDate);
        validateDateRange(userInputVisitDate);
    }

    public static void validateOrder(String userInputOrder) {
        validateBlank(userInputOrder);
        validateOrderFormat(userInputOrder);
    }

    private static void validateBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(PREFIX + COMMON_ERROR_MESSAGE);
        }
    }

    private static void validateNumeric(String userInputVisitDate) {
        if (!NUMERIC_PATTERN.matcher(userInputVisitDate).matches()) {
            throw new IllegalArgumentException(PREFIX + VISIT_DATE_ERROR_MESSAGE);
        }
    }

    private static void validateDateRange(String userInputVisitDate) {
        if (isUnderDateRange(userInputVisitDate) || isOverDateRange(userInputVisitDate)) {
            throw new IllegalArgumentException(PREFIX + VISIT_DATE_ERROR_MESSAGE);
        }
    }

    private static boolean isUnderDateRange(String userInputVisitDate) {
        return new BigInteger(userInputVisitDate).compareTo(MONTH_START_DATE) < COMPARE_EQUAL_VALUE_RESULT;
    }

    private static boolean isOverDateRange(String userInputVisitDate) {
        return new BigInteger(userInputVisitDate).compareTo(MONTH_END_DATE) > COMPARE_EQUAL_VALUE_RESULT;
    }

    private static void validateOrderFormat(String userInputOrder) {
        if (!ORDER_PATTERN.matcher(userInputOrder).matches()) {
            throw new IllegalArgumentException(PREFIX + ORDER_ERROR_MESSAGE);
        }
    }
}
