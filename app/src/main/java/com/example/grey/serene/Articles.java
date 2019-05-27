package com.example.grey.serene;


public class Articles {

    private long id;
    private String title;
    private String author;
    private String type;
    private String content;
    private String source;

    public Articles() {

    }


    Articles(long id, String title, String author, String type, String content, String source) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.type = type;
        this.content = content;
        this.source = source;
    }


    Articles(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

}
