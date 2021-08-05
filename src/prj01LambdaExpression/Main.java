package prj01LambdaExpression;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {
        ArrayList<Employee> staff = loadStaffFromFile();

        Collections.sort(staff, (employee1, employee2) -> employee1.getSalary().compareTo(employee2.getSalary()) );

        System.out.println("\nSorted salary");
        for (Employee employee : staff) {
            System.out.println(employee);
        }

        System.out.println("\nSorted salary :: name");
        Collections.sort(staff,
                (employee1, employee2) ->
                        {
                            int resultCompareSalary = employee1.getSalary().compareTo(employee2.getSalary());
                            int resultCompareName = employee1.getName().compareTo(employee2.getName());
                            if ( resultCompareSalary == 0)
                                return resultCompareName;
                            else
                                return resultCompareSalary;
                        }
        );

        for (Employee employee : staff) {
            System.out.println(employee);
        }

        System.out.println("\nSorted name");
        Collections.sort(staff, Comparator.comparing(Employee::getName));
        for(Employee employee : staff){
            System.out.println(employee);
        }


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