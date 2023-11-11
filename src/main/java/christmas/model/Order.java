package christmas.model;

import christmas.validator.DomainValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    private static final String ORDER_DELIMITER = ",";
    private static final String MENU_AND_AMOUNT_DELIMITER = "-";
    private static final int INDEX_OF_MENU = 0;
    private static final int INDEX_OF_AMOUNT = 1;
    private final String userInputOrder;

    public Order(String userInputOrder) {
        this.userInputOrder = userInputOrder;
        DomainValidator.validateMenuOfOrder(getAllMenuOfOrder());
        DomainValidator.validateAmountOfOrder(getAllAmountOfOrder());
    }

    private Map<String, Integer> createOrder() {
        return splitOrder().stream()
                .map(this::splitMenuAndAmount)
                .collect(Collectors.toMap(
                        menuAndAmount -> menuAndAmount.get(INDEX_OF_MENU),
                        menuAndAmount -> Integer.parseInt(menuAndAmount.get(INDEX_OF_AMOUNT))
                ));
    }

    private List<String> getAllMenuOfOrder() {
        return splitOrder().stream()
                .map(this::splitMenuAndAmount)
                .map(menuAndAmount -> menuAndAmount.get(INDEX_OF_MENU))
                .toList();
    }

    private List<String> getAllAmountOfOrder() {
        return splitOrder().stream()
                .map(this::splitMenuAndAmount)
                .map(menuAndAmount -> menuAndAmount.get(INDEX_OF_AMOUNT))
                .toList();
    }

    private List<String> splitOrder() {
        return new ArrayList<>(Arrays.asList(userInputOrder.split(ORDER_DELIMITER)));
    }

    private List<String> splitMenuAndAmount(String eachOrder) {
        return new ArrayList<>(Arrays.asList(eachOrder.split(MENU_AND_AMOUNT_DELIMITER)));
    }
}
