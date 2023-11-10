package christmas.validator;

public class CommonValidator {
    private static final String COMMON_ERROR_MESSAGE = "[ERROR] 공백이 아닌 문자를 입력해주세요.";

    public static void validateBlank(String input) {
        if (input == null || input.isBlank()) {
            System.out.println(COMMON_ERROR_MESSAGE);
            throw new IllegalArgumentException();
        }
    }
}
