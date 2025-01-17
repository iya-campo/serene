package com.example.grey.serene;

public class Users {

    private long id;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private int age;
    private String notifications;
    private String alarm;
    private String interpreter;
    private String startDate;
    private String alarmTime;

    public Users() {

    }

    public Users(long id, String username, String password, String email, String nickname, int age, String notifications, String alarm, String alarmTime, String interpreter, String startDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.age = age;
        this.notifications = notifications;
        this.alarm = alarm;
        this.alarmTime = alarmTime;
        this.interpreter = interpreter;
        this.startDate = startDate;
    }

    public Users(String nickname, String email, String password){
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public long getID() {
        return this.id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
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

    public String getInterpreter() {
        return interpreter;
    }

    public void setInterpreter(String interpreter) {
        this.interpreter = interpreter;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getAlarmTime(){return alarmTime;}

    public void setAlarmTime(String alarmTime){ this.alarmTime = alarmTime; }

}


