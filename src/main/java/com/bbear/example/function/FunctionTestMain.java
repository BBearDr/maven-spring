package com.bbear.example.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author chenjunxiong <chenjunxiong@kuaishou.com>
 * Created on 2021-02-07
 *
 * 函数式编程
 */
public class FunctionTestMain {

    public static void main(String[] args) {
        Function<Integer, String> function = functionTest(new ArrayList<>());
        String apply = function.apply(123);
//        System.out.println(apply);

        consumerTest("abc", x -> System.out.println(x + "123"));

    }

    public static Function<Integer,String> functionTest(List<String> list) {
        Function<Integer,String> function = (x) -> String.valueOf(x) + list.get(1);
        return function;
    }

    public static <T> void consumerTest(T t, Consumer<T> consumer) {
        consumer.accept(t);
    }

    public String supplierTest() {
        Supplier<String> stringCallable = () -> {
            return "supplier";
        };
        return stringCallable.get();
    }
}
