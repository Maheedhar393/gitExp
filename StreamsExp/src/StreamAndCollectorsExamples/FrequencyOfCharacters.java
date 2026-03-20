package StreamAndCollectorsExamples;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyOfCharacters {
    public static void main(String[] args) {

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
//        System.out.println(
//                Arrays.stream(expSt.toCharArray())
//
//        );
        //This won't work because Arrays.stream() doesn't have an overload for char[],
        //Arrays.stream() works with Object arrays and primitive type arrays like int[], long[], double[] only.
        //If you want to create a stream of characters from a String, you can use the chars() method as shown above.



    }
}
