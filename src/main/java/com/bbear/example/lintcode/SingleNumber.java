package com.bbear.example.lintcode;

/**
 * 在2n+1的数当中找出一个不同的数字
 *  思路：
 *   循环数组，运用异或运算，找出不同的数字
 *
 * @author junxiongchen
 * @date 2018/05/24
 */
public class SingleNumber {
    public static void main(String[] args) {
        int[] num = {1, 2, 2, 3, 1};
        int solution = solution(num);
        System.out.println(solution);
    }
    private static int solution(int[] num) {
        int i = 0;
        for (int i1 = 0; i1 < num.length; i1++) {
            //异或运算符，表示二进制数当相同两个数是0，不同的是1
            i ^= num[i1];
            System.out.println(i);
        }
        return i;
    }
}
