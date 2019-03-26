package com.example.grey.serene;

public class Users {
    private long userID;

    private String userName;

    private String email;

    private String userNickName;

    private int age;

    private String alarm;

    private String notifications;

    private String password;

    private String alarmName;

    private Float th;

    // constructors

    public Users() {}

    public Users(long id, String userName,String email, String userNickName, int age, String alarmName, String alarm, String notifications, String password) {

        this.userID = id;

        this.userName = userName;

        this.email=email;

        this.userNickName=userNickName;

        this.age = age;

        this.notifications=notifications;

        this.password=password;

        this.alarm=alarm;

        this.alarmName = alarmName;

    }

    public Users(String userName,String email, String password) {

        this.userName = userName;

        this.email=email;

        this.password=password;

    }

    public Users(String userNickName,int age) {

        this.userNickName = userNickName;

        this.age=age;

    }

    public Users(String notifications,String alarm) {

        this.notifications = notifications;

        this.alarm=alarm;

    }

    // properties

    public void setID(long id) {

        this.userID = id;

    }

    public long getID() {

        return this.userID;

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUsersName(String username) {

        this.userName = username;

    }

    public String getUsersName() {

        return this.userName;

    }

    public void setUserNickName(String userNickname) {

        this.userNickName = userNickname;

    }

    public String getUserNickName() {

        return this.userNickName;

    }

    public void setAge(int age) {

        this.age = age;

    }

    public int getAge() {

        return this.age;

    }

    public void setAlarm(String alarm) {

        this.alarm = alarm;

    }

    public String getAlarm() {

        return this.alarm;

    }

    public void setPassword(String password) {

        this.password = password;

    }

    public String getPassword() {

        return this.password;

    }

    public void setNotifications(String notifications) {

        this.notifications = notifications;

    }

    public String getNotifications() {

        return this.notifications;

    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public Float getTh() {
        return th;
    }

    public void setTh(Float th) {
        this.th = th;
    }

}


