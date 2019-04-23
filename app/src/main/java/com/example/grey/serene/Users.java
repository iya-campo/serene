package com.example.grey.serene;

public class Users {

    private long userID;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private int age;
    private String notifications;
    private String alarm;

    public Users() {

    }

    public Users(long userID, String username, String password, String email, String nickname, int age, String notifications, String alarm) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.age = age;
        this.notifications = notifications;
        this.alarm = alarm;

    }

    public long getID() {
        return this.userID;
    }

    public void setID(long userID) {
        this.userID = userID;
    }

    public String getUsersname() {
        return this.username;
    }

    public void setUsersname(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNotifications() {
        return this.notifications;
    }

    public void setNotifications(String notifications) {
        this.notifications = notifications;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

}


