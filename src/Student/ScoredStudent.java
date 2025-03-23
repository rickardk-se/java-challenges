import java.util.Random;

public class ScoredStudent extends Student {
    static Random random = new Random();
    double percentComplete;

    public ScoredStudent(String name, String course, int yearStarted) {
        super(name, course, yearStarted);
        this.percentComplete = random.nextDouble(0, 100.001);
    }

    @Override
    public String toString() {
        return "%s %8.1f%%".formatted(super.toString(), percentComplete);
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        String fName = fieldName.toUpperCase();
        return switch (fName) {
            case "NAME" -> name.equalsIgnoreCase(value);
            case "COURSE" -> course.equalsIgnoreCase(value);
            case "YEARSTARTED" -> yearStarted == Integer.parseInt(value);
            case "PERCENTCOMPLETE" -> percentComplete <= Double.parseDouble(value);
            default -> false;
        };
    }
}