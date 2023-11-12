package christmas.model;

import christmas.model.event.Event;
import java.util.ArrayList;
import java.util.List;

public class Benefit {
    private final List<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        if (event.isApplied()) {
            events.add(event);
        }
    }

}
