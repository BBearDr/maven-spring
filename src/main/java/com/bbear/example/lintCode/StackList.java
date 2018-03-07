package com.bbear.example.lintCode;

import java.util.Stack;

/**
 * 使用两个栈来实现队列，支持push,pop,top
 * 实现push(1), pop(), push(2), push(3), top(), pop()，你应该返回1，2和2
 * 队列的特点：先进后出
 *
 * @author junxiongchen
 * @date 2018/02/24
 */
public class StackList {
    /**
     * 入栈
     */
    Stack<Integer> stack;
    /**
     * 出栈
     */
    Stack<Integer> stack1;

    public StackList() {
        stack = new Stack<>();
        stack1 = new Stack<>();
    }

    public static void main(String[] args) {
        StackList stackList = new StackList();
        stackList.push(1);
        System.out.println(stackList.pop());
        stackList.push(2);
        stackList.push(3);
        System.out.println(stackList.top());
        System.out.println(stackList.pop());
    }

    private void push(int number) {
        stack.push(number);
    }

    private int pop() {
        if (!stack1.empty()) {
            return stack1.pop();
        } else {
            while (stack.size() > 0) {
                stack1.push(stack.pop());
            }
            System.out.println("stack:" + stack);
            System.out.println("stack1" + stack1);
            return stack1.pop();
        }
    }

    private int top() {
        if (!stack1.empty()) {
            return stack1.pop();
        } else {
            while (stack.size() > 0) {
                stack1.push(stack.pop());
            }
            return stack1.peek();
        }
    }
}
