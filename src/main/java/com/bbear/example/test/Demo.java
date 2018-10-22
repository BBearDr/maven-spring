package com.bbear.example.test;

import com.bbear.example.design.singleton.DoubleCheck;
import sun.security.provider.MD5;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author junxiongchen
 * @date 2018/08/20
 */
public class Demo {
    public static int num = 397;

    public static void main(String[] args) {
        float a =501 / 500;
        int ceil = (int) Math.ceil((float)1/(float)500);
        System.out.println(a);
        System.out.println(ceil);
    }
    private String checkUrl(String redirectUrl) {
        Pattern DAJIE_URL_PATTERN = Pattern.compile("^((http|https)://)?(\\w+\\.){0,}(dajie\\.com|dajie\\.me|d-j\\.me|51job.com)", 42);
        String redirectUrlLowerCase = redirectUrl.toLowerCase();
        if (!DAJIE_URL_PATTERN.matcher(redirectUrlLowerCase).find()) {
            System.out.println("http://www.dajie.com");
        }
        System.out.println(12);
        return redirectUrl;
    }
    public String encryption(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            String md5 = new BigInteger(1, md.digest()).toString(16).toUpperCase();
            //BigInteger会把0省略掉，需补全至32位
            return fillMD5(md5);
        } catch (Exception e) {
            throw new RuntimeException("MD5加密错误:" + e.getMessage(), e);
        }
    }

    public static String fillMD5(String md5){
        return md5.length()==32?md5:fillMD5("0"+md5);
    }

    public static String md5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] byteArray = inStr.getBytes("UTF-8");
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString().toUpperCase();
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
    }

    private static boolean getB() {
        int result = 0;
        return result == 1;
    }

    public List<String> DataList(String startTime, String endTime) {
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

        num = (num % 10) * 100 + (num / 10 % 10) * 10 + num / 100;
        System.out.println(num);
    }

    public void sortLetters(char[] chars) {
// write your code here
        int i = 0, j = chars.length - 1;
        while (i <= j) {
            while ((i <= j) && Character.isLowerCase(chars[i])) {
                i++;
            }
            while ((i <= j) && Character.isUpperCase(chars[j])) {
                j--;
            }
            if (i <= j) {
                char tmp;
                tmp = chars[i];
                chars[i] = chars[j];
                chars[j] = tmp;
            }
        }
        System.out.println(chars);
        return;
    }
}
