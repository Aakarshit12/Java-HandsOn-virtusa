import java.util.*;
import java.util.stream.Collectors;

public class UniversityManagement {
    private static Map<String, Course> courseMap = new HashMap<>();

    public static void main(String[] args) {

        String[] departments = { "CS", "Math", "Physics" };

        Instructor i1 = new Instructor(1, "Dr. Smith", "smith@uni.com", "CS");
        Instructor i2 = new Instructor(2, "Dr. John", "john@uni.com", "Math");

        Student s1 = new Student(101, "Alice", "alice@uni.com", "CS");
        Student s2 = new Student(102, "Bob", "bob@uni.com", "Math");
        Student s3 = new Student(103, "Charlie", "charlie@uni.com", "CS");

        Course c1 = new Course("C101", "Data Structures", 2); // ex2: capacity 2
        Course c2 = new Course("M201", "Linear Algebra", 5);

        courseMap.put(c1.getCourseId(), c1);
        courseMap.put(c2.getCourseId(), c2);

        c1.assignInstructor(i1);
        c2.assignInstructor(i2);

        c1.enrollStudent(s1);
        c1.enrollStudent(s3);
        c1.enrollStudent(s2); // ex2: should print capacity full
        c2.enrollStudent(s2);

        // ex5: assign grades
        c1.assignGrade(s1, 88.5);
        c1.assignGrade(s3, 74.0);
        c2.assignGrade(s2, 91.0);

        // ex1: GPA
        System.out.println("\nGPA:");
        System.out.println("  " + s1.getName() + " : " + s1.getGPA());
        System.out.println("  " + s2.getName() + " : " + s2.getGPA());
        System.out.println("  " + s3.getName() + " : " + s3.getGPA());

        // ex3: remove student
        System.out.println("\nRemoving Charlie from Data Structures...");
        c1.removeStudent(s3);

        List<Student> csStudents = Arrays.asList(s1, s2, s3)
                .stream()
                .filter(s -> s.getMajor().equals("CS"))
                .collect(Collectors.toList());

        System.out.println("\nCS Students:");
        csStudents.forEach(s -> System.out.println("  " + s.getName()));

        System.out.println("\nStudent Count Per Course:");
        courseMap.values()
                .stream()
                .forEach(course -> System.out.println("  " + course.getTitle() + " : " + course.getStudents().size()));

        List<Course> popularCourses = courseMap.values()
                .stream()
                .filter(course -> course.getStudents().size() > 1)
                .collect(Collectors.toList());

        System.out.println("\nPopular Courses:");
        popularCourses.forEach(c -> System.out.println("  " + c.getTitle()));

        Set<String> uniqueDepartments = Arrays.stream(departments)
                .collect(Collectors.toSet());

        System.out.println("\nDepartments:");
        uniqueDepartments.forEach(d -> System.out.println("  " + d));

        // ex4: sort alphabetically
        List<Student> sorted = Arrays.asList(s1, s2, s3)
                .stream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .collect(Collectors.toList());

        System.out.println("\nStudents Alphabetically:");
        sorted.forEach(s -> System.out.println("  " + s.getName()));
    }
}