package com.bbear.example.pojo;

import org.springframework.context.annotation.Bean;

/**
 * @author junxiongchen
 * @date 2017/11/24
 */
public class UserPOJO {
    /**
     * 用户名
     */
    String UserName;
    /**
     * 用户密码
     */
    String PassWord;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    @Override
    public String toString() {
        return "UserPOJO{" +
                "UserName='" + UserName + '\'' +
                ", PassWord='" + PassWord + '\'' +
                '}';
    }
}
