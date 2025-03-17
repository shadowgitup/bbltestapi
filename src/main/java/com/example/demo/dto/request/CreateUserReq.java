package com.example.demo.dto.request;

import jakarta.validation.constraints.NotNull;


public class CreateUserReq {

    @NotNull(message = "User Id cannot be null")
    private int userId;

    private int id;

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Body cannot be null")
    private String body;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    
    
}
