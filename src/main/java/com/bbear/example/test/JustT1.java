package com.bbear.example.test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author junxiongchen
 * @date 2018/03/29
 */
public class JustT1 {
    public static void main(String[] args) {
        int i = -1;
        System.out.println(1&(~i));
    }

    private static double findMidNum(int[] A, int[] B) {
        int len = A.length + B.length;
        //偶数
        if (len % 2 == 0) {
            return (findKh(A, 0, B, 0, len / 2) + findKh(A, 0, B, 0, len / 2 + 1)) / 2.0;
        }
        return findKh(A, 0, B, 0, len / 2 + 1);
    }

    private static double findKh(int[] a, int startA, int[] b, int startB, int k) {
        if (startA >= a.length) {
            return b[startB + k - 1];
        }
        if (startB >= b.length) {
            return a[startA + k - 1];
        }
        if (k == 1) {
            return Math.min(a[startA], b[startB]);
        }
        int halfA = startA + k / 2 - 1 < a.length ? a[startA + k / 2 - 1] : Integer.MAX_VALUE;
        int halfB = startB + k / 2 - 1 < b.length ? b[startB + k / 2 - 1] : Integer.MAX_VALUE;
        if (halfA > halfB) {
            return findKh(a, startA, b, startB + k / 2, k - k / 2);
        } else {
            return findKh(a, startA + k / 2, b, startB, k - k / 2);
        }
    }
}
