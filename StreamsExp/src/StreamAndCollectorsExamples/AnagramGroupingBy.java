package StreamAndCollectorsExamples;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AnagramGroupingBy {

    public static void main(String[] args) {

        List<String> words = List.of("eat","tea","tan","ate","nat","bat");
        System.out.println(
                String.valueOf(words.stream()
                      .collect(Collectors.groupingBy(
                        word ->{
                            char arr[] = word.toCharArray();
                            Arrays.sort(arr);
                            return new String(arr);
                        },
                              LinkedHashMap::new,
                        Collectors.toList()
                ))
        ));
    }
}
