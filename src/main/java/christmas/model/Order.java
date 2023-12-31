package christmas.model;

import christmas.validator.DomainValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    private static final String ORDER_DELIMITER = ",";
    private static final String MENU_AND_AMOUNT_DELIMITER = "-";
    private static final int INDEX_OF_MENU = 0;
    private static final int INDEX_OF_AMOUNT = 1;
    private final String userInputOrder;
    private Map<Menu, Integer> order;

    public Order(String userInputOrder) {
        this.userInputOrder = userInputOrder;
        DomainValidator.validateMenuOfOrder(getAllMenuOfOrder());
        DomainValidator.validateAmountOfOrder(getAllAmountOfOrder());
        takeOrder();
    }

    public int getTotalCostAfterDiscount(int totalDiscountExceptGiftEvent) {
        return getTotalCost() - totalDiscountExceptGiftEvent;
    }

    public Map<String, Integer> getOrder() {
        return order.keySet().stream()
                .collect(Collectors.toMap(Menu::getKoreanName, menu -> order.get(menu), (a, b) -> b));
    }

    public int getTotalCost() {
        return order.keySet()
                .stream()
                .mapToInt(menu -> menu.getPrice() * order.get(menu))
                .sum();
    }

    public int getMenuAmount(String menuCategory) {
        return order.keySet()
                .stream()
                .mapToInt(menu -> addMenuAmount(menu, menuCategory))
                .sum();
    }

    private int addMenuAmount(Menu menu, String menuCategory) {
        if (isMenuCategory(menu, menuCategory)) {
            return order.get(menu);
        }
        return 0;
    }

    private boolean isMenuCategory(Menu menu, String menuCategory) {
        return menu.getCategory().equals(menuCategory);
    }

    private void takeOrder() {
        Map<Menu, Integer> order = new EnumMap<>(Menu.class);
        for (String eachOrder : splitOrder()) {
            List<String> splitMenuAndAmount = splitMenuAndAmount(eachOrder);
            order.put(Menu.findMenuByKoreanName(splitMenuAndAmount.get(INDEX_OF_MENU)),
                    Integer.parseInt(splitMenuAndAmount.get(INDEX_OF_AMOUNT)));
        }
        this.order = order;
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
