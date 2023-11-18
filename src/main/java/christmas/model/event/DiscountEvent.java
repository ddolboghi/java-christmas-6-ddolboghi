package christmas.model.event;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public abstract class DiscountEvent implements Event {
    private static final int EVENT_YEAR = 2023;
    private static final int EVENT_MONTH = 12;
    private static final int EVENT_START_DATE = 1;
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
