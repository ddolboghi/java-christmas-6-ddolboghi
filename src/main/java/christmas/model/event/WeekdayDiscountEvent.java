package christmas.model.event;

import static christmas.util.constant.DiscountEventConst.NON_DISCOUNT_COST;
import static christmas.util.rule.DiscountEventRule.WEEKDAY_DISCOUNT_EVENT;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class WeekdayDiscountEvent extends DiscountEvent {
    private final int menuAmount;
    private final List<Integer> weekdays;

    public WeekdayDiscountEvent(int visitDate, int menuAmount) {
        super(visitDate);
        this.menuAmount = menuAmount;
        this.weekdays = getDates(this::isWeekday);
    }

    @Override
    public String getEventName() {
        return WEEKDAY_DISCOUNT_EVENT.getEventName();
    }

    @Override
    public int getDiscount() {
        if (isApplied()) {
            return menuAmount * WEEKDAY_DISCOUNT_EVENT.getDiscountCost();
        }
        return NON_DISCOUNT_COST;
    }

    @Override
    public boolean isApplied() {
        return weekdays.contains(visitDate);
    }

    private boolean isWeekday(LocalDate date) {
        return date.getDayOfWeek() != DayOfWeek.FRIDAY && date.getDayOfWeek() != DayOfWeek.SATURDAY;
    }
}
