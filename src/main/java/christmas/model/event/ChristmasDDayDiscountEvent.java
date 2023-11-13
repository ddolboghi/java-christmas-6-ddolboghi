package christmas.model.event;

import static christmas.util.constant.DiscountEventConst.NON_DISCOUNT_COST;
import static christmas.util.rule.ChristmasDDayDiscountEventRule.CHRISTMAS_EVENT;

public class ChristmasDDayDiscountEvent extends DiscountEvent {

    public ChristmasDDayDiscountEvent(int visitDate) {
        super(visitDate);
    }

    @Override
    public String getEventName() {
        return CHRISTMAS_EVENT.getEventName();
    }

    @Override
    public int getDiscount() {
        if (isApplied()) {
            return (visitDate - CHRISTMAS_EVENT.getStartDate()) * CHRISTMAS_EVENT.getUnitDiscountCost()
                    + CHRISTMAS_EVENT.getDefaultDiscountCost();
        }
        return NON_DISCOUNT_COST;
    }

    @Override
    public boolean isApplied() {
        return visitDate >= CHRISTMAS_EVENT.getStartDate() && visitDate <= CHRISTMAS_EVENT.getEndDate();
    }
}
