package com.bbear.example.test.thread;

import com.bbear.example.util.productAndConsume.Main;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author junxiongchen
 * @date 2018/01/04
 */
public class ThreadJoin {
    public static void main(String[] args) {
       /* ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit();*/
    }
}
class Thread implements Runnable {
    @Override
    public void run() {

    }
}
class Thread1 implements  Runnable{
    @Override
    public void run() {

    }
}
class Thread2 implements  Runnable{
    @Override
    public void run() {
        Thread1 thread1 = new Thread1();
        System.out.println("t3");
    }
}
