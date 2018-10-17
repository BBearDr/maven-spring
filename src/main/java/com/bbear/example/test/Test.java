package com.bbear.example.test;

import com.dajie.common.util.Base64;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author junxiongchen
 * @date 2018/10/08
 */
public class Test {

    public static void main(String[] args) {
/*        Queue<SmsRequest> queue = new LinkedList<>();
        while (true) {
            SmsRequest smsRequest = new SmsRequest("id", "name", "address");
            queue.add()
        }*/
        String str = "http://plank.dajie.com/redirect/toUrl?id=1&plank_mobile=aHR0cDovL3d3dy5iYWlkdS5jb20_ZnJvbT1lZG1fY2xpY2smbV90cGxfaWQ9MTA4MjY*&plank_pc=aHR0cDovL3d3dy4xMjMwNi5jbj9mcm9tPWVkbV9jbGljayZtX3RwbF9pZD0xMDgyNg**";
        String s = Base64.encode(str.getBytes());
        System.out.println(s);
        try {
            String s1 = new String(Base64.decode("aHR0cDovL2dpdGh1Yi5j\n" +
                    "b20/ZnJvbT1lZG1fY2xpY2smbV90cGxfaWQ9MTEzNjg="));
            System.out.println(s1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
