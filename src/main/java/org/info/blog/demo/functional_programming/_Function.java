package org.info.blog.demo.functional_programming;


import java.util.function.Function;
import java.util.function.Predicate;

public class _Function {
    public static void main(String[] args) {

        Function<Integer, Integer> crease = y -> y = y + 1;
        int count = 0;
        crease.apply(count);

        int x = crease(count, y -> y = y - 1);
        System.out.println(x);

        Function<Integer, Integer> funv = xy -> xy * 2;
        System.out.println(funv.apply(6));

        int age = 18;
        System.out.println(belowAgeCheker(18, integer -> integer < 21 ? true : false));


    }

    static int crease(int count, Function<Integer, Integer> function) {
        return function.apply(count);
    }

    static String belowAgeCheker(int age, Predicate<Integer> predicate) {
        return predicate.test(age) ?
                "Below age" :
                "Good to go";
    }
}
