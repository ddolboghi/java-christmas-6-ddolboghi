package christmas.validator;

import java.util.regex.Pattern;

public class VisitDateValidator extends CommonValidator {
    private static final String VISIT_DATE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.\n";
    private static final int MONTH_START_DATE = 1;
    private static final int MONTH_END_DATE = 31;

    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");

    public static void validateNumeric(String userInputVisitDate) {
        if (!isNumeric(userInputVisitDate)) {
            throw new IllegalArgumentException(VISIT_DATE_ERROR_MESSAGE);
        }
    }

    public static void validateDateRange(String userInputVisitDate) {
        if (!isInRange(userInputVisitDate)) {
            throw new IllegalArgumentException(VISIT_DATE_ERROR_MESSAGE);
        }
    }

    private static boolean isNumeric(String userInputVisitDate) {
        return NUMERIC_PATTERN.matcher(userInputVisitDate).matches();
    }

    private static boolean isInRange(String userInputVisitDate) {
        if (isNumeric(userInputVisitDate)) {
            int visitDate = Integer.parseInt(userInputVisitDate);
            return (MONTH_START_DATE <= visitDate) && (MONTH_END_DATE >= visitDate);
        }
        return false;
    }
}
