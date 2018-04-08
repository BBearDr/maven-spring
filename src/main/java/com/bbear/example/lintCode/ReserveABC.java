package com.bbear.example.lintCode;

import java.util.Arrays;

/**
 *1. 单词的构成：无空格字母构成一个单词
   2.输入字符串是否包括前导或者尾随空格？可以包括，但是反转后的字符不能包括
   3.如何处理两个单词间的多个空格？在反转字符串中间空格减少到只含一个

 * @author junxiongchen
 * @date 2018/03/21
 */
public class ReserveABC {
    public static void main(String[] args) {
        String str = "  ABC      is world ! ";
        String s = solution(str);
        System.out.println(s);
    }
    private static String solution(String str){
        String[] split = str.trim().split(" ");
        System.out.println(Arrays.toString(split));
        StringBuilder sb = new StringBuilder();
        for (int i =  split.length -1; i >=0; i--) {
//            System.out.println(i);
            if ("".equals(split[i])) {
                continue;
            }
            sb.append(split[i]).append(",");
        }

        return sb.toString().trim();
    }
}
