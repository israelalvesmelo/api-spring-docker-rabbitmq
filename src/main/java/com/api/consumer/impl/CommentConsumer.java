package com.api.consumer.impl;

import com.api.conections.messaging.impl.MessageConstants;
import com.api.consumer.IConsumer;
import com.api.dto.CommentDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CommentConsumer implements IConsumer {

    @RabbitListener(queues = MessageConstants.COMMENT_QUEUE)
    public void consumer(CommentDto commentDto){
        System.out.println("Consumer " + commentDto.getAuthor());
    }
}
