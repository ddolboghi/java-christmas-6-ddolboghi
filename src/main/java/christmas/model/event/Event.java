package christmas.model.event;

public interface Event {
    String getEventName();

    int getDiscount();

    boolean isApplied();
}
