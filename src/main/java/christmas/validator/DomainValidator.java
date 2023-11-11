package christmas.validator;

import static christmas.util.exception.ErrorMessage.ORDER_ERROR_MESSAGE;
import static christmas.util.exception.ErrorMessage.PREFIX;

import christmas.util.Menu;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class DomainValidator {
    private static final String ONLY_CATEGORY_OF_ORDER = "drink";
    private static final int COMPARE_EQUAL_VALUE_RESULT = 0;
    private static final BigInteger MIN_MENU_AMOUNT = new BigInteger("1");
    private static final BigInteger MAX_MENU_AMOUNT = new BigInteger("20");
    private static final String ERROR_MESSAGE = PREFIX + ORDER_ERROR_MESSAGE;

    public static void validateMenuOfOrder(List<String> allMenuOfOrder) {
        validateMenuExist(allMenuOfOrder);
        validateDuplicateMenu(allMenuOfOrder);
        validateOnlyCategory(allMenuOfOrder);
    }

    private static void validateMenuExist(List<String> allMenuOfOrder) {
        allMenuOfOrder.removeAll(getKoreanNamesOfMenu());
        if (!allMenuOfOrder.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    private static List<String> getKoreanNamesOfMenu() {
        return Arrays.stream(Menu.values())
                .map(Menu::getKoreanName)
                .toList();
    }

    private static void validateDuplicateMenu(List<String> allMenuOfOrder) {
        if (allMenuOfOrder.size() != Set.copyOf(allMenuOfOrder).size()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    private static void validateOnlyCategory(List<String> allMenuOfOrder) {
        allMenuOfOrder.removeAll(getOnlyCategoryMenuNames());
        if (allMenuOfOrder.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    private static List<String> getOnlyCategoryMenuNames() {
        return Stream.of(Menu.values())
                .filter(menu -> ONLY_CATEGORY_OF_ORDER.equals(menu.getCategory()))
                .map(Menu::getKoreanName)
                .toList();
    }

    public static void validateAmountOfOrder(List<String> allAmountOfOrder) {
        int amountSum = 0;
        for (String amount : allAmountOfOrder) {
            validateAmountOfOneMenu(amount);
            amountSum += Integer.parseInt(amount);
        }
        validateAmountSumOfOrder(amountSum);
    }

    private static void validateAmountSumOfOrder(int amountSum) {
        if (amountSum > MAX_MENU_AMOUNT.intValue()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    private static void validateAmountOfOneMenu(String amount) {
        if (isUnderMinMenuAmount(amount) || isOverMaxMenuAmount(amount)) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    private static boolean isUnderMinMenuAmount(String amount) {
        return new BigInteger(amount).compareTo(MIN_MENU_AMOUNT) < COMPARE_EQUAL_VALUE_RESULT;
    }

    private static boolean isOverMaxMenuAmount(String amount) {
        return new BigInteger(amount).compareTo(MAX_MENU_AMOUNT) > COMPARE_EQUAL_VALUE_RESULT;
    }
}
