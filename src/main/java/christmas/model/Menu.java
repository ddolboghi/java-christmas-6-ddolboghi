package christmas.model;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Menu {
    PINE_MUSHROOM_SOUP("양송이수프", "appetizer", 6000),
    TAPAS("타파스", "appetizer", 5500),
    CAESAR_SALAD("시저샐러드", "appetizer", 8000),
    T_BONE_STEAK("티본스테이크", "main", 55000),
    BARBECUE_RIBS("바비큐립", "main", 54000),
    SEAFOOD_PASTA("해산물파스타", "main", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", "main", 25000),
    CHOCOLATE_CAKE("초코케이크", "dessert", 15000),
    ICE_CREAM("아이스크림", "dessert", 5000),
    ZERO_COLA("제로콜라", "drink", 3000),
    RED_WINE("레드와인", "drink", 60000),
    CHAMPAGNE("샴페인", "drink", 25000);

    private final String koreanName;
    private final String category;
    private final int price;

    Menu(String koreanName, String category, int price) {
        this.koreanName = koreanName;
        this.category = category;
        this.price = price;
    }

    //이 메서드는 Order에서만 사용되고, 검증된 값만 받으므로 null을 반환하지 않는다.
    public static Menu findMenuByKoreanName(String koreanName) {
        return Stream.of(Menu.values())
                .filter(menu -> menu.koreanName.equals(koreanName))
                .findFirst()
                .get();
    }

    public static Set<String> getMenuNamesOfSingleOrderCategory(String prohibitedSingleOrderCategory) {
        return Stream.of(Menu.values())
                .filter(menu -> prohibitedSingleOrderCategory.equals(menu.getCategory()))
                .map(Menu::getKoreanName)
                .collect(Collectors.toUnmodifiableSet());
    }

    public String getKoreanName() {
        return koreanName;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }
}
