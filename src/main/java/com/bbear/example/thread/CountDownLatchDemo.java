package com.bbear.example.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author junxiongchen
 * @date 2018/04/26
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
    }
    private static long timeTask(int nThreads ,final Runnable task){
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            Thread thread = new Thread(){
                @Override
                public void run(){
                    try {
                        start.await();
                        try {
                            task.run();
                        }finally {
                            end.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
        long s = System.currentTimeMillis();
        start.countDown();
        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long s1 = System.currentTimeMillis();
        return s1 - s;
    }
}
