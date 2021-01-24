package com.bbear.example.function;

import com.bbear.example.dao.UserDao;

/**
 * @author chenjunxiong <chenjunxiong@kuaishou.com>
 * Created on 2021-01-22
 */
public class PrintTest {

    public static void main(String[] args) {
//        FunctionTest1 functionTest1 = new FunctionTest1();
//        String print = functionTest1.print(111);
//        System.out.println(print);

        UserDao userDao = new UserDao();
        userDao.setName("测试");

        IFunctionTest2 functionTest2 = new IFunctionTest2();
        String print = functionTest2.print(userDao);
        System.out.println(print);

//        String s = functionTest2.print2(userDao, () -> {
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("123", "测试123==");
//            map.put("234", "测试234==");
//            return map;
//        });
//        System.out.println(s);

        String s = functionTest2.print3(() -> {
            ParamInterfaceImpl paramInterface = new ParamInterfaceImpl();
            return paramInterface.test();
        });

        System.out.println(s);

        int s1 = functionTest2.print4(() -> {
            return 23;
        });

        System.out.println(s1);

        IFunctionTest1 functionTest1 = new IFunctionTest1();
        boolean test = functionTest1.predicate.test("22");
        System.out.println(test);
    }

}
