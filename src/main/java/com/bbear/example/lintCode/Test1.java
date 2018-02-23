package com.bbear.example.lintCode;

/**
 * 计算阶乘尾数的0的个数
 * 1,2,3,4,5,6,7……，取值是5的倍数.即5*k
 * 当是25的倍数的时候 ,会产生两个0,5*(1,2,3,4,5)=5*5k
 * @author junxiongchen
 * @date 2018/02/23
 */
public class Test1 {
    public static void main(String[] args) {
        System.out.println(calZero(101));
    }

    private static long calZero(long n) {
        long count = 0;
        long temp = n / 5;
        while (temp != 0) {
            count += temp;
            temp = temp / 5;
        }
        return count;
    }
}
