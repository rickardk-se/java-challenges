package Employee;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("Anna", "Svensson", LocalDate.of(2016, 5, 10)));
        employees.add(new Employee("Erik", "Johansson", LocalDate.of(2012, 9, 3)));
        employees.add(new Employee("Karin", "Nilsson", LocalDate.of(2018, 2, 20)));
        employees.add(new Employee("Lars", "Andersson", LocalDate.of(2009, 11, 1)));
        employees.add(new Employee("Sara", "Larsson", LocalDate.of(2021, 6, 15)));

        Main demo = new Main();
        System.out.println("Employees:");
        demo.printEmployees(employees);
        System.out.println("\nSorted by hire date:");
        demo.printSortedEmployees(employees);
    }

    public record Employee(String firstName, String lastName, LocalDate hireDate) {
        public String toString() {
            int currentYear = LocalDate.now().getYear();
            int yearsWorked = currentYear - hireDate.getYear();
            return "%s %s has been an employee for %d years".formatted(firstName, lastName, yearsWorked);
        }
    }

    public void printEmployees(List<Employee> employees) {
        // Local class
        class EmployeePrinter {
            Employee employee;

            public EmployeePrinter(Employee employee) {
                this.employee = employee;
            }

            public void print(Employee employee) {
                System.out.println(employee);
            }
        }

        for (Employee employee : employees) {
            EmployeePrinter printer = new EmployeePrinter(employee);
            printer.print(employee);
        }

    }

    public void printSortedEmployees(List<Employee> employees) {
        // Anonymous class
        employees.sort(new java.util.Comparator<>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return e1.hireDate.compareTo(e2.hireDate);
            }
        });

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

}
