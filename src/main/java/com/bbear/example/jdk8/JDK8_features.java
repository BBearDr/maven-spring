package com.bbear.example.jdk8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * JDK8 新特性
 * @author junxiongchen
 * @date 2018/08/09
 */
public class JDK8_features {
    public List<Integer> list = new ArrayList<Integer>();

    /**
     * Lambda表达式
     */
    @Test
    public void testLambda() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.forEach(System.out ::println);
        list.forEach(e -> System.out.println("方式二：" +e));
    }
}
