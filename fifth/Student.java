import java.util.*;

class Student extends Person {
    private String major;
    private List<Course> enrolledCourses;
    private Map<Course, Double> gradeMap;

    public Student(int id, String name, String email, String major) {
        super(id, name, email);
        this.major = major;
        this.enrolledCourses = new ArrayList<>();
        this.gradeMap = new HashMap<>();
    }

    public String getMajor() {
        return major;
    }

    public void enrollCourse(Course course) {
        enrolledCourses.add(course);
    }

    // ex3
    public void removeCourse(Course course) {
        enrolledCourses.remove(course);
    }

    // ex5
    public void setGrade(Course course, double grade) {
        gradeMap.put(course, grade);
    }

    // ex1: average of all course grades
    public double getGPA() {
        if (gradeMap.isEmpty())
            return 0.0;
        double total = 0;
        for (double g : gradeMap.values())
            total += g;
        return total / gradeMap.size();
    }


    public void displayInfo() {
        System.out.println("Student: " + name + ", Major: " + major);
    }
}