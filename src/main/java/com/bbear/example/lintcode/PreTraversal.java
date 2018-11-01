package com.bbear.example.lintcode;

import java.util.Stack;

/**
 * 前序遍历
 * @author junxiongchen
 * @date 2018/09/03
 */
public class PreTraversal<AnyType extends Comparable<? super AnyType>> {

    private static class TreeNode<AnyType> {
        AnyType element;
        TreeNode<AnyType> left;
        TreeNode<AnyType> right;
        public TreeNode(AnyType element) {
            this(element, null, null);
        }

        public TreeNode(AnyType thisElement,TreeNode<AnyType> left,TreeNode<AnyType> right) {
            element = thisElement;
            left = left;
            right = right;
        }


    }
    /**
     *将数组转化成二叉树
     */
    private TreeNode<AnyType> root;
    private void insert(AnyType element) {
        root = insert(element,root);
    }

    /**
     * 将每次添加的元素依次进行深度比较，当比父结点小的作为左子结点，大的是右子结点
     * @param element
     * @param root
     * @return
     */
    public TreeNode<AnyType> insert(AnyType element, TreeNode<AnyType> root) {
        //如果该结点没有子结点，那么添加该结点
        if (root == null) {
            return new TreeNode<AnyType>(element,null,null);
        }
        //root.element 始终指向的是父结点
        int compareResult = element.compareTo(root.element);
        if (compareResult < 0) {
            root.left = insert(element, root.left);
        }
        if (compareResult > 0) {
            root.right = insert(element, root.right);
        }
        return root;
    }

    public static void main(String[] args) {
        int[] input = {4, 2, 6, 1, 3, 5, 7, 8, 10};
        PreTraversal<Integer> tree = new PreTraversal<>();
        for (int i = 0; i < input.length; i++) {
            tree.insert(input[i]);
        }
        tree.solution1(tree.root);
    }

    /**
     * 使用非递归方式进行前序遍历
     * 思路：使用栈的先进后出原则，从根结点(直接输出)--右结点--左结点依次入栈然后出栈
     *
     * @param treeNode
     */
    private void solution(TreeNode<AnyType> treeNode) {
        if (treeNode == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);
        while (!stack.isEmpty()) {
            //关键步骤：将刚入栈的子二叉数作为新数，进行判断
            TreeNode node = stack.pop();
            System.out.print(node.element + " ,");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     *  使用递归方式进行前序遍历
     * @param treeNode
     */
    private void solution1(TreeNode<AnyType> treeNode) {
        if (treeNode != null) {
            System.out.print(treeNode.element + " ");
            solution1(treeNode.left);
            solution1(treeNode.right);
        }
    }
}
