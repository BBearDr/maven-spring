package com.bbear.example.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author junxiongchen
 * @date 2018/03/05
 */
public class JustTest {
    public static void main(String[] args)  {
        for (int i = 0; i < 5; i++) {
            new Thread(new T1()).start();
            new Thread(new T2()).start();
        }
    }
    private static void help(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            try {
                String str = "ad";
                int n = Integer.parseInt(str);
            } catch (Exception e) {
                System.out.println("error");
            }
        }
        System.out.println("123");
    }
    private static void test(String str) throws Exception {
        if ("".equals(str)) {
            throw new Exception("throw test");
        }
    }
}
class T1 implements Runnable{

    @Override
    public void run() {
        System.out.println("class T1:"+Thread.currentThread().getName());
    }
}
class  T2 implements Runnable{

    @Override
    public void run() {
       /* try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("class T2："+Thread.currentThread().getName());
    }
}