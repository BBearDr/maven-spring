package com.bbear.example.lintCode;

/**
 * @author junxiongchen
 * @date 2018/09/03
 */
public class Tree<AnyType extends Comparable<? super AnyType>> {
    private static class BinaryNode<AnyType> {
        BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;
    }

    private BinaryNode<AnyType> root;

    public void insert(AnyType x) {
        root = insert(x, root);
    }

    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return new BinaryNode<>(x, null, null);
        }
        System.out.println("x:" + x+ ",t:" +t.element);
        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        } else {
            ;
        }

        return t;
    }

    /**
     * 前序遍历
     */
    public void preOrder(BinaryNode<AnyType> Node) {
        if (Node != null) {
            System.out.print(Node.element + " 前序");
            preOrder(Node.left);
            preOrder(Node.right);
        }
    }

    /**
     * 中序遍历
     */
    public void midOrder(BinaryNode<AnyType> Node) {
        if (Node != null) {
            midOrder(Node.left);
            System.out.print(Node.element + " 中序");
            midOrder(Node.right);
        }
    }

    /**
     * 后序遍历
     */
    public void posOrder(BinaryNode<AnyType> Node) {
        if (Node != null) {
            posOrder(Node.left);
            posOrder(Node.right);
            System.out.print(Node.element + "后序 ");
        }
    }

    /*
     * 层序遍历
     * 递归
     */
    public void levelOrder(BinaryNode<AnyType> Node) {
        if (Node == null) {
            return;
        }

        int depth = depth(Node);

        for (int i = 1; i <= depth; i++) {
            levelOrder(Node, i);
        }
    }

    private void levelOrder(BinaryNode<AnyType> Node, int level) {
        if (Node == null || level < 1) {
            return;
        }

        if (level == 1) {
            System.out.print(Node.element + "  ");
            return;
        }

        // 左子树
        levelOrder(Node.left, level - 1);

        // 右子树
        levelOrder(Node.right, level - 1);
    }

    public int depth(BinaryNode<AnyType> Node) {
        if (Node == null) {
            return 0;
        }

        int l = depth(Node.left);
        int r = depth(Node.right);
        if (l > r) {
            return l + 1;
        } else {
            return r + 1;
        }
    }

    public static void main(String[] args) {
        int[] input = {4, 2, 6, 1, 3, 5, 7, 8, 10};
        Tree<Integer> tree = new Tree<>();
        for (int i = 0; i < input.length; i++) {
            tree.insert(input[i]);
        }
        System.out.print("前序遍历 ：");
        tree.preOrder(tree.root);
        System.out.print("\n中序遍历 ：");
        tree.midOrder(tree.root);
        System.out.print("\n后序遍历 ：");
        tree.posOrder(tree.root);
        System.out.print("\n递归层序遍历：");
        tree.levelOrder(tree.root);
    }
}
