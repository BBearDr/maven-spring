package com.bbear.example.thread;

import javax.sql.rowset.serial.SerialStruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 同时启动多个线程，并记录消耗时间
 *
 * @author junxiongchen
 * @date 2018/04/26
 */
public class CountDownLatchDemo {
    private int nThread;
    private CountDownLatch startlatch;
    private CountDownLatch endLatch;

    private Map<Integer, Integer> map = new HashMap<>();

    public CountDownLatchDemo(int nThread, CountDownLatch startlatch, CountDownLatch endLatch) {
        this.nThread = nThread;
        this.startlatch = startlatch;
        this.endLatch = endLatch;
    }

    public static void main(String[] args) {
        int nThread = 5;
        //?为什么是1
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(nThread);
        new CountDownLatchDemo(nThread, startLatch, endLatch).timeTask();
    }
    private long timeTask() {
        for (int i = 0; i < nThread; i++) {
            Thread thread = new Thread(new Work(i));
            thread.start();
        }
        long start = System.currentTimeMillis();
        //启动线程计数
        startlatch.countDown();
        try {
            endLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //所有线程结束
        long end = System.currentTimeMillis();
        System.out.println("总耗时 ：" + (end - start));
        System.out.println(map.toString());
        return end - start;
    }
     class Work implements Runnable{
         private int i;

         public Work(int i) {
             this.i = i;
         }

         @Override
        public void run() {
            try {
                //等待所有线程启动完成
                startlatch.await();
                Random random = new Random();
                int num = random.nextInt(500) + 500;
                System.out.println(Thread.currentThread().getName() + "耗时" + num);
                map.put(i, num);
                Thread.sleep(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //结束线程计数减1
                endLatch.countDown();
            }
        }
    }
}
