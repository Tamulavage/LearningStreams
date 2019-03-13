import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) throws IOException {

       // Test test = new Test();
        //test.streamTest();
        // test.intTestSum();
        //test.sortedAndFirst();
        // test.stringArraySortfilter();
        // test.averageUsingMaps();
        //  test.streamFromListFilter();
        // test.streamFromFile();
        // test.fileToList();
        // countRowsFromFile();
        //parseFromCSVFIle();
      //  ParseFromCSVStoreInHashMap();
       // reduceSum();
        reduceSumStatistic();

    }

    public Test() {

    }

    public void streamTest() {

        // Integer Stream
        IntStream
                .range(1, 10)
                //     .skip(5)
                .forEach(x ->
                {
                    System.out.println(x);
                });
        System.out.println();


    }

    public void intTestSum() {

        System.out.println(
                IntStream
                        .range(1, 5)
                        .sum());
        System.out.println();

    }

    public void sortedAndFirst() {

        Stream.of("Ava", "Aneri", "Alberto")
                .sorted()
                .findFirst()
                .ifPresent(System.out::println);

    }

    public void stringArraySortfilter() {

        String[] names = {"Bob", "Dylan", "Thomas"};
        Arrays.stream(names) // same as Stream.of(names)
                .filter(x -> x.startsWith("B"))
                .sorted()
                .forEach(System.out::println);
    }

    public void averageUsingMaps() {

        Arrays.stream(new int[]{2, 4, 6, 8, 10})
                .map(x -> x * x)
                .average()
                .ifPresent(System.out::println);
    }

    public void streamFromListFilter() {

        List<String> people = Arrays.asList("Bob", "Dylan", "Thomas");
        people
                .stream()
                .map(String::toLowerCase)
                .filter(x -> x.startsWith("b"))
                .forEach(System.out::println);
    }

    public void streamFromFile() throws IOException {

        Stream<String> bands = Files.lines(Paths.get("bands.txt"));
        bands
                .sorted()
                .filter(x -> x.length() > 13)
                .forEach(System.out::println);
        bands.close();
    }

    public void fileToList() throws IOException {

        List<String> band = Files.lines(Paths.get("bands.txt"))
                .filter(x -> x.toLowerCase().contains("test"))
                .collect(Collectors.toList());
        band.forEach(x -> System.out.println(x));
    }

    private static void countRowsFromFile() throws IOException {
        Stream<String> rows = Files.lines(Paths.get("data.txt"));
        int rowCount = (int) rows
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .count();
        System.out.println(rowCount + " rows. ");
        rows.close();
    }

    private static void parseFromCSVFIle() throws IOException {

        Stream<String> rows = Files.lines(Paths.get("data.txt"));
        rows
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .forEach(x -> System.out.println(x[0] + " " + x[1] + " " + x[2]));
        rows.close();

    }

    private static void ParseFromCSVStoreInHashMap() throws IOException {
        Stream<String> row = Files.lines(Paths.get("data.txt"));

        Map<String, Integer> map = new HashMap<>();

        map = row
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .collect(Collectors.toMap(
                        x -> x[0],
                        x -> Integer.parseInt(x[1])));
        row.close();
        for(String key:map.keySet()) {
            System.out.println(key+ " " + map.get(key));
        }

    }

    public static void reduceSum() {

        double total = Stream.of(7.3, 1.5, 4.8)
                .reduce(0.0, (Double a, Double b) -> a+b);
        System.out.println("Total = " + total);
    }

    public static void reduceSumStatistic(){
        IntSummaryStatistics summary = IntStream.of(7, 2, 19, 88, 73, 4, 10)
                .summaryStatistics();
        System.out.println(summary);
    }
}
