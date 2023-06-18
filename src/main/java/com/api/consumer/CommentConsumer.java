package com.api.consumer;

import com.api.conections.messaging.MessagingConstants;
import com.api.dto.CommentDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CommentConsumer implements IConsumer{

    @RabbitListener(queues = MessagingConstants.COMMENT_QUEUE)
    public void consumer(CommentDto commentDto){
        commentDto.text();
    }
}
