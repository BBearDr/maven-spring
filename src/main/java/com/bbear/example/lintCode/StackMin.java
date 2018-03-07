package com.bbear.example.lintCode;

import java.util.Stack;

/**
 * 实现一个带有取最小值min方法的栈，min方法将返回当前栈中的最小值。
 你实现的栈将支持push，pop 和 min 操作，所有操作要求都在O(1)时间内完成。
 栈的特点：先进后出

 * @author junxiongchen
 * @date 2018/02/23
 */
public class StackMin {
    Stack<Integer> s;
    Stack<Integer> fuzhu;

    public StackMin() {
        s = new Stack<>();
        fuzhu = new Stack<>();
    }
    public static void main(String[] args) throws Exception {
        StackMin stackMin = new StackMin();
        stackMin.push(13);
        stackMin.push(7);
        stackMin.push(11);
        stackMin.push(2);
        stackMin.push(1);
        stackMin.pop();
        System.out.println(stackMin.min());
        System.out.println(stackMin.s);
        System.out.println(stackMin.fuzhu);
    }
    //压入堆的顶部
    private  void push(int number) {
        s.push(number);
        if (fuzhu.isEmpty() == true) {
            fuzhu.push(number);
        } else {
            //查看堆顶部的对象但是不移除
            Integer peek = fuzhu.peek();
            if (peek < number) {
                fuzhu.push(peek);
            } else {
                fuzhu.push(number);
            }
        }
    }
    //移除堆顶部的对象并且返回
    private int pop() throws Exception {
        if (s.isEmpty() == true) {
            throw new Exception("stack is null");
        } else {
            fuzhu.pop();
            return s.pop();
        }
    }

    private int min() throws Exception {
        if (fuzhu.isEmpty() == true) {
            throw new Exception("min stack is null");
        } else {
            return fuzhu.peek();
        }
    }
}
