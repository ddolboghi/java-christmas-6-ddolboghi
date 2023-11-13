package christmas.model;

import java.util.stream.Stream;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NOTHING("없음", 0);

    private final String badgeName;
    private final int standardTotalDiscount;

    Badge(String badgeName, int standardTotalDiscount) {
        this.badgeName = badgeName;
        this.standardTotalDiscount = standardTotalDiscount;
    }

    public static String grantBadge(int totalDiscount) {
        return Stream.of(Badge.values())
                .filter(badge -> badge.standardTotalDiscount <= totalDiscount)
                .findFirst()
                .orElse(NOTHING)
                .badgeName;
    }
}
