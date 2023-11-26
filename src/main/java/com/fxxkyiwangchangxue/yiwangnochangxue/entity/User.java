package com.fxxkyiwangchangxue.yiwangnochangxue.entity;

import java.io.Serializable;

/**
 * 用户实体类
 */

public class User implements Serializable {
    private int uid;
    private String userName;
    private String password;
    private String nickName;
    private String email;
    private int money;
    private boolean isChosenCourse;

    public User(String userName, String password, String nickName, String email) {
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
    }

    public User(int uid, String userName, String password, String nickName, String email, int permission, int money, boolean isChosenCourse) {
        this.uid = uid;
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
        this.money = money;
        this.isChosenCourse = isChosenCourse;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", money=" + money +
                '}';
    }

    public boolean isChosenCourse() {
        return isChosenCourse;
    }

    public void setChosenCourse(boolean chosenCourse) {
        isChosenCourse = chosenCourse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public User() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
