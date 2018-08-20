package com.bbear.example.lintCode;

/**
 * 不使用加法进行加法运算
 *
 * @author junxiongchen
 * @date 2018/02/08
 */
public class Add {
    public static void main(String[] args) {
        int a=2;
        int b = 3;
        System.out.println(cal(a, b));
    }

    private static int cal(int a, int b) {
        int c = 0;
        int d = 0;
        //a,b没有进位
        while ((a & b) != 0) {
            System.out.println("a&b:" + (a&b));
            //忽略进位进行计算
            c = a ^ b;
            System.out.println("c:" + c);
            //应该进位的值
            d = (a & b) << 1;
            System.out.println("d:" + d);
            a = c;
            b = d;
        }
        return a | b;
    }
    private static int cal2(int a, int b){
        if ((a & b) == 0) {
            return a | b;
        }
        return cal2(a ^ b, (a & b) << 1);
    }
}
