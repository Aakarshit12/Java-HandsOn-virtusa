class Student {

    private int rollNo;
    private String nm;
    private int[] s; // marks in 5 subjects

    static String college = "Greenwood Degree College";

    Student() {
        rollNo = 0;
        nm = "N/A";
        s = new int[5];
    }

    Student(int rollNo, String nm, int[] s) {
        if (nm == null || nm.trim().length() == 0)
            throw new IllegalArgumentException("Roll " + rollNo + ": name blank");

        for (int i = 0; i < 5; i++) {
            if (s[i] < 0 || s[i] > 100)
                throw new IllegalArgumentException(nm + " sub" + (i + 1) + " out of range: " + s[i]);
        }

        this.rollNo = rollNo;
        this.nm = nm.trim();
        this.s = s;
    }

    public int calculateTotal() {
        int tot = 0;
        for (int i = 0; i < 5; i++)
            tot += s[i];
        return tot;
    }

    public double calculateAverage() {
        return calculateTotal() / 5.0;// avg==pct
    }

    protected double calculatePercentage() {
        return calculateTotal() / 5.0;
    }

    public char getGrade() {
        double avg = calculateAverage();
        if (avg > 80)
            return 'A';
        if (avg >= 60)
            return 'B';
        if (avg >= 40)
            return 'C';
        return 'F';
    }

    public void displayDetails() {
        System.out.println("Roll     : " + rollNo + "   Name: " + nm);
        System.out.println("Marks    : " + s[0] + "  " + s[1] + "  " + s[2] + "  " + s[3] + "  " + s[4]);
        System.out.printf("Total    : %d/500    Pct: %.1f%%%n", calculateTotal(), calculatePercentage());
        System.out.println("Grade    : " + getGrade());

    }

    String getName() {
        return nm;
    }

    int getRollNo() {
        return rollNo;
    }

    int scored(int i) {
        return s[i];
    }
}

public class Third_HandsOn {

    public static void main(String[] args) {

        Student[] batch = new Student[50];
        int n = 6;

        batch[0] = new Student(101, "Aman ", new int[] { 88, 76, 91, 83, 79 });
        batch[1] = new Student(102, "Priya ", new int[] { 55, 62, 49, 58, 61 });
        batch[2] = new Student(103, "Raj ", new int[] { 92, 88, 95, 90, 97 });
        batch[3] = new Student(104, "Karan ", new int[] { 35, 28, 42, 30, 38 });
        batch[4] = new Student(105, "Divya ", new int[] { 72, 68, 74, 70, 66 });
        batch[5] = new Student(106, "Simran ", new int[] { 81, 79, 85, 88, 76 });

        int[][] grid = new int[5][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 5; j++)
                grid[j][i] = batch[i].scored(j);

        String[] subNames = { "Maths", "Physics", "Chem", "English", "CS" };

        System.out.println("  " + Student.college);

        for (int i = 0; i < n; i++)
            batch[i].displayDetails();

        // class topper
        int top = 0;
        for (int i = 1; i < n; i++)
            if (batch[i].calculateTotal() > batch[top].calculateTotal())
                top = i;

        System.out.println("CLASS TOPPER  : " + batch[top].getName()
                + "  (" + batch[top].calculateTotal() + "/500)");

        // subject toppers
        System.out.println("\nSUBJECT TOPPERS:");
        for (int j = 0; j < 5; j++) {
            int bst = 0;
            for (int i = 1; i < n; i++)
                if (grid[j][i] > grid[j][bst])
                    bst = i;
            System.out.println("  " + subNames[j] + " : " + batch[bst].getName()
                    + "  (" + grid[j][bst] + ")");
        }

        // distinction
        int dist = 0;
        for (int i = 0; i < n; i++)
            if (batch[i].calculateAverage() > 75)
                dist++;
        System.out.println("\nDistinction (avg > 75) : " + dist);

        // grade split
        int ga = 0, gb = 0, gc = 0, gf = 0;
        for (int i = 0; i < n; i++) {
            char g = batch[i].getGrade();
            if (g == 'A')
                ga++;
            else if (g == 'B')
                gb++;
            else if (g == 'C')
                gc++;
            else
                gf++;
        }

        System.out.println("\nGRADE SPLIT:");
        System.out.println("  A  (>80)  : " + ga);
        System.out.println("  B (60-80) : " + gb);
        System.out.println("  C (40-60) : " + gc);
        System.out.println("  F  (<40)  : " + gf);
    }
}