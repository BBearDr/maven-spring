package com.bbear.example.thread;

import com.bbear.example.test.Demo;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用threadPoolExecutor.isTerminated() 判断线程是否执行完任务
 * @author junxiongchen
 * @date 2018/09/17
 */
public class ThreadShutDown {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            }
        });
        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.execute(new Termin(i));
        }
        threadPoolExecutor.shutdown();
        while (true) {
            //此方法是线程结束之后在进行输出
            if (threadPoolExecutor.isTerminated()) {
                System.out.println("结束了");
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Termin implements Runnable {
        private int ii;

        public Termin(int ii) {
            this.ii = ii;
        }

        @Override
        public void run() {
            System.out.println(ii);
        }
    }
}
