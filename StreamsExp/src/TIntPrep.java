import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class EmployeeInt {
    String name;
    int age;
    String department;
    double salary;

    public EmployeeInt(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class TIntPrep {

    public static void main(String[] args) {

        EmployeeInt emp1 = new EmployeeInt("Mahee", 30, "IT", 50000);
        EmployeeInt emp2 = new EmployeeInt("Akshit", 25, "HR", 40000);
        EmployeeInt emp3 = new EmployeeInt("Ram", 35, "IT", 60000);
        EmployeeInt emp4 = new EmployeeInt("Shyam", 28, "Finance", 45000);
        EmployeeInt emp5 = new EmployeeInt("Sita", 32, "HR", 42000);
        EmployeeInt emp6 = new EmployeeInt("Geeta", 29, "Finance", 47000);
        EmployeeInt emp7 = new EmployeeInt("Mahee", 30, "IT", 50000);

        List<EmployeeInt> employeeInts = List.of(emp1, emp2, emp3, emp4, emp5, emp6, emp7);

        System.out.println(
                employeeInts.stream()
                        .collect(Collectors.groupingBy(
                                EmployeeInt::getDepartment
                        ))
        );

        List<Integer> list = List.of(1,4,2,2,3,3);
        System.out.println(
                list.stream()
                        .collect(Collectors.groupingBy(
                                Function.identity()  //1
                        )).entrySet().stream()
                        .filter(e -> e.getValue().size() > 1)
                        .map(e -> e.getKey()) //4
                        .collect(Collectors.toList())

        );


                employeeInts.stream()
                        .sorted((a, b) -> (int)(b.getSalary()-a.getSalary()))  //2
                        .forEach(System.out::println);

        System.out.println("------------------------");
        employeeInts.stream()
                .sorted((a, b) -> (int)(b.getSalary()-a.getSalary()))
                .limit(2)
                .skip(1)
                .forEach(System.out::println);

        System.out.println(
                list.stream()
                        .collect(
                                Collectors.toMap(
                                        Function.identity()
                                        ,x -> (x+"").length()  //3
                                        ,(a, b) -> a
                                )
                        )
        );

        String str = "Maheedhar";

        System.out.println(
                str.chars()
                        .mapToObj(c -> (char)c)  //5
                        .collect(Collectors.groupingBy(
                                Function.identity()
                        ))
                        .entrySet()
                        .stream()
                        .filter(e -> e.getValue().size() <= 1)
                        .map(e -> e.getKey())
                        .collect(Collectors.toList())
        );

        System.out.println(
                str.chars()
                        .mapToObj(c -> String.valueOf((char)c)) //6
                        .reduce("",(a,b)-> b+a)
        );
    }
}


