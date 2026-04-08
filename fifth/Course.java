import java.util.*;

class Course {
    private String courseId;
    private String title;
    private Instructor instructor;
    private List<Student> students;
    private int capacity;

    public Course(String courseId, String title, int capacity) {
        this.courseId = courseId;
        this.title = title;
        this.students = new ArrayList<>();
        this.capacity = capacity;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void assignInstructor(Instructor instructor) {
        this.instructor = instructor;
        instructor.assignCourse(this);
    }

    // ex2: capacity check
    public void enrollStudent(Student student) {
        if (students.size() >= capacity) {
            System.out.println(title + ": capacity full, cannot enrol " + student.getName());
            return;
        }
        students.add(student);
        student.enrollCourse(this);
    }

    // ex3
    public void removeStudent(Student student) {
        if (students.remove(student))
            student.removeCourse(this);
        else
            System.out.println(student.getName() + " not found in " + title);
    }

    // ex5
    public void assignGrade(Student student, double grade) {
        if (!students.contains(student)) {
            System.out.println(student.getName() + " is not enrolled in " + title);
            return;
        }
        student.setGrade(this, grade);
    }
}