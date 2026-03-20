import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntermediateOpsExp {

    public static void main(String[] args){

        //Intermediate operations like filter, map, flatmap, distinct, sorted are lazy, they won't be executed until terminal operator is being invoked
        //1. filter
        List<String> list = Arrays.asList("Mahee", "Akshit", "Ram", "Shyam");
        long l = list.stream().filter(x -> x.length() % 2 == 0).count();
        System.out.println(l);


        //2. map
        System.out.println(
                list.stream()
                        .map(String::toUpperCase)
                        .collect(Collectors.toList())
        );

        //3. distinct
        System.out.println(
                list.stream()
                        .distinct()
                        .collect(Collectors.toList())
        );

        //4. sorted
        System.out.println(
                list.stream()
                        .sorted((a,b)->
                               a.compareTo(b))
                        .collect(Collectors.toList())
        );

        //5. limit
        System.out.println(
                list.stream()
                        .limit(2)
                        .toList()
        );

        //6. skip
        System.out.println(
                list.stream()
                        .skip(2)
                        .toList()
                        .get(0)
        );


        //7. peek
        //performs intermediate operation on each element as it is consumed from the stream like for debugging purpose
        //it is mainly used for performing operations like logging, printing, etc without modifying the stream

        System.out.println(
                list.stream()
                        .peek(x -> System.out.println("Processing: " + x))
                        .map(String::toUpperCase)
                        .collect(Collectors.toList())
        );

        //8. flatMap
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("Banana", "Apple"),
                Arrays.asList("Cat", "Dog"),
                Arrays.asList("Ear", "Fan")
        );
        System.out.println(
                listOfLists
                        .stream()
                        .flatMap(x -> x.stream()) // Here we are accumulating all inner lists into a single unified stream
                        .collect(Collectors.toList())
        );

        List<String> sentences = Arrays.asList(
                "Hello World", "Java Streams");
        System.out.println(
                sentences
                        .stream()
                        .flatMap(x ->Arrays.stream(x.split(" ")))
                        .map(String::toUpperCase)
                        .collect(Collectors.toList()));

       Stream<String> str = list.stream();
       str.forEach(System.out::println);
       //List<String> newList = str.collect(Collectors.toList()); // This will throw IllegalStateException because stream has already been operated upon or closed

    }
}
