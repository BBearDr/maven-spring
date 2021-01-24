package com.bbear.example.function;

import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;

/**
 * @author chenjunxiong <chenjunxiong@kuaishou.com>
 * Created on 2021-01-22
 * 函数编程测试
 */
public class IFunctionTest1 implements IFunction<String, Integer> {


    @Override
    public String print(Integer integer) {
        return "这是输出的：" + integer;
    }

    public void test() {
        Function<Integer, String> function = (x) -> String.valueOf(x);
    }

    Predicate<String> predicate = (x) -> {
        if (StringUtils.equals(x, "22")) {
            return true;
        }
        return false;
    };

}
