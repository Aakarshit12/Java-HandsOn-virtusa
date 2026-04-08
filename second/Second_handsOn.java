class Student {

    private String name;
    private int marks;
    static String schoolName;
    static int studentCount;

    static {
        schoolName = "Wisdom World School";
    }

    Student(String name, int marks) {
        if (name == null || name.trim().isEmpty())
            System.out.println("Name cannot be empty.");

        if (marks < 0 || marks > 100)
            System.out.println("Marks must be between 0 and 100.");

        this.name = name;
        this.marks = marks;
        studentCount++;
    }

    int getMarks() {
        return marks;
    }

    // Passing marks=33
    boolean hasPassed() {
        return marks >= 33;
    }

    char computeGrade() {
        if (!hasPassed())
            return 'F';
        if (marks >= 90)
            return 'A';
        if (marks >= 75)
            return 'B';
        if (marks >= 50)
            return 'C';
        return 'D';
    }

    void display() {
        String status = hasPassed() ? "PASS" : "FAIL";
        System.out.println(name + " | " + marks + " | Grade: " + computeGrade() + " | " + status);
    }

    static int getStudentCount() {
        return studentCount;
    }
}

public class Second_handsOn {

    public static void main(String[] args) {

        System.out.println("School: " + Student.schoolName);

        Student s1 = new Student("Aman ", 85);
        Student s2 = new Student("Raj ", 92);
        Student s3 = new Student("Karan ", 40);
        Student s4 = new Student("Priya ", 28);

        s1.display();
        s2.display();
        s3.display();
        s4.display();

        System.out.println("Total Students: " + Student.getStudentCount());
    }
}