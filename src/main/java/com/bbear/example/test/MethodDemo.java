package com.bbear.example.test;

import com.bbear.example.pojo.UserPOJO;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author junxiongchen
 * @date 2017/11/29
 */
public class MethodDemo {

    public static void main(String[] args) {
        List<? extends Serializable> list = getList(1, "sbc");
        for (int i = 0; i < list.size(); i++) {
            Serializable serializable = list.get(i);
            System.out.println(serializable.toString());
        }
    }
    private static <T> List<T> getList(T param,T param2){
        List<T> list = new ArrayList<T>();
        list.add(param);
        list.add(param2);
        return list;
    }
}
