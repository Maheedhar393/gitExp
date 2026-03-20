import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class Employee{

    private String name;
    private String department;
    private int salary;

    public Employee(String name, String department, int salary){
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}

public class StreamsExamplesExp {
    public static void main(String[] args) {
        Employee emp1 = new Employee("Mahee", "IT", 75000);
        Employee emp2 = new Employee("Akshit", "HR", 50000);
        Employee emp3 = new Employee("Ram", "IT", 80000);
        Employee emp4 = new Employee("Shyam", "Finance", 60000);
        Employee emp5 = new Employee("Sita", "HR", 55000);


        List<Employee> employees = List.of(emp1, emp2, emp3, emp4, emp5);


        Map<String, Integer> maxSalaryByDept = employees.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(
                                                Comparator.comparingDouble(Employee::getSalary)
                                        ),
                                        empOpt -> empOpt.map(Employee::getSalary)
                                                .orElse(0)
                                )
                        )
                );
        System.out.println(maxSalaryByDept);


        System.out.println(
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.maxBy((a, b) -> a.getSalary() - b.getSalary())
                        ))
        );




    }
}
