package christmas.validator;

import static christmas.util.constant.ValidatorConstant.VALUE_COMPARATOR;
import static christmas.validator.ErrorMessage.ORDER_ERROR_MESSAGE;
import static christmas.validator.ErrorMessage.PREFIX;

import christmas.model.Menu;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DomainValidator {
    private static final String PROHIBITED_SINGLE_ORDER_CATEGORY = "drink";
    private static final BigInteger MAX_MENU_AMOUNT = new BigInteger("20");
    private static final String ERROR_MESSAGE = PREFIX + ORDER_ERROR_MESSAGE;

    public static void validateMenuOfOrder(List<String> allMenuOfOrder) {
        validateMenuExist(allMenuOfOrder);
        validateDuplicateMenu(allMenuOfOrder);
        validateSingleOrderCategory(allMenuOfOrder);
    }

    private static void validateMenuExist(List<String> allMenuOfOrder) {
        if (!getKoreanNamesOfMenu().containsAll(allMenuOfOrder)) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    private static Set<String> getKoreanNamesOfMenu() {
        return Arrays.stream(Menu.values())
                .map(Menu::getKoreanName)
                .collect(Collectors.toUnmodifiableSet());
    }

    private static void validateDuplicateMenu(List<String> allMenuOfOrder) {
        if (allMenuOfOrder.size() != Set.copyOf(allMenuOfOrder).size()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    private static void validateSingleOrderCategory(List<String> allMenuOfOrder) {
        if (Menu.getMenuNamesOfSingleOrderCategory(PROHIBITED_SINGLE_ORDER_CATEGORY).containsAll(allMenuOfOrder)) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
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
        if (new BigInteger(amount).compareTo(MAX_MENU_AMOUNT) > VALUE_COMPARATOR) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }
}
