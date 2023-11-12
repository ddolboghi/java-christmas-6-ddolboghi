package christmas.model;

import static christmas.util.OrderManagerConst.DESSERT_CATEGORY;
import static christmas.util.OrderManagerConst.INDEX_OF_AMOUNT;
import static christmas.util.OrderManagerConst.INDEX_OF_MENU;
import static christmas.util.OrderManagerConst.MAIN_CATEGORY;
import static christmas.util.OrderManagerConst.MENU_AND_AMOUNT_DELIMITER;
import static christmas.util.OrderManagerConst.ORDER_DELIMITER;

import christmas.util.Menu;
import christmas.validator.DomainValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderManager {
    private final String userInputOrder;
    private Map<Menu, Integer> order;

    public OrderManager(String userInputOrder) {
        this.userInputOrder = userInputOrder;
        DomainValidator.validateMenuOfOrder(getAllMenuOfOrder());
        DomainValidator.validateAmountOfOrder(getAllAmountOfOrder());
        takeOrder();
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

    public int getDessertAmount() {
        return order.keySet()
                .stream()
                .mapToInt(this::addDessertAmount)
                .sum();
    }

    private int addDessertAmount(Menu menu) {
        if (isDessert(menu)) {
            return order.get(menu);
        }
        return 0;
    }

    private boolean isDessert(Menu menu) {
        return menu.getCategory().equals(DESSERT_CATEGORY);
    }

    public int getMainAmount() {
        return order.keySet()
                .stream()
                .mapToInt(this::addMainAmount)
                .sum();
    }

    private int addMainAmount(Menu menu) {
        if (isMain(menu)) {
            return order.get(menu);
        }
        return 0;
    }

    private boolean isMain(Menu menu) {
        return menu.getCategory().equals(MAIN_CATEGORY);
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
