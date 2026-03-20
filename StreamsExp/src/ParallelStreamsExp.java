import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ParallelStreamsExp {

    public static void main(String[] args) {

        //<--------------------------Parallel Streams with independent task-------------------------->

        // In parallel streams workload is distributed among multiple threads
        // We use parallel stream to process large data sets faster by utilizing multiple CPU cores
        // In parallel stream multiple threads process the data concurrently
        List<Integer> list = Stream.iterate(1, x -> x + 1)
                .limit(20000)
                .toList();

        long start = System.currentTimeMillis();
        List<Long> ls = list.stream().map(ParallelStreamsExp::giveFactorial).toList();
        long end = System.currentTimeMillis();
        System.out.println("Time taken in sequential stream: " + (end - start) + " ms");

        long startP = System.currentTimeMillis();
        List<Long> lsP = list.parallelStream().map(ParallelStreamsExp::giveFactorial).toList();
        lsP = list.parallelStream().map(ParallelStreamsExp::giveFactorial).sequential().toList();
        // we can use sequential() method to convert parallel stream back to sequential stream if needed
        long endP = System.currentTimeMillis();
        System.out.println("Time taken in parallel stream: " + (endP - startP) + " ms");

        //Parallel streams are not always faster than sequential streams, especially for small data sets or simple operations due to the overhead of managing multiple threads.
        //Parallel streams are most effective for CPU -intensive operations on large data sets where the tasks are independent
        //They may add overhead for simple tasks or small data sets.

        //Here is the example of parallel streams with dependent tasks [3, 5, 6, 10, 15]
        List<Integer> listDep = Arrays.asList(1, 2, 3, 4, 5);

        AtomicInteger sum = new AtomicInteger(0);
        List<Integer> cumulativesum = listDep.parallelStream()
                       .map(sum::addAndGet).toList();

       System.out.println("Cumulative sum with parallel stream (may be incorrect due to dependencies): " + cumulativesum);

       //Note: In the above example, the cumulative sum calculation is dependent on the order of processing. Beacause each element's result depends on the previous elements' results.
        //The factorial example is independent because the calculation of each factorial does not depend on any other element.


    }

    private  static long giveFactorial(long num){
        long res = 1;
        for(long i = 1; i <= num; i++) {
            res = res * i;
        }
        return  res;
    }

}
