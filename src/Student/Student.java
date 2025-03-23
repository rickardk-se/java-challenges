public class Student implements QueryItem, Comparable<Student> {
    private static int nextId = 1;
    String name;
    String course;
    int yearStarted;
    int id;

    public Student(String name, String course, int yearStarted) {
        this.name = name;
        this.course = course;
        this.yearStarted = yearStarted;
        this.id = nextId++;
    }

    @Override
    public int compareTo(Student other) {
        return other.id - this.id;
    }

    public int getYearStarted() {
        return yearStarted;
    }

    @Override
    public String toString() {
        return "%15s (%02d) %-15s %d".formatted(name, id, course, yearStarted);
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        String fName = fieldName.toUpperCase();
        return switch (fName) {
            case "NAME" -> name.equalsIgnoreCase(value);
            case "COURSE" -> course.equalsIgnoreCase(value);
            case "YEARSTARTED" -> yearStarted == Integer.parseInt(value);
            default -> false;
        };
    }
}
