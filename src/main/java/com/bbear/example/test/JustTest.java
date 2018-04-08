package com.bbear.example.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Runnable() {
            @Override
            public void run() {

                System.out.println("send result:" + 123);
            }
        });
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