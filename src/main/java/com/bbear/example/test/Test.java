package com.bbear.example.test;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.collect.ImmutableList;

/**
 * @author junxiongchen
 * @date 2018/10/08
 */
public class Test {

    public static void main(String[] args) {

        Test test = new Test();

        test.tes(() -> test.getTest());
        System.out.println(1);

    }

    public void tes(Callable<String> t1) {
        try {
            String call = t1.call();
            System.out.println("tes:" + call);
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public String getTest() {
        System.out.println("this is test");
        return "OK";
    }

}
