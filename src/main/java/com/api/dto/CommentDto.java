package com.api.dto;

import java.io.Serializable;

public class CommentDto implements Serializable {
    private String content;
    private String author;

    public CommentDto(String content, String author){
        this.content = content;
        this.author = author;
    }
    public void text(){
        System.out.println("------------------------");
        System.out.println(this.content);
        System.out.println(this.author);
    }

}
