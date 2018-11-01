package com.bbear.example.lintcode;

/**
 * 寻找落单的数
 * 3n +1 数中找到落单的数
 *
 * @author junxiongchen
 * @date 2018/05/29
 */
public class SignalNumberII {
    public SignalNumberII() {
    }

    public static void main(String[] args) {
        int[] num = {1, 1, 1, 3};
        int solution = solution1(num);
        System.out.println(solution);
    }

    private static int solution(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }
        int[] bits = new int[32];
        int result = 0;
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < num.length; j++) {
                bits[i] += num[j] >> i & 1;
                System.out.println(num[j] >> i & 1);
            }
            bits[i] = bits[i] % 3;
            result = result | bits[i] << i;
        }
        System.out.println("" + (~result));
        return result;
    }

    /**
     * ^ 异或： 1^1 = 0 ,0^0 =0, 1^0=1,0^1=1
     * 思路： one保存第一次出现的数据，two保存第二次出现的数据，第三次出现的时候两数都会清0
     * @param num
     * @return
     */
    private static int solution1(int[] num) {
        int ones = 0;
        int twos = 0;
        for (int i = 0; i < num.length; i++) {
            ones = (ones ^ num[i]) & (~twos);
            twos = (twos ^ num[i]) & (~ones);
            System.out.println("ones:" + ones + "twos:" + twos);
        }
        return ones;
    }
}
