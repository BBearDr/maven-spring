package com.bbear.example.lintCode;

/**
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
        System.out.println(""+(~result));
        return result;
    }
    private static int solution1(int[] num) {
        int ones = 0;
        int twos = 0;
        for(int i=0;i< num.length ;i++){
            ones = (ones^num[i]) & (~ twos);
            twos = (twos^num[i]) & (~ ones);
            System.out.println("ones:"+ones+"twos:"+twos);
        }
        return ones;
    }
}
