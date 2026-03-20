import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class LazyOpsExp {

    public static void main(String[] args){

        List<String> names = Arrays.asList("Mahee", "Akshit", "Ram", "Shyam");

        Stream<String> stream = names.stream()
                .filter(
                        name -> {
                            System.out.println("Filtering: " + name);
                            return name.length() > 3;
                        }
                );

        System.out.println("Before Terminal Operation");

        List<String> list = stream.collect(java.util.stream.Collectors.toList());
        System.out.println("After Terminal Operation");
        System.out.println(list);


    }
}
