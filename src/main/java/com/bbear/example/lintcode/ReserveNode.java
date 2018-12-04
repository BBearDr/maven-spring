package com.bbear.example.lintcode;


import org.w3c.dom.Node;
import java.util.Stack;

/**
 * 反转单链表 ,暂时忽略Node的问题
 * 1->2->3->null ,反转后 null<-1<-2-<3
 *
 * @author junxiongchen
 * @date 2018/03/07
 */
public class ReserveNode {
    /**
     *  利用栈的先进后出
     * @param node
     */
    private void reserveNode1(Node node){
        Stack<Node> stack = new Stack<>();
        while (node != null) {
            stack.push(node);
            node = node.getNextSibling();
        }
        while (stack != null) {
            System.out.println("stack.pop=====>" + stack.pop());
        }
    }

    /**
     * 头插法插入链表
     * @param head
     */
    private void reserveNode2(Node head){
        if (head == null) {
            return;
        }
        /**翻转之后的链表*/
        Node node ;

        Node pre = head;
        Node cur = head.getNextSibling();
        Node next;
        while (cur != null){
            next = cur.getNextSibling();
//            cur.getNextSibling() = pre;
            pre = cur;
            cur = next;
        }
     //   head.getNextSibling() = null;
        node = pre;
    }

}
