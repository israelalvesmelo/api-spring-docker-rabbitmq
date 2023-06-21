package com.api.service.impl;

import com.api.service.IMessagingService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService implements IMessagingService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String nameQueue, Object message){
        this.rabbitTemplate.convertAndSend(nameQueue, message);
    }
}
