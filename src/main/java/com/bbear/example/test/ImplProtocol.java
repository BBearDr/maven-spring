package com.bbear.example.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author junxiongchen
 * @date 2018/03/08
 */
public  class ImplProtocol implements Protocol {

    protected static List<String> list = new ArrayList<String>();

    public ImplProtocol(List<String> list) {
        list.add("1");
        list.add("2");
        list.add("3");
        ImplProtocol.list = list;
    }

    @Override
    public String getStrInfo() {
        return null;
    }

    @Override
    public List<String> getListInfo() {
        return null;
    }

    @Override
    public <T> T export() {
        List<String> list = new ArrayList<>();
        list.add("1");
        return (T) list;
    }
}
