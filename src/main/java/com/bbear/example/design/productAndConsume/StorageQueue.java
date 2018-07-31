package com.bbear.example.design.productAndConsume;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author junxiongchen
 * @date 2018/03/14
 */
public class StorageQueue {
    private static final int MAX_NUM = 200;
    private final Lock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final  Condition empty = lock.newCondition();
    private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<Object>(150);

    public LinkedBlockingQueue<Object> getList() {
        return list;
    }

    public void setList(LinkedBlockingQueue<Object> list) {
        this.list = list;
    }

    public void product(int num) {
        lock.lock();
        while (list.size() + num > MAX_NUM) {
            System.out.println("Thread1:"+Thread.currentThread().getName()+"[现在要生产数量]："+num+"\t【库存量】："+list.size()+"\t暂时不能在进行生产");
            try {
                full.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 1; i <= num; i++) {
            try {
                list.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread2:"+Thread.currentThread().getName()+"[已经生产]："+num+"[库存量]："+list.size());
        full.signalAll();
        empty.signalAll();

        lock.unlock();
    }
    public void consumer(int num) {
        lock.lock();
        //仓库已满
        while (list.size() < num) {
            System.out.println("Thread3:"+Thread.currentThread().getName()+"[现在要消费数量]：" + num +"\t库存量】：" + list.size() +  "\t暂时不能在进行消费");
            try {
                empty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 1; i <= num; i++) {
            list.poll();
        }
        System.out.println("Thread4:"+Thread.currentThread().getName()+"[已经消费]："+num+"[库存量]："+list.size());
        full.signalAll();
        empty.signalAll();
        lock.unlock();
    }
}
