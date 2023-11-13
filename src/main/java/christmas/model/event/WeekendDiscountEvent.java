package christmas.model.event;

import static christmas.util.constant.DiscountEventConst.NON_DISCOUNT_COST;
import static christmas.util.rule.DiscountEventRule.WEEKEND_DISCOUNT_EVENT;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class WeekendDiscountEvent extends DiscountEvent {
    private final int menuAmount;
    private final List<Integer> weekends;

    public WeekendDiscountEvent(int visitDate, int menuAmount) {
        super(visitDate);
        this.menuAmount = menuAmount;
        this.weekends = getDates(this::isWeekend);
    }

    @Override
    public String getEventName() {
        return WEEKEND_DISCOUNT_EVENT.getEventName();
    }

    @Override
    public int getDiscount() {
        if (isApplied()) {
            return menuAmount * WEEKEND_DISCOUNT_EVENT.getDiscountCost();
        }
        return NON_DISCOUNT_COST;
    }

    @Override
    public boolean isApplied() {
        return weekends.contains(visitDate);
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }
}
