package com.bbear.example.thread;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList 带有锁的数组
 *
 * @author junxiongchen
 * @date 2018/08/30
 */
public class CopyOnWriteDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add(1);
        copyOnWriteArrayList.add(2);
        copyOnWriteArrayList.add(4);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                copyOnWriteArrayList.set(1, 11);
                copyOnWriteArrayList.remove(2);
            }
        });
        /**
         * CopyOnWriteArrayList 是弱一致性，因为在遍历元素的时候获取的是原数组的快照
         * 当有其他线程对集合元素进行增删时，对遍历的集合是不可见的，所以在下面情况下是遍历的依然是原集合
         */
        Iterator<Integer> iterator = copyOnWriteArrayList.iterator();

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
