package com.bbear.example.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author junxiongchen
 * @date 2018/08/20
 */
public class Demo {
    public static int num = 397;
    public static void main(String[] args) {
        do {
            System.out.println(123);
        } while (true);
    }
    private static boolean getB(){
        int result =0;
        return result == 1;
    }

    public List<String> DataList(String startTime, String endTime){
        List<String> list = new ArrayList<String>();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(startTime);
            Date end = format.parse(endTime);
            if (end.before(start)) {
                return null;
            } else if (end.equals(start)) {
                list.add(endTime);
            } else {
                Calendar tempStart = Calendar.getInstance();
                tempStart.setTime(start);
                Calendar tempEnd = Calendar.getInstance();
                tempEnd.setTime(end);
                while (tempStart.before(tempEnd)) {
                    list.add(format.format(tempStart.getTime()).toString());
                    tempStart.add(Calendar.DAY_OF_YEAR, 1);
                }
                list.add(endTime);
            }
            return list;
        } catch (Exception e) {
        }

        return list;
    }

    private static void solution(int num) {

        num = (num%10)* 100 + (num/10%10)*10 + num/100 ;
        System.out.println(num);
    }
    public void sortLetters(char[] chars) {
// write your code here
        int i=0,j=chars.length-1;
        while(i<=j)
        {
            while((i<=j)&&Character.isLowerCase(chars[i]))
            {
                i++;
            }
            while((i<=j)&&Character.isUpperCase(chars[j]))
            {
                j--;
            }
            if(i<=j)
            {
                char tmp;
                tmp=chars[i];
                chars[i]=chars[j];
                chars[j]=tmp;
            }
        }
        System.out.println(chars);
        return;
    }
}
