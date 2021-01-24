package com.bbear.example.function;

import java.util.Map;
import com.bbear.example.dao.UserDao;

/**
 * @author chenjunxiong <chenjunxiong@kuaishou.com>
 * Created on 2021-01-22
 */
public class IFunctionTest2 implements IFunction<String, UserDao> {
    @Override
    public String print(UserDao userDao) {
        return userDao.getName();
    }

    public <T> String print2(UserDao userDao, ParamInterface<T> param) {
        Map<String,String> map = (Map<String, String>) param.test();
        return map.get("123") + userDao.getName();
    }

    public String print3(ParamInterface<?> param) {
        return (String) param.test();
    }

    /**
     * 通配符 "？" 相对与 T
     * ？表示不确定的java类型
     * T 表示确定的java类型 即 可以用 T t= new T() 表示，而不能用？
     * KV代表键值对
     * E 代表 element
     */
    public <T> T print4(ParamInterface<? extends T> paramInterface) {
        return paramInterface.test();
    }
}
