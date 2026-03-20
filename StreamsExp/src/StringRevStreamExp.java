public class StringRevStreamExp {

    public static void main(String[] args) {
        String str = "HelloWorld";

        System.out.println(
                str
                        .chars()
                        .filter(x -> x == 'l')
                        .count()
        );

    }
}
