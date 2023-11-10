package christmas.validator;

import java.util.regex.Pattern;

public class VisitDateValidator extends CommonValidator {
    private static final String VISIT_DATE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");

    public static void validateNumeric(String userInputVisitDate) {
        if (!NUMERIC_PATTERN.matcher(userInputVisitDate).matches()) {
            throw new IllegalArgumentException(VISIT_DATE_ERROR_MESSAGE);
        }
    }
}
