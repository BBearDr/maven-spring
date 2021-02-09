package com.bbear.example.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;

/**
 * @author chenjunxiong <chenjunxiong@kuaishou.com>
 * Created on 2021-01-24
 */
public class ReflectionMain {

    public static void main(String[] args) {

        test1();
    }

    public static void test1() {
        Class<ReflectionModel> reflectionModelClass = ReflectionModel.class;
        Method[] declaredMethods = reflectionModelClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.println(parameterType.getName());
            }


        }
    }
}
