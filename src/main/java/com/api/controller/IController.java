package com.api.controller;

import com.api.dto.CommentDto;
import org.springframework.http.ResponseEntity;

public interface IController {

    public ResponseEntity create(CommentDto commentDto);

}