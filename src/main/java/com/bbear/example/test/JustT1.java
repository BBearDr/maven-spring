package com.bbear.example.test;


import org.apache.commons.lang3.time.DateUtils;

import javax.servlet.jsp.tagext.TryCatchFinally;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author junxiongchen
 * @date 2018/03/29
 */
public class JustT1 {
    static JustT1 justT1 = new JustT1();
    public static void main(String[] args) {
        //commit but not push
        String s = String.valueOf(null);
        System.out.println(s);
    }
    private void test() {
        String str = "adc";
        try {
            int i = Integer.parseInt(str);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

