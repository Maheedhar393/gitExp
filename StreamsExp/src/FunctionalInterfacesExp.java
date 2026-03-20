import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class FunctionalInterfacesExp {

    public static void main(String[] args){

        //Predicate<T>, Here T is the input type for the predicate
        Predicate<Integer> isEven = x -> x % 2==0;
        System.out.println("5 is even or not: "+isEven.test(5));
        //Combining two predicates
        Predicate<String> startsWithM = x -> x.startsWith("M");
        Predicate<String> endsWithE = x -> x.endsWith("e");
        Predicate<String> res = startsWithM.and(endsWithE); //And operation using predicate
        System.out.println("Maheed starts with 'M' and ends with 'e': "+res.test("Maheed"));
        res = startsWithM.or(endsWithE); //Or operation using predicate
        System.out.println("Maheed starts with 'M' or ends with 'e': "+res.test("Maheed"));
        res = startsWithM.negate(); //negation operation using predicate
        System.out.println("Negating Maheed starts with 'M': "+res.test("Maheed"));

        System.out.println("--------------------Predicate ends--------------------");

        //Function<T, R>, Here T is the input type for the Function and R is the return type of the Function
        Function<Integer, Integer> doubleIt = x -> x*2;
        Function<Integer, Integer> tripleIt = x -> x*3;

        Function<Integer, Integer> resFun = doubleIt.andThen(tripleIt);
        System.out.println("The result of applying doubleit and tripleit from Function is: "+resFun.apply(10));
        resFun = doubleIt.compose(tripleIt); // same as andThen but tripleIt will be applied first and then doubleIt
        System.out.println("The result of applying doubleit and tripleit from Function is: "+resFun.apply(20));
        Function<Integer, Integer> testIdentity = Function.identity();
        System.out.println("Value obtained from Identity: "+testIdentity.apply(8));
        // Identity returns the Function object and when give apply() method on that object the same value will be returned.

        System.out.println("--------------------Function ends--------------------");

        //Consumer<T>, here Consumer consumes input values of type T and will not return anything
        Consumer<Integer> print = x -> System.out.println("The Integer is: "+x);
        print.accept(10);
        Consumer<Integer> con = print.andThen(x -> System.out.println("After is: "+x));
        con.accept(15);

        System.out.println("--------------------Consumer ends--------------------");

        //Supplier<T>, here Supplier will not take any input but returns the values of type T
        Supplier<Integer> sup = ()-> 10;
        System.out.println("The value that is returned from Supplier is: "+ sup.get());

        System.out.println("--------------------Supplier ends--------------------");

        //BiPredicate, BiFunction, BiConsumer
        BiPredicate<Integer, String> biPreTest = (x, y ) -> x+y.length() >= 2;
        System.out.println(biPreTest.test(10, "Mahee"));

        BiFunction<Integer, String, String> biFunTest = (x, y)-> x+y;
        System.out.println(biFunTest.apply(12, "Mahee"));

        BiConsumer<Integer, String> biConTest = (x, y ) -> System.out.println(x+y);
        biConTest.accept(11, "Mahee");


        UnaryOperator<Integer> un = x -> x * 2;
        System.out.println(un.apply(10));

        BinaryOperator<Integer> bi = (x, y )-> x+y;
        System.out.println(bi.apply(2,3));



    }
}
