package com.example.grey.serene;

public class Journal {
    private long id;
    private int hours_slept;
    private String food_intake;
    private String medicinal_intake;
    private String date;
    private String content;
    private long user_id;

    Journal(){}

    Journal(long id, int hours_slept, String food_intake, String medicinal_intake, String date, String content, long user_id){
        this.id = id;
        this.hours_slept = hours_slept;
        this.food_intake = food_intake;
        this.medicinal_intake = medicinal_intake;
        this.date = date;
        this.content = content;
        this.user_id = user_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHours_slept() {
        return hours_slept;
    }

    public void setHours_slept(int hours_slept) {
        this.hours_slept = hours_slept;
    }

    public String getFood_intake() {
        return food_intake;
    }

    public void setFood_intake(String food_intake) {
        this.food_intake = food_intake;
    }

    public String getMedicinal_intake() {
        return medicinal_intake;
    }

    public void setMedicinal_intake(String medicinal_intake) {
        this.medicinal_intake = medicinal_intake;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
