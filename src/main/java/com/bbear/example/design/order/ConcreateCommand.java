package com.bbear.example.design.order;

/**
 * 命令执行具体类
 * @author junxiongchen
 * @date 2018/02/05
 */
public class ConcreateCommand extends Command {
    Receiver receiver ;

    public ConcreateCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.doSomething();
    }
}
