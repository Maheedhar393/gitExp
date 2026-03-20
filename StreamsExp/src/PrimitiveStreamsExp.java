import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class PrimitiveStreamsExp {

    public static void main(String[] args) {
        int[] numbers = {1,2,3,4,5,6,7,8,9,10};
        IntStream stream = Arrays.stream(numbers);

        System.out.println("IntStream with Range: "+IntStream.range(1, 5).boxed().collect(Collectors.toList()));
        //here we have added boxed() because toList doesn't accept primitive data types.


        System.out.println("IntStream with closed range: "+IntStream.rangeClosed(1,5).boxed().collect(Collectors.toList()));

        DoubleStream doubleStream = new Random().doubles(5);
        System.out.println("Random Doubles: "+doubleStream.boxed().toList());

        IntStream intStream = new Random().ints(5,10,20);
        System.out.println("Random Ints between 10 and 20: "+intStream.boxed().toList());

        System.out.println("Sum: "+stream.sum());
        System.out.println("Average: "+IntStream.range(1,10).average().orElse(0.0));
        System.out.println("Max: "+IntStream.range(1,10).max().orElse(-1));
        System.out.println("Min: "+IntStream.range(1,10).min().orElse(-1));



    }
}
