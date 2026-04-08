import java.util.*; 
import java.util.function.*; 
import java.util.stream.*; 
class Employee { 
int id; 
String name; 
double salary; 
String department; 
Employee(int id, String name, double salary, String department) 
{ 
} 
this.id = id; 
this.name = name; 
this.salary = salary; 
this.department = department; 
} 
public class eight_handon { 
public static void main(String[] args) { 
List<Employee> employees = Arrays.asList( 
new Employee(1, "Aman", 60000, "IT"), 
new Employee(2, "Riya", 45000, "HR"), 
new Employee(3, "John", 70000, "IT"), 
new Employee(4, "Sara", 50000, "Finance") 
); 
        // Predicate 
        Predicate<Employee> highSalary = e -> e.salary > 50000; 
 
        // Function 
        Function<Employee, String> toUpperName = e -> 
e.name.toUpperCase(); 
 
        // Consumer 
        Consumer<Employee> printEmployee = e -> 
                System.out.println(e.name + " - " + e.salary); 
 
        // Filter 
        List<Employee> filtered = employees.stream() 
                .filter(highSalary) 
                .collect(Collectors.toList()); 
 
        // Map 
        List<String> names = filtered.stream() 
                .map(toUpperName) 
                .collect(Collectors.toList()); 
 
        // Reduce 
        double totalSalary = employees.stream() 
                .map(e -> e.salary) 
                .reduce(0.0, Double::sum); 
 
        double avgSalary = employees.stream() 
                .mapToDouble(e -> e.salary) 
                .average() 
                .orElse(0); 
 
        System.out.println("Filtered Employees:"); 
        filtered.forEach(printEmployee); 
 
        System.out.println("Uppercase Names: " + names); 
        System.out.println("Total Salary: " + totalSalary); 
        System.out.println("Average Salary: " + avgSalary); 
 
        // Grouping by department 
        Map<String, List<Employee>> grouped = 
                employees.stream() 
                        .collect(Collectors.groupingBy(e -> e.department)); 
 
        System.out.println("Grouped by Department: " + grouped); 
 
        // Highest paid employee 
        Employee maxSalaryEmp = employees.stream() 
                .max(Comparator.comparing(e -> e.salary)) 
                .orElse(null); 
 
        System.out.println("Highest Paid: " + maxSalaryEmp.name); 
    } 
} 