package com.bbear.example.util.order;

/**
 * 调用者,具体的执行逻辑
 * @author junxiongchen
 * @date 2018/02/05
 */
public class Invoker {
    Command command;

    public void setCommand(Command command) {
        this.command = command;
    }
    public void action(){
        command.execute();
    }
}
