import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorsExp {
    public static void main(String[] args) {
        List<Integer> list = List.of(1,2,3,4,5,6,7,8,9,10,5,7,3,5,6);

        //1. Collecting to a List
        List<Integer> evenNumbers = list.stream()
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even Numbers: " + evenNumbers);

        //2. Collecting to a Set
        Set<Integer> intSet = list.stream()
                .collect(Collectors.toSet());
        System.out.println("Set of Integers: " + intSet);

        //3. Collecting to a Specific Collection (e.g., TreeSet)
        LinkedList<Integer> intLList = intSet.stream()
                .collect(Collectors .toCollection(LinkedList::new));
        System.out.println("LinkedList of Integers: " + intLList);


        //4. Joining Strings
        //Concatenates the stream elements into a single String
        List<String> strList = List.of("Mahee", "Akshit", "Ram", "Shyam");
        String joinedStr = strList.stream()
                .map(s -> "( \""+s+"\" )")
                .collect(Collectors.joining(", ", "{", "}"));
        System.out.println("Joined String: " + joinedStr);

        //5. Summarizing Data
        //Generates statistical summary  like (count, sum, min, average, max)

        IntSummaryStatistics stats = list.stream()
                .collect(Collectors.summarizingInt(x -> x));
        System.out.println("Statistical Summary: " + stats);
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Average: " + stats.getAverage());
        System.out.println("Max: " + stats.getMax());


        //6. Calculating Average
        double average = list.stream()
                .collect(Collectors.averagingInt(x -> x));
        System.out.println("Average: " + average);

        //7. Grouping Data
        //There are 3 types of groupingBy methods
        //a. groupingBy(Function<? super T,? extends K> classifier) here classifier is a function that determines the keys in the resulting map
        //b. groupingBy(Function<? super T,? extends K> classifier, Collector<? super T,A,D> downstream) here downstream is a collector that is applied to the values associated with each key
        //c. groupingBy(Function<? super T,? extends K> classifier, Supplier<M> mapFactory, Collector<? super T,A,D> downstream) here mapFactory is a supplier that provides
        // a new empty Map into which the results will be inserted(For example we can use TreeMap to have sorted keys by giving TreeMap::new as mapFactory)
        strList = List.of("Mahee", "Akshit", "Ram", "Shyam");
        //Type a
        System.out.println("Type a: "+
                strList.stream()
                        .collect(Collectors.groupingBy(
                                String::length
                        ))
        );

        //Type b
        System.out.println("Type b: "+
                strList.stream()
                        .collect(Collectors.groupingBy(
                                String::length,
                                Collectors.maxBy((a,b) -> a.compareTo(b))

                        ))
        );
        //If you don't want Optional as value you can use collectingAndThen to transform the result like shown below
        System.out.println("Type b with collectingAndThen: "+
                strList.stream()
                        .collect(Collectors.groupingBy(
                                String::length,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy((a,b) -> a.compareTo(b)),
                                        opt -> opt.orElse("") //here opt -> opt.orElse("") transforms Optional<String> to String hence it is a Function and orElse method returns the value if present otherwise returns empty string
                                )
                        )));


        //Type c
        System.out.println("Type c: "+
                strList.stream()
                        .collect(Collectors.groupingBy(
                                String::length,
                                TreeMap::new,
                                Collectors.maxBy((a,b) -> a.compareTo(b))

                        ))
        );

        //8. Partitioning Data
        //Partitions the stream elements into two groups based on a predicate
        System.out.println(
                strList.stream()
                        .collect(Collectors.partitioningBy(
                                x -> x.length() >= 5
                        ))
        );

        //9. Mapping Collector
        //Applies a mapping function to the stream elements before collecting them
        System.out.println(
                strList.stream()
                        .collect(Collectors.mapping(x-> x.toUpperCase(), Collectors.toList()))
        );


        //10. Reducing Collector
        //Performs a reduction operation on the stream elements
        System.out.println(
                list.stream()
                        .collect(Collectors.reducing(0, (a,b) -> a + b))
        );

        //11. toMap
        System.out.println(
               strList.stream()
                       .collect(Collectors.toMap(
                               s -> s,
                               String::length
                       ))
        );





    }
}
