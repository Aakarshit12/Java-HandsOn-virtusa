import java.util.*;

class Instructor extends Person {
    private String department;
    private Set<Course> teachingCourses;

    public Instructor(int id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
        this.teachingCourses = new HashSet<>();
    }

    public String getDepartment() {
        return department;
    }

    public void assignCourse(Course course) {
        teachingCourses.add(course);
    }

    public void displayInfo() {
        System.out.println("Instructor: " + name + ", Dept: " + department);
    }

    public void show() {
        displayInfo();
    }
}