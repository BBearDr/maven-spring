package com.bbear.example.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用队列实现栈的下列操作：先进后出
         push(x) -- 元素 x 入栈
         pop() -- 移除栈顶元素
         top() -- 获取栈顶元素
         empty() -- 返回栈是否为空
 * @author junxiongchen
 * @date 2018/08/09
 */
public class List2Stack {
    private static List<Integer> listI = new ArrayList<Integer>();
    private static List<Integer> listII = new ArrayList<>();

    public static void main(String[] args) {
        List2Stack list2Stack = new List2Stack();
        list2Stack.empty();
        list2Stack.push(1);
        list2Stack.push(2);
        list2Stack.push(3);
        list2Stack.push(4);
        System.out.println(list2Stack.pop());
        System.out.println(listI);
        System.out.println(listII);
        System.out.println(list2Stack.pop());
        System.out.println(listI);
        System.out.println(listII);
        list2Stack.push(5);
        System.out.println(listI);
        System.out.println(listII);
        System.out.println(list2Stack.top());
        System.out.println(list2Stack.empty());
    }

    /**
     * 向栈中加入元素,哪个队列中的元素为空加入哪个
     * @param num
     */
    private void push(int num) {
        if (listI.size() == 0 && listII.size() == 0 || listII.size() == 0) {
            listI.add(num);
        }
        if (listI.size() == 0) {
            listII.add(num);
        }
    }

    /**
     * 移除栈顶元素
     */
    private int pop(){
        if (listI.size() == 0 && listII.size() == 0){
            System.out.println("队列为空");
            return 0;
        }
        //将有元素的队列移入没有元素的队列中，原队列中只保留最后一个，并返回
        if (listI.size() > 0) {
            return getPop(listI, listII);
        }
        if (listII.size() > 0) {
            return getPop(listII, listI);
        }
        return 0;
    }

    private int getPop(List<Integer> list1,List<Integer> list2) {
        //剩下一个元素作为出栈元素
        while(list1.size()>1) {
            list2.add(list1.remove(0));
        }
        return list1.remove(0);
    }


    /**
     * 返回栈顶元素，返回队列中的最后一个元素
     * @return
     */
    private int top() {
        if (listI.size() == 0 && listII.size() == 0) {
            System.out.println("stack为空");
            return 0;
        }
        if (listII.size() == 0) {
            return listI.get(listI.size()-1);
        }
        if (listI.size() == 0) {
            return listII.get(listII.size()-1);
        }
        return 0;
    }
    private boolean empty() {
        if (listI.size() == 0 && listII.size() == 0) {
            System.out.println("stack为空");
            return true;
        }
        return false;
    }
}
