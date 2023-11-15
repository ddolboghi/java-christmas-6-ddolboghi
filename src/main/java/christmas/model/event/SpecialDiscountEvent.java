package christmas.model.event;

import static christmas.util.constant.DiscountConstant.NON_DISCOUNT_COST;
import static christmas.util.rule.DiscountEventRules.SPECIAL_DISCOUNT_EVENT;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class SpecialDiscountEvent extends DiscountEvent {
    private static final int CHRISTMAS_DATE = 25;
    private final List<Integer> specialDays;

    public SpecialDiscountEvent(int visitDate) {
        super(visitDate);
        this.specialDays = getDates(this::isSpecialDay);
    }

    @Override
    public String getEventName() {
        return SPECIAL_DISCOUNT_EVENT.getEventName();
    }

    @Override
    public int getDiscount() {
        if (isApplied()) {
            return SPECIAL_DISCOUNT_EVENT.getDiscountCost();
        }
        return NON_DISCOUNT_COST;
    }

    @Override
    public boolean isApplied() {
        return specialDays.contains(visitDate);
    }

    private boolean isSpecialDay(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SUNDAY || date.getDayOfMonth() == CHRISTMAS_DATE;
    }
}
