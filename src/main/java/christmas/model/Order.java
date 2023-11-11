package christmas.model;

import christmas.validator.DomainValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    private static final String ORDER_DELIMITER = ",";
    private static final String MENU_AND_AMOUNT_DELIMITER = "-";
    private static final int INDEX_OF_MENU = 0;
    private static final int INDEX_OF_AMOUNT = 1;
    private final String userInputOrder;
    private Map<String, Integer> order;

    public Order(String userInputOrder) {
        this.userInputOrder = userInputOrder;
        DomainValidator.validateMenuOfOrder(getAllMenuOfOrder());
        DomainValidator.validateAmountOfOrder(getAllAmountOfOrder());
        takeOrder();
    }

    public Map<String, Integer> getOrder() {
        return Collections.unmodifiableMap(order);
    }

    private void takeOrder() {
        order = splitOrder().stream()
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
                .collect(Collectors.toList());
    }

    private List<String> getAllAmountOfOrder() {
        return splitOrder().stream()
                .map(this::splitMenuAndAmount)
                .map(menuAndAmount -> menuAndAmount.get(INDEX_OF_AMOUNT))
                .toList();
    }

    private List<String> splitOrder() {
        return Arrays.stream(userInputOrder.split(ORDER_DELIMITER)).map(String::trim)
                .toList();
    }

    private List<String> splitMenuAndAmount(String eachOrder) {
        return new ArrayList<>(Arrays.asList(eachOrder.split(MENU_AND_AMOUNT_DELIMITER)));
    }
}
