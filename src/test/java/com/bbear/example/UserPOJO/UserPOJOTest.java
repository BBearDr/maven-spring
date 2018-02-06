package com.bbear.example.UserPOJO;

import com.bbear.example.pojo.UserPOJO;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author junxiongchen
 * @date 2017/11/28
 */
public class UserPOJOTest {
    @Test
    public void test(){
        System.out.println(123);
        ApplicationContext apc = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");
        UserPOJO user = (UserPOJO)apc.getBean("user");
        user.setUserName("Chenjx");
        user.setPassWord("123");
        UserPOJO user1 = (UserPOJO)apc.getBean("user");
        user1.setUserName("Chenjx1");
        user1.setPassWord("1231");
        System.out.println(user.toString());
        System.out.println(user1.toString());
    }
}
