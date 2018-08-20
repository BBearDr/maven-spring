package com.bbear.example.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author junxiongchen
 * @date 2018/03/05
 */
public class JustTest {
    private JustTest justTest = new JustTest();
    public JustTest() {
        this.justTest = new JustTest();
    }

    public static void main(String[] args)  {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        map.put(3, 3);
        map.put(4, 4);
        Set<Map.Entry<Integer, Integer>> map1 = map.entrySet();
        for (Map.Entry<Integer, Integer> a : map1) {
            System.out.println(a.getKey());
            System.out.println(a.getValue());
        }
        for (Integer key : map.keySet()) {
         //   System.out.println(key);
        }
        for (Integer values : map.values()) {
           // System.out.println(values);
        }
    }
    private static boolean check(){
        boolean result = false;
        final ExecutorService exec = Executors.newFixedThreadPool(1);
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Thread.sleep(2000);
                System.out.println("123");
                return false;
            }
        };
        Future<Boolean> future = exec.submit(callable);
        try {
            result = future.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            result = false;
        } catch (ExecutionException e) {
            e.printStackTrace();
            result = false;
        } catch (TimeoutException e) {
            e.printStackTrace();
            result = false;
        }
        exec.shutdown();
        return result;
    }
}