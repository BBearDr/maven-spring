package com.bbear.example.redis;

/**
 * @author junxiongchen
 * @date 2018/07/27
 */
public class Client implements Runnable {
    private Service service;

    public Client(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.demo();
    }

    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 10; i++) {
            new Thread(new Client(service)).start();
        }
    }
}
