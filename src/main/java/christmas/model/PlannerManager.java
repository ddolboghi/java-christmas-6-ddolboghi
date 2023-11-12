package christmas.model;

public class PlannerManager {
    private final int visitDate;

    public PlannerManager(String userInputVisitDate) {
        this.visitDate = Integer.parseInt(userInputVisitDate);
    }

    public int getVisitDate() {
        return visitDate;
    }
}
