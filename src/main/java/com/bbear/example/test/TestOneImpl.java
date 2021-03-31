package com.bbear.example.test;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author chenjunxiong <chenjunxiong@kuaishou.com>
 * Created on 2021-03-09
 */
@Service
public class TestOneImpl extends AbstractTest implements TestInterface {


    @Override
    public boolean doSingle() {
        return false;
    }

    @Override
    public String getName() {
        return "this is TestOneImpl";
    }

    @Override
    public void check() {
        execute();
    }
}
