package christmas.model.event;

public interface Event {
    String getEventName();

    int discount();

    boolean isApplied();
}
