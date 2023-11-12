package christmas.model;

public class PlannerManager {
    private final int visitDate;
    private final EventManager eventManager;
    private final Benefit benefit;

    public PlannerManager(String userInputVisitDate, int totalCost) {
        this.visitDate = Integer.parseInt(userInputVisitDate);
        benefit = new Benefit();
        eventManager = new EventManager(benefit, totalCost);
    }

    public int getVisitDate() {
        return visitDate;
    }

    public String getPresentationMenu() {
        return eventManager.getPresentationMenu();
    }
}
