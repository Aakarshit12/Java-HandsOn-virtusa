// Student class
class Student {
    private String name;
    private int marks;
    static String schoolName;
    static int studentCount;

    // static block
    static {
        schoolName = "ABC School";
    }

    // constructor
    Student(String name, int marks) {
        this.name = name;
        if (marks < 0)
            marks = 0;
        if (marks > 100)
            marks = 100;
        this.marks = marks;
        studentCount++;
    }

    // restrict direct access
    int getMarks() {
        return marks;
    }

    // grade using operators
    char grade() {
        if (marks >= 90)
            return 'A';
        else if (marks >= 75)
            return 'B';
        else if (marks >= 50)
            return 'C';
        else
            return 'F';
    }

    // display
    void display() {
        System.out.println(name + " " + marks + " " + grade() + " " + schoolName);
    }

    // static method
    static int getStudentCount() {
        return studentCount;
    }
}

// main Class
public class First_HandsOn {
    public static void main(String[] args) {

        Student s1 = new Student("Aman", 85);
        Student s2 = new Student("Raj", 92);
        Student s3 = new Student("Karan", 40);

        s1.display();
        s2.display();
        s3.display();

        System.out.println("Total:" + Student.getStudentCount());
    }
}