package com.bbear.example.lintCode;

import java.util.Stack;

/**
 * 使用两个栈来实现队列，支持push,pop,top
 * 实现push(1), pop(), push(2), push(3), top(), pop()，你应该返回1，2和2
 *
 * 思路 ：  栈是先进后出，队列是先进先出的
 *              所以一个栈作为入栈，一个栈作为出栈，实现队列
 *
 * @author junxiongchen
 * @date 2018/02/24
 */
public class Stack2List {
    /**
     * 入栈
     */
    static Stack<Integer> stackPush;
    /**
     * 出栈
     */
    static Stack<Integer> stackPop;

    public Stack2List() {
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    public static void main(String[] args) {
        Stack2List stackList = new Stack2List();
        stackList.push(1);
        stackList.push(2);
        stackList.push(3);
        System.out.println(stackList.pop());
        stackList.push(4);
        stackList.push(5);
        System.out.println(stackList.pop());
        System.out.println("stackPush:" + stackPush);
        System.out.println("stackPop:" + stackPop);
    }

    /**
     * 入栈
     * @param number
     */
    private void push(int number) {
        stackPush.push(number);
    }

    /**
     * 出栈
     * @return
     */
    private int pop() {
        //判断出栈中是否还有元素，有则出栈
        if (!stackPop.empty()) {
            return stackPop.pop();
        } else {
            //出栈中没有元素
            //判断入栈中是否还有元素，有则给到出栈
            while (stackPush.size() > 0) {
                stackPop.push(stackPush.pop());
            }
        /*    System.out.println("stack:" + stackPush);
            System.out.println("stackPop" + stackPop);*/
            //删除栈顶的值返回
            return stackPop.pop();
        }
    }

    private int top() {
        if (!stackPop.empty()) {
            return stackPop.pop();
        } else {
            while (stackPush.size() > 0) {
                stackPop.push(stackPush.pop());
            }
            //不删除栈顶的值返回
            return stackPop.peek();
        }
    }
}
