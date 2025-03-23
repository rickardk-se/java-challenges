import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {

    static Random random = new Random();

    public static void main(String[] args) {

        int studentCount = 25;

        List<Student> students = new ArrayList<>();

        for (int i = 0; i < studentCount; i++) {
            students.add(new Student(randomName(),
                    randomCourse(), random.nextInt(2020, 2024)));
        }
        // Sort by id
        Collections.sort(students, Collections.reverseOrder());
        printStudents(students);

        ScoredStudent swedbankStudent = new ScoredStudent("Johannes K", "Python", 2021);

        students.add(swedbankStudent);

        printStudents(students);

        List<ScoredStudent> swedbankStudents = new ArrayList<>();

        for (int i = 0; i < studentCount; i++) {
            swedbankStudents.add(new ScoredStudent(randomName(),
                    randomCourse(), random.nextInt(2020, 2024)));
        }

        // Sort by score
        Collections.sort(swedbankStudents, new StudentScoreComparator().reversed());
        printStudentsGeneric(swedbankStudents);
        printStudentsWildcard(swedbankStudents);

        List<?> list = new ArrayList<>();
        System.out.println(list);

        testList(new ArrayList<>(List.of(1, 2, 3)));
        testList(new ArrayList<>(List.of("Apples", "Bananas", "Oranges")));

        System.out.println();

        var queryList = new QueryList<>(swedbankStudents);
        var matches = queryList.getMatches("Course", "Python");
        printStudentsGeneric(matches);

        var students2021 = QueryList.getMatches(swedbankStudents, "YearStarted", "2021");
        printStudentsGeneric(students2021);

        System.out.println("Students with less than 50% complete by id:");
        var studentsLessThan50 = QueryList.getMatches(swedbankStudents, "PercentComplete", "50");
        Collections.sort(studentsLessThan50, Collections.reverseOrder());
        printStudentsGeneric(studentsLessThan50);

        System.out.println("Students with less than 50% complete by percent complete:");
        Collections.sort(studentsLessThan50, new StudentScoreComparator().reversed());
        printStudentsGeneric(studentsLessThan50);

    }

    public static void testList(List<?> list) {
        for (var item : list) {
            if (item instanceof String s) {
                System.out.println("String: " + s.toUpperCase());
            } else if (item instanceof Integer i) {
                System.out.println("Integer: " + i.floatValue());
            }
        }
    }

    public static void printStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println();
    }

    public static <T extends Student> void printStudentsGeneric(List<T> students) {
        for (T student : students) {
            System.out.println(student.getYearStarted() + ": " + student);
        }
        System.out.println();
    }

    public static void printStudentsWildcard(List<? extends Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println();
    }

    public static String randomName() {
        String[] firstNames = { "Jaanika", "Mihkel", "Ivari", "Paul", "Grete", "Johannes", "Anneli" };
        int lastNameIndex = random.nextInt(65, 91);
        return firstNames[random.nextInt(firstNames.length)] + " " + (char) lastNameIndex;
    }

    public static String randomCourse() {
        String[] courses = { "Java", "Python", "C++", "JavaScript", "Ruby", "C#", "Rust" };
        return courses[random.nextInt(courses.length)];
    }
}