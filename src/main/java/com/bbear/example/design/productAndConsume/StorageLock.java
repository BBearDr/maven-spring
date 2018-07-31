package com.bbear.example.design.productAndConsume;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author junxiongchen
 * @date 2017/12/26
 */
public class StorageLock {
    private static final int MAX_NUM = 100;
    private final Lock lock = new ReentrantLock();
    private final  Condition full = lock.newCondition();
    private final  Condition empty = lock.newCondition();
    private LinkedList<Object> list = new LinkedList<Object>();
    public LinkedList<Object> getList()
    {
        return list;
    }

    public void setList(LinkedList<Object> list)
    {
        this.list = list;
    }


    public void product(int num){
        lock.lock();
        while (list.size() + num > MAX_NUM) {
            System.out.println("[现在要生产数量]："+num+"\t【库存量】："+list.size()+"\t暂时不能在进行生产");
            try {
                full.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 1; i <= num; i++) {
            list.add(new Object());
        }
        System.out.println("[已经生产]："+num+"[库存量]："+list.size());
        full.signalAll();
        empty.signalAll();

        lock.unlock();
    }
    public void consumer(int num) {
        lock.lock();
        //仓库已满
        while (list.size() < num) {
            System.out.println("[现在要消费数量]：" + num +"\t库存量】：" + list.size() +  "\t暂时不能在进行消费");
            try {
                empty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 1; i <= num; i++) {
            list.remove();
        }
        System.out.println("[已经消费]："+num+"[库存量]："+list.size());
        full.signalAll();
        empty.signalAll();
        lock.unlock();
    }
}
