package com.bbear.example.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用CountDownLatch，来判断线程是否执行完任务
 * 但是这种性能差与第一种
 * @author junxiongchen
 * @date 2018/09/17
 */
public class ThreadShutDown2 {
    private static CountDownLatch countDownLatch = new CountDownLatch(3);
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("超出了");
        }
    });
    private static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        for (int i = 0; i < 3; i++) {
            threadPoolExecutor.execute(new sol());
        }
        threadPoolExecutor.shutdown();
        countDownLatch.await();
        System.out.println("结束");
    }

    private static class sol implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(list.get(i));
            }
           countDownLatch.countDown();
        }
    }
}
