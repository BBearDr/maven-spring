package com.bbear.example.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author chenjunxiong <chenjunxiong@kuaishou.com>
 * Created on 2021-03-24
 */
public abstract class AbstractProtocol implements Protocol {
    @Override
    public String getStrInfo() {
        return "abstract protocol";
    }
}
