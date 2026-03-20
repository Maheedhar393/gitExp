package StreamAndCollectorsExamples;


import java.util.Comparator;
import java.util.List;
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
        return "\n"+"Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}'+'\n';
    }
}
public class HighestSalaryEmployeeList {

    public static void main(String[] args) {

        Employee emp1 = new Employee("Mahee", "IT", 75000);
        Employee emp2 = new Employee("Akshit", "HR", 50000);
        Employee emp3 = new Employee("Ram", "IT", 80000);
        Employee emp4 = new Employee("Shyam", "Finance", 60000);
        Employee emp5 = new Employee("Sita", "HR", 55000);
        Employee emp6 = new Employee("Geeta", "Finance", 70000);
        Employee emp7 = new Employee("Laxman", "IT", 80000);
        Employee emp8 = new Employee("Karan", "HR", 50000);
        Employee emp9 = new Employee("Arjun", "Finance", 60000);
        Employee emp10 = new Employee("Duryodhan", "IT", 90000);
        Employee emp11 = new Employee("Shakuni", "HR", 65000);
        Employee emp12 = new Employee("Bheem", "Finance", 75000);
        Employee emp13 = new Employee("Nakula", "IT", 72000);
        Employee emp14 = new Employee("Sahadev", "HR", 48000);
        List<Employee> employees = List.of(emp1, emp2, emp3, emp4, emp5, emp6, emp7, emp8, emp9, emp10, emp11, emp12, emp13, emp14);

        System.out.println(employees
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                 Collectors.collectingAndThen(
                                         Collectors.toList(),
                                         x -> x.stream()
                                                 .sorted((a,b)-> b.getSalary() - a.getSalary())
                                                 .limit(3)
                                                 .toList()
                                 )
                        )
                ));

    }


}
