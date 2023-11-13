package christmas.model;

import christmas.model.event.Event;
import java.util.List;

public class Benefit {
    private final List<Event> events;

    public Benefit(List<Event> events) {
        this.events = events;
    }

    public List<Event> getAppliedEvents() {
        return events.stream()
                .filter(Event::isApplied)
                .toList();
    }
}
