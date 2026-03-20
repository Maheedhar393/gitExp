package StreamAndCollectorsExamples;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PartitioningAndSummaryStatistics {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};

        System.out.println(
                Arrays.stream(arr)
                        .boxed()
                        .collect(
                                Collectors.partitioningBy(
                                        x -> x % 2 == 0,
                                        Collectors.counting()
                                )
                        )
        );

//        System.out.println(
//                Arrays.stream(arr)      //returns IntStream and Collectors work with Object streams so this throws error
//                        .collect(       // Note that most of the Collectors methods won't work with primitive streams directly
//                                Collectors.partitioningBy(
//                                        x-> x % 2 == 0,
//                                        Collectors.counting()
//                                )
//                        )
//        );

        System.out.println(
                Arrays.stream(arr)
                        .boxed()
                        .collect(Collectors.partitioningBy(
                                x -> x % 2 == 0,
                                Collectors.summarizingInt( Integer::intValue)  //To unbox Integer to int and to perform the summary statistics operations
                        ))
        );

        //Use summarizingInt with Integer::intValue to unbox each Integer to int
        // and compute summary statistics (count, sum, min, average, max) in one pass


    }
}
