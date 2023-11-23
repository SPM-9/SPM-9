package com.fxxkyiwangchangxue.yiwangnochangxue.entity;

/**
 * 用户实体类
 */

public class User {
    private int uid;
    private String userName;
    private String password;
    private String nickName;
    private String email;
    private int permission;
    private int money;

    public User(int uid, String userName, String password, String nickName, String email, int permission, int money) {
        this.uid = uid;
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
        this.permission = permission;
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", permission=" + permission +
                ", money=" + money +
                '}';
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

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
