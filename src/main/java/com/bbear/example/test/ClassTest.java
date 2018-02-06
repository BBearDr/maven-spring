package com.bbear.example.test;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author junxiongchen
 * @date 2017/12/04
 */
public class ClassTest {
    public void method(String param){

    }
    public void method1(String param){

    }

    public static class Father implements Serializable {
        //内部类
        public class FatherInnerClass{

        }
    }
    public static class Childer extends Father implements Serializable{
        public class ChildrenInnerClass {

        }
    }

    public static void main(String[] args) {
        ClassTest classTest = new ClassTest();
        /**
         * 判断输入的类型是否与设定的类型兼容
         * 父类，实现类，实例
         */
        ArrayList<String> list = new ArrayList<String>();
        boolean isInstance = Collection.class.isInstance(list);
///        System.out.println(isInstance);
        /**
         * 将一个对象强制转换成class表示的类或者接口
         */
        List list1 = List.class.cast(list);
        Collection collection = Collection.class.cast(list);
///        System.out.println(collection.size());
        /**
         * 获取该类上的注解、成员变量、构造方法，方法
         */
        //获得该类，以及父类上的所有方法
        Method[] methods = classTest.getClass().getMethods();
        try {
            //获取指定方法
            Method method = ClassTest.class.getMethod("toString");
///            System.out.println(method.getName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        /**
         * 当前类中的方法
         */
        Method[] methods1 = classTest.getClass().getDeclaredMethods();
        for (Method method : methods1) {
///            System.out.println(method.getName());
        }
        /**
         * 返回内部类，内部注解，内部接口等
         */
        Class<?>[] classzz = ClassTest.class.getClasses();
        for (Class c : classzz) {
///            System.out.println(c.toString());
        }
        /**
         * 返回"数组"表示的元素类型
         */
        String[] str = {"21","23"};
        Class<?> componentType = str.getClass().getComponentType();
///        System.out.println(componentType.toString());
        /**
         * 该类是在那个类中定义的
         */
        Class<?> enclosingClass = Childer.class.getEnclosingClass();
///        System.out.println(enclosingClass.toString());
        /**
         * 该类中实现的接口,不包括父类的
         */
        Class<?>[] interfaces = Childer.class.getInterfaces();
///        System.out.println(interfaces[0].toString());
        /**
         * 返回此类或接口以整数编码的 Java 语言修饰符。
         */
        int modifiers = ClassTest.class.getModifiers();
        String modName = Modifier.toString(modifiers);
//        System.out.println(modName);

        /**
         * 获得包名
         */
        Package aPackage = ClassTest.class.getPackage();
//        System.out.println(aPackage.getName());
        /**
         * 获得流
         */
        InputStream resourceAsStream = ClassTest.class.getResourceAsStream("");
        /**
         * 获取定义的泛型
         */
        TypeVariable<Class<Childer>>[] typeParameters = Childer.class.getTypeParameters();
//        System.out.println(typeParameters.length);
        /**
         * 注解判断
         */
        boolean isAnnotation = ClassTest.class.isAnnotation();
        System.out.println(isAnnotation);
    }
}
