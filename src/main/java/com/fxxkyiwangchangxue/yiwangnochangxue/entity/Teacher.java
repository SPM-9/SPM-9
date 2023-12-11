package com.fxxkyiwangchangxue.yiwangnochangxue.entity;

import java.io.Serializable;

public class Teacher implements Serializable {
    private int teacherId;
    private String userName;
    private String password;
    private String nickName;
    private String email;
    private int money;

    public Teacher (int teacherId, String userName, String password, String nickName, String email, int money) {
        this.teacherId = teacherId;
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
        this.money = money;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "teacherId=" + teacherId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", money=" + money +
                '}';
    }
}
