package com.bbear.example.test;

import java.util.List;

/**
 * @author chenjunxiong <chenjunxiong@kuaishou.com>
 * Created on 2021-03-24
 */
public class ProtocolTest extends AbstractProtocol implements Protocol1 {
    @Override
    public String getStrInfo() {
        return "protocol test";
    }

    @Override
    public List<String> getListInfo() {
        return null;
    }

    @Override
    public <T> T export() {
        return null;
    }
}
