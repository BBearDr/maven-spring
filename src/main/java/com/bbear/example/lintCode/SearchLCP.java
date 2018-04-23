package com.bbear.example.lintCode;

import java.util.ArrayList;
import java.util.List;


/**
 * 查找最小公共前缀 例如 abc ,abcdf ,abcmb, 得到最小公共前缀是abc
 * @author junxiongchen
 * @date 2018/04/08
 */
public class SearchLCP {
    public static void main(String[] args) {
        List<String[]> list = new ArrayList<>();
        String[] s1 = {"a","b","c","m"};
        String[] s2 = {"a","b","c","d","g","v"};
        String[] s4 = {"a","b","c","f"};
        String[] s3 = {"a","b","c","d","r","w","t"};
        list.add(s1);
        list.add(s2);
        list.add(s4);
        list.add(s3);
        String s = longCommentPrefix(list);
        System.out.println(s);
    }

    private static String longCommentPrefix(List<String[]> list) {
        if (list.size() <= 1) {
            return "";
        }
        String[] firstStr = list.get(0);
        int minStrLength = firstStr.length;
        for (int i = 0; i < list.size(); i++) {
            String[] nextStr = list.get(i);
            if (minStrLength > nextStr.length) {
                minStrLength = nextStr.length;
            }
            for (int i1 = 0; i1 < minStrLength; i1++) {
                if (firstStr[i1] != nextStr[i1]) {
                    minStrLength = i1;
                    break;
                }
            }
        }
        String s = "";
        for (int i = 0; i < firstStr.length; i++) {
            s += firstStr[i];
        }
        System.out.println(s);
        return s.substring(0,minStrLength);
    }
}
