import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TerminalOpsExp {


    public static void main(String[] args){


        List<Integer>  list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //1. collect
        System.out.println(list
                .stream()
                .skip(2)
                .collect(Collectors.toList()));

        //2. forEach
        list.stream().forEach(System.out::print);

        System.out.println();

        //3. reduce
        System.out.println(list
                .stream()
                .reduce(0, Integer::sum)
        );

        //4. count
        System.out.println(list
                .stream()
                .filter(x -> x % 2 == 0)
                .count());

        //5. anyMatch, allMatch, noneMatch
        System.out.println(list
                .stream()
                .anyMatch( x -> x == 6)
        );

        System.out.println(list
                .stream()
                .allMatch(x -> x < 11));

        System.out.println(list
                .stream()
                .noneMatch(x ->x > 10)
        );

        //6. findFirst, findAny
        System.out.println(list
                .stream()
                .findFirst()
                .get());

        System.out.println(list
                .stream()
                .findAny()
        .get());

        //Note:
        //All these anyMatch, allMatch, noneMatch, findFirst, findAny are short-circuiting operations.
        //Meaning they may not process all elements of the stream to produce a result.
        //For example, anyMatch can return true as soon as it finds a matching element without checking the rest.

        //Example
        String sentence = "Hello world";
        System.out.println(
                sentence
                        .chars()
                        .filter(x -> x == 'l')
                        .count()
        );

        // Note:
        // chars() method of String class returns an IntStream representing the sequence of characters in the string.
        // Each character is represented by its Unicode code point (an integer value). So here ascii value is being compared.
        //Tough x is integer they are just comparing with character 'l' whose ascii value is 108.

        //Note:
        //Stateful operations like sorted() and distinct() will require storing all elements of the stream in memory to perform their operations.
        //Stateless operations like filter() and map() can process each element independently without needing to store the entire stream.

        //7. toArray
        Integer[] arr = list
                .stream()
                .filter(x -> x % 2 != 0)
                .toArray(Integer[]::new);
        for( Integer i : arr){
            System.out.print(i+" ");
        }

        int[] arrp = {1,2,3,4,5,6,7,8,9,10};
        int [] evenArr = Arrays.stream(arrp)
                .filter(x -> x % 2 == 0)
                .toArray();
        for (int i : evenArr){
            System.out.print(i+" ");
        }

        //8. min, max

        System.out.println();
        System.out.println(list
                .stream()
                .max(Comparator.naturalOrder())
                .get());
        //For max operation if you reverse the order using (a,b) -> b.compareTo(a) then it will give minimum element
        //Because it will pick the last element in the stream according to the comparator provided
        System.out.println(list
                .stream()
                .min(Comparator.naturalOrder())
                .get());

        System.out.println();
        //9. forEachOrdered
        list.parallelStream().forEach(System.out::print);
        System.out.println();
        list.parallelStream().forEachOrdered(System.out::print);
        System.out.println();
        list.parallelStream().sequential().forEach(System.out::print);

    }
}
