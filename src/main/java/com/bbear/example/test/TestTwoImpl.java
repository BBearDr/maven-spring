package com.bbear.example.test;

/**
 * @author chenjunxiong <chenjunxiong@kuaishou.com>
 * Created on 2021-03-09
 */
public class TestTwoImpl extends AbstractTest {
    @Override
    public boolean doSingle() {
        return false;
    }

    @Override
    public String getName() {
        return "this is TestTwoImpl";
    }

    @Override
    public void execute() {
        System.out.println("this is TestTwoImpl execute");
        super.execute();
    }

}
