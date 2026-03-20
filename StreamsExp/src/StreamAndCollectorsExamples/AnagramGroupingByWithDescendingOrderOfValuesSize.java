package StreamAndCollectorsExamples;

import java.security.Signature;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnagramGroupingByWithDescendingOrderOfValuesSize {
    public static void main(String[] args) {
        List<String> words = List.of("Eat", "tea", "Tan", "ate", "nat", "bat", "Tab", "beAt");

        System.out.println(
                words.stream()
                        .collect(Collectors.groupingBy(
                                word ->{
                                    char arr[] = word.toLowerCase().toCharArray();
                                    Arrays.sort(arr);
                                    return  new String(arr);
                              },
                                LinkedHashMap::new,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream()
                                                .sorted(Comparator.naturalOrder())
                                                .collect(Collectors.toList()))

                        ))
                        .entrySet()
                        .stream()
                        .sorted((a, b) -> b.getValue().size() - a.getValue().size())
                        .collect(Collectors.toList())
        );

        System.out.println(
                words.stream()
                        .collect(Collectors.groupingBy(
                                word ->{
                                    char arr[] = word.toLowerCase().toCharArray();
                                    Arrays.sort(arr);
                                    return  new String(arr);
                                },
                                LinkedHashMap::new,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream()
                                                .sorted(Comparator.naturalOrder())
                                                .collect(Collectors.toList())
                                )
                        ))
                        .entrySet()
                        .stream()
                        .sorted((a, b) -> b.getValue().size() - a.getValue().size())
                        .filter(x -> x.getValue().size() >= 3)
                        .collect(Collectors.toList())
        );



    }
}
