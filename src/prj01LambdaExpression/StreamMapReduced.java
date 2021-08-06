package prj01LambdaExpression;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class StreamMapReduced {
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {
        ArrayList<Employee> staff = loadStaffFromFile();
        System.out.println("show all employees with salary > 100000");

        staff.stream().filter(employee -> employee.getSalary() > 100000).forEach(System.out::println);

        staff.stream()
                .map(employee -> employee.getSalary())
                .filter(integer -> integer >= 100000)
                .reduce((s1, s2) -> s1 + s2)
                .ifPresent(System.out::println);


        System.out.println("\n\n\nprint all employees work start in 2017 with max salaries:");
        int maxSalaryIn2017 = 0;
        Optional<Employee> optional = staff.stream()
                .filter(employee -> employee.getWorkStart().getYear() == 2017 - 1900)
                .max(Comparator.comparing(Employee::getSalary));
        if(optional.isPresent()){
            maxSalaryIn2017 = optional.get().getSalary();
        }
        int finalMaxSalaryIn2017 = maxSalaryIn2017;
        System.out.println("maxSalary in 2017 = " + finalMaxSalaryIn2017);
        staff.stream()
                .filter(employee -> employee.getWorkStart().getYear() == 2017 - 1900)
                .filter(employee -> employee.getSalary() == finalMaxSalaryIn2017)
                .forEach(System.out::println);




    }

    private static ArrayList<Employee> loadStaffFromFile() {
        ArrayList<Employee> staff = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for (String line : lines) {
                String[] fragments = line.split("\t");
                if (fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                        fragments[0],
                        Integer.parseInt(fragments[1]),
                        (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}
