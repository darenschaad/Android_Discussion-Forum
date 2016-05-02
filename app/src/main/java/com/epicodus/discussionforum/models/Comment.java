package com.epicodus.discussionforum.models;

/**
 * Created by Guest on 5/2/16.
 */
public class Comment {
    String title;
    String body;
    String date;
    String imageUrl;
    String user;

    public Comment() {}

    public Comment(String title, String body, String date, String imageUrl, String user) {
        this.title = title;
        this.body = body;
        this.date = date;
        this.imageUrl = imageUrl;
        this.user = user;
    }

    public String getTitle() { return title; }

    public String getBody() { return body; }

    public String getDate() { return date; }

    public String getImageUrl() { return imageUrl; }

    public String getUser() { return user; }
}
