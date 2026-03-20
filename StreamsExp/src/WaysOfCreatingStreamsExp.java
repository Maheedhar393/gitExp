import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class WaysOfCreatingStreamsExp {

    public static void main(String[] arg){

        //1. From Collections
        List<Integer> ls = Arrays.asList(1,2,3,4,5);
        Stream<Integer> lsSt = ls.stream();

        //2. From Arrays
        String[] array = {"a", "b", "c"};
        Stream<String> arSt = Arrays.stream(array);

        int[] ar = {1,3,4,5,6,8,9,10};
        int [] asLs = Arrays.stream(ar).filter(x -> x%2 ==0).toArray();

        //3.Using Stream.of()
        Stream<String> stream2 = Stream.of("a", "b");

        //4. Using Infinite Streams
        Stream<Integer> generate = Stream.generate(()-> 1).limit(3); //creates a stream with all 1s of size 3, here we are using Supplier Functional interface
        Integer[] a = Stream.iterate(1, x -> x + 1).limit(5).toArray(Integer[]::new);
        for( Integer i : a){
            System.out.println(i);
        }
    }
}
