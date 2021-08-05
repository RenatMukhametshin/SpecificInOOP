package prj01LambdaExpression;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamExample {
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {
        ArrayList<Employee> staff = loadStaffFromFile();
        staff.forEach(System.out::println);

        Stream<Employee> stream = staff.stream();
        //show only emloyees with >= 100000 salary
        System.out.println("\nSalaries >= 100000");
        stream.filter(employee -> employee.getSalary() >= 100000).forEach(System.out::println);

        //create stream and show only even number%2==0
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers.filter(integer -> integer % 2 == 0).forEach(System.out::println);

        System.out.println("\n infinity stream");
        //Stream.iterate(1, n -> n + 1).forEach(System.out::println);

        System.out.println("\n generate stream");
        //Stream.generate(()->"aaa").forEach(System.out::println);

        Integer[] numbersInt = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Arrays.stream(numbersInt).forEach(integer -> System.out.println(integer * 2));

        "jdkjavalanguage".chars().forEach(System.out::println);


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
