package com.bbear.example.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 栅栏 ，侧重的是一组线程相互等待至某个状态，然后线程在同时进行
 * @author junxiongchen
 * @date 2018/09/26
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        int nThread = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        for (int i = 0; i < nThread; i++) {
            new Demo(cyclicBarrier).start();
        }
    }

    static class Demo extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Demo(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "：正在写入数据");
            try {
                Thread.sleep(4000);
                System.out.println(Thread.currentThread().getName() + "： 已经写完数据");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有数据都已经写完");
        }
    }
}
