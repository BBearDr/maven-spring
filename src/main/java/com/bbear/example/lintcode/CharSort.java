package com.bbear.example.lintcode;

/**
 * 字符大小写顺序
 *  按照小写字母大写字母的顺序进行排序
 * @author junxiongchen
 * @date 2018/08/23
 */
public class CharSort {
    public static void main(String[] args) {
        char[] chars = {'a', 'b','A','c','D'};
        solution(chars);
    }

    /**
     * 思路： 小写字母和大写字母查找，分别从两遍开始进行，查找到位置之后进行交换
     * @param chars
     */
    private static void solution(char[] chars) {
        int start = 0;
        int end = chars.length - 1;
        while (start <= end) {
            while (start <= end && Character.isLowerCase(chars[start])) {
                start++;
            }
            while (start <= end && Character.isUpperCase(chars[end])) {
                end--;
            }
            if (start <= end) {
                char temp = chars[start];
                chars[start] = chars[end];
                chars[end] = temp;
            }
        }
        System.out.println(chars);
    }
}
