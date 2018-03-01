package com.bbear.example.test.thread;


import static java.lang.Math.abs;

/**
 * 计算三个数的和
 * 即使是无序的数组，要先对他进行排序，然后在进行从两头进行加减，从而使时间复杂度和空间复杂度是最小的
 * @author junxiongchen
 * @date 2018/02/28
 */
public class ClassTest2 {
    public static void main(String[] args) {
        int[] str = {-1,-2,0,1,2,3,4};
        String s = test(str, 0);
        String s1 = test1(str, 9);
        System.out.println(s);
        System.out.println(s1);
    }

    /**
     * 返回三数之和是0的三个数字
     * @param num
     * @param target
     * @return
     */
    private static String test(int[] num, int target){
        String result = "";
        int i = 0, last = num.length - 1;
        while (i < last) {
            int a = num[i], j = i+1,k = last;
            while (j < k) {
                int b = num[j], c = num[k];
                int sum = a + b + c;
                if (sum == 0) {
                    return num[i] + "," + num[j] + "," + num[k];
                }
                if (sum < 0) {
                    while (num[j] == b && j < k) {
                        ++j;
                    }
                }
                if (sum > 0) {
                    while (num[k] == c && j < k) {
                        --k;
                    }
                }
            }
            while (num[i] == a && i<last) {
                ++i;
            }
        }
        return result;
    }

    private static String test1(int[] num, int target) {
        String result = "";
        int i = 0, last = num.length - 1;
        int min = num[0] + num[1] + num[2];
        while (i < last) {
            int a = num[i], j = i+1,k = last;
            while (j < k) {
                int b = num[j], c = num[k];
                int sum = a + b + c;
                if (abs(min-target) > abs(sum - target)) {
                    min = sum;
                }
                if (min == target) {
                    return num[i] + "," + num[j] + "," + num[k];
                }
                if (sum < target) {
                    while (num[j] == b && j < k) {
                        ++j;
                    }
                }
                if (sum > target) {
                    while (num[k] == c && j < k) {
                        --k;
                    }
                }
            }
            while (num[i] == a && i<last) {
                ++i;
            }
        }
        return result;
    }
}

