package christmas.model.event;

import static christmas.util.constant.DiscountEventConst.EVENT_MONTH;
import static christmas.util.constant.DiscountEventConst.EVENT_START_DATE;
import static christmas.util.constant.DiscountEventConst.EVENT_YEAR;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public abstract class DiscountEvent implements Event {
    protected int visitDate;

    public DiscountEvent(int visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    abstract public String getEventName();

    @Override
    abstract public int getDiscount();

    @Override
    abstract public boolean isApplied();

    protected List<Integer> getDates(Predicate<LocalDate> filter) {
        YearMonth yearMonth = YearMonth.of(EVENT_YEAR, EVENT_MONTH);
        return IntStream.rangeClosed(EVENT_START_DATE, yearMonth.lengthOfMonth())
                .mapToObj(yearMonth::atDay)
                .filter(filter)
                .map(LocalDate::getDayOfMonth)
                .toList();
    }
}
