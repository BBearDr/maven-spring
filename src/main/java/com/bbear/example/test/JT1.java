package com.bbear.example.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author junxiongchen
 * @date 2018/03/27
 */
public class JT1{
    private JT1() {
    }

    public static void main(String[] args) throws InterruptedException {
        JT1 test = new JT1();

        CountDownLatch latch = new CountDownLatch(1);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.submit(test.new MapOper(latch));
        }
        executorService.shutdown();

       /* Thread t1 = new Thread(test.new MapOper(latch));
        Thread t2 = new Thread(test.new MapOper(latch));
        Thread t3 = new Thread(test.new MapOper(latch));
        Thread t4 = new Thread(test.new MapOper(latch));
        Thread t5 = new Thread(test.new MapOper(latch));
        Thread t6 = new Thread(test.new MapOper(latch));

        t1.setName("Thread1");
        t2.setName("Thread2");
        t3.setName("Thread3");
        t4.setName("Thread4");
        t5.setName("Thread5");
        t6.setName("Thread6");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();*/

        System.out.println("thread already start, sleep for a while...");

        Thread.sleep(1000);
        latch.countDown();
    }
    public class MapOper implements Runnable {

        CountDownLatch latch ;

        public MapOper(CountDownLatch latch) {
            this.latch = latch;
        }
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" Sync Started!"+System.currentTimeMillis());
        }
    }
}
