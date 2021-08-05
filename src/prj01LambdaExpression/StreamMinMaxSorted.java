package prj01LambdaExpression;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class StreamMinMaxSorted {
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {
        ArrayList<Employee> staff = loadStaffFromFile();
        staff.stream().sorted(Comparator.comparing(Employee::getSalary)).forEach(System.out::println);

        System.out.println();

        Optional<Employee> optional = staff.stream().max(Comparator.comparing(Employee::getSalary));
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }

        System.out.println("show all employees with max salary");
        staff.stream().filter(employee -> employee.getSalary().compareTo(optional.get().getSalary()) == 0).forEach(System.out::println);

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
