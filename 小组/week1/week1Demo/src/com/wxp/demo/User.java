package com.wxp.demo;

public class User {
    private int userId;
    private String username;
    private String password;
    private String userType;
    private String phoneNumber;

    public User(int userId, String username, String password, String userType, String phoneNumber) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
