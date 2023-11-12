package christmas.model;

import static christmas.util.events.PresentationEventRule.NON_PRESENTATION_EVENT_MENU;

import christmas.model.event.PresentationEvent;

public class EventManager {
    private final Benefit benefit;
    private final int totalCost;
    private final PresentationEvent presentationEvent;

    public EventManager(Benefit benefit, int totalCost) {
        this.benefit = benefit;
        this.totalCost = totalCost;
        presentationEvent = new PresentationEvent(totalCost);
    }

    public String getPresentationMenu() {
        return presentationEvent.presentMenu();
    }

    public void addPresentationEventToBenefit() {
        if (getPresentationMenu().equals(NON_PRESENTATION_EVENT_MENU)) {
            benefit.addEvent(presentationEvent);
        }
    }


}
