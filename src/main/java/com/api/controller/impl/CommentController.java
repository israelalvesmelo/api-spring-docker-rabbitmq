package com.api.controller.impl;

import com.api.conections.messaging.impl.MessageConstants;
import com.api.controller.IController;
import com.api.dto.CommentDto;
import com.api.service.IMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "comment")
public class CommentController implements IController {

    @Autowired
    private IMessagingService service;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CommentDto commentDto){
        this.service.sendMessage(MessageConstants.COMMENT_QUEUE, commentDto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
