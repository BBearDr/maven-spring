package com.bbear.example.design.productAndConsume;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 队列实现的生产消费模式
 * @author junxiongchen
 * @date 2018/03/14
 */
public class QueueThread {
    private int queueSize = 5;
    private LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(queueSize);

    public static void main(String[] args)  {
        QueueThread test = new QueueThread();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();

        producer.start();
        consumer.start();
    }

    class Consumer extends Thread{

        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while(true){
                try {
                    Thread.sleep(1000);
                    queue.poll();
                    System.out.println("从队列取走一个元素，队列剩余"+queue.size()+"个元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Producer extends Thread{

        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while(true){
                try {
                    Thread.sleep(1000);
                    for (int i = 0; i < 5; i++) {
                        System.out.println(1);
                        queue.put(20);
                    }
//                    queue.put(20);

                    System.out.println("向队列取中插入一个元素，队列剩余空间："+queue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

