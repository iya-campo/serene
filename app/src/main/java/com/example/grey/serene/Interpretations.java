package com.example.grey.serene;


public class Interpretations {

    private String date;
    private String content;

    public Interpretations() {

    }

    Interpretations(String date, String content) {
        this.date = date;
        this.content = content;
    }

    Interpretations(String title) {
        this.date = title;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
