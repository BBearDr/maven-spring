package com.bbear.example.design.order;

/**
 * 命令模式，类解耦，命令发出去后，不用在意具体是谁执行了命令，只要知道有人执行了该命令，并且成功返回结果
 * 但是，当命令过多的时候就会出现Command的子类过多的情况
 * @author junxiongchen
 * @date 2018/02/05
 */
public class OrderMain {
    public static void main(String[] args) {
        Receiver receiver = new ConcreateReceiver();
        Command command = new ConcreateCommand(receiver);
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.action();
    }
}
