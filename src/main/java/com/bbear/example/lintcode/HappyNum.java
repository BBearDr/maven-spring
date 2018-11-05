package com.bbear.example.lintcode;

/**
 * 快乐数
 * 当一个数每个位置上的数字进行平方，相加的数字再进行上述操作，
 * 直到最后和为1，那么这个数字就为快乐数字
 * @author junxiongchen
 * @date 2018/11/05
 */
public class HappyNum {
    public static void main(String[] args) {
        HappyNum happyNum = new HappyNum();
        happyNum.solution(19);
    }

    /**
     * 在快乐数字中，如果出现4就表示它不是快乐数字，会在之后的运算中再出现4
     * @param num
     */
    private void solution(int num){
        while (num !=1 && num !=4) {
            int result = 0;
            while (num != 0) {
                result += (num % 10) * (num % 10);
                num /= 10;
            }
            num = result;
        }
        System.out.println(num);
    }
}
