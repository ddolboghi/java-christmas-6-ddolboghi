package christmas.model;

import static christmas.util.rule.GiftEventRule.GIFT_EVENT_MENU_AMOUNT;
import static christmas.util.rule.GiftEventRule.NON_GIFT_MENU;

import christmas.model.event.GiftEvent;
import christmas.util.Menu;

public class GiftEventLoader {

    public static String getGiftMenu(int totalCost) {
        if (new GiftEvent(totalCost).isApplied()) {
            return String.format(GIFT_EVENT_MENU_AMOUNT, Menu.CHAMPAGNE.getKoreanName());
        }
        return NON_GIFT_MENU;
    }
}
