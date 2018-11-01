package com.bbear.example.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 *  假设存在相同值时，即 begin。mid。end三值相同时，那么就无法判断是哪个区间
 * @author junxiongchen
 * @date 2018/04/24
 */
public class SearchSpinListII {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(4);
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(4);
        System.out.println(list);
        int solution = solution(list, 2);
        System.out.println(solution);
    }
    private static int solution(List<Integer> list ,int target) {
        int begin = 0;
        int end = list.size() - 1;
        while (begin <= end) {
            int mid = begin + (end - begin) / 2;
            if (list.get(mid) == target) {
                return mid;
            }
            //在同一个递增序列中
            if (list.get(mid) > list.get(begin)) {
                if (list.get(begin) <= target && target < list.get(mid)) {
                    end = mid - 1;
                } else {
                    begin = mid + 1;
                }
            }
            //不在同一个序列中
            if (list.get(mid) < list.get(begin)) {
                if (list.get(mid) < target && target <= list.get(end)) {
                    begin = mid + 1;
                }else {
                    end = mid - 1;
                }
            }else{
                begin++;
            }
        }
        return -1;
    }
}
