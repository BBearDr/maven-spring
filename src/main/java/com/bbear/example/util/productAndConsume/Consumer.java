package com.bbear.example.util.productAndConsume;

/**
 * @author junxiongchen
 * @date 2017/12/26
 */
public class Consumer extends Thread {
    //消费数目
    private int num;
    //所在仓库的位置
    private StorageLock storageLock;

    public Consumer(StorageLock storageLock) {
        this.storageLock = storageLock;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public StorageLock getStorageLock() {
        return storageLock;
    }

    public void setStorageLock(StorageLock storageLock) {
        this.storageLock = storageLock;
    }

    @Override
    public void run(){
        consume(num);
    }

    private void consume(int num) {
        storageLock.consumer(num);
    }
}
