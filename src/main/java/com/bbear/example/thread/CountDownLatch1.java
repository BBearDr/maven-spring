package com.bbear.example.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author junxiongchen
 * @date 2018/09/11
 */
public class CountDownLatch1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2){
            @Override
            public void await() throws InterruptedException {
                super.await();
                System.out.println(Thread.currentThread().getName() + " count down is ok");
            }
        };
        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ",is done");
                countDownLatch.countDown();
            }
        },"thread1");
        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ",is done");
                countDownLatch.countDown();
            }
        },"thread2");

        th1.start();
        th2.start();
     //   countDownLatch.await();
    }
}
