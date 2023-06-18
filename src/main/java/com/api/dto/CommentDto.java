package com.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CommentDto implements Serializable {
    @NotEmpty
    @Size(min = 3, message = "content should have at least 3 characters")
    private String content;

    @NotEmpty
    @Size(min = 2, message = "author should have at least 2 characters")
    private String author;

    public CommentDto(String content, String author){
        this.content = content;
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }


}
