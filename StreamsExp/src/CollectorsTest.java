import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorsTest {

    public static void main(String[] args) {
        List<String> ls = Arrays.asList("Mahee", "Akshit", "Ram", "Shyam", "Sita", "Geeta", "Mahee", "Akshit", "Ram");
        System.out.println(
                ls.stream()
                        .collect(Collectors
                                .groupingBy(
                                      String::length
                                ))
        );

        System.out.println(
                ls.stream()
                        .collect(Collectors
                                .groupingBy(
                                        Function.identity(),
                                        Collectors.counting()
                                ))
        );

        List<Integer> lsInt = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        System.out.println(
                lsInt.stream()
                        .collect(Collectors.partitioningBy(
                                x -> x%2==0
                        ))
        );

        Map<String, Integer> items = Map.of("apple", 10, "banana", 20, "orange", 30);
        System.out.println(items.values()
                .stream()
                .collect(Collectors.reducing(0, (a, b) ->a + b))
        );


        //toMap type a: identity function as key mapper and length of string as value mapper both the parameters are functions,
        //here is each element from the stream is passed to keymapper so the key is decided, the same element will be passed to the valuemapper to calculate the length and store in the value

        System.out.println(
                ls.stream().
                        collect(Collectors.toSet())
                        .stream()
                        .collect(Collectors.toMap(
                                Function.identity(),
                                String::length
                        ))

        );

        //toMap type b: if there are duplicate keys then we can provide a merge function to decide how to handle duplicates
        //Here first parameter is key mapper(Function), second is value mapper(Function) and third is merge function(BinaryOperator)
        System.out.println(
                ls.stream()
                        .collect(Collectors.toMap(
                                Function.identity(),
                                v -> 1,
                                (existing, newOne) -> existing + newOne
                        ))
        );

        String expSt = "Java Streams Rocks";
        System.out.println("Res: "+
                expSt.toLowerCase().replaceAll("\\s", "")  .chars().mapToObj(c -> (char)c)  //
                        .collect(Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,
                                Collectors.counting()
                        ))
        );

            //        \\s+ means: a regualar expression
            //        \\ → escape for Java string (so regex sees \s)
            //        \s → any whitespace
            //        + → one or more occurrences

        System.out.println(
                String.valueOf(expSt.toLowerCase().chars()
                         .mapToObj(c -> (char)c)
                         .filter(x -> x != ' ' )
                         .collect(Collectors.groupingBy(
                                 Function.identity(),
                                 LinkedHashMap::new,
                                 Collectors.counting()
                         )
         )));


    }



}
