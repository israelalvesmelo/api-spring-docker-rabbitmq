package com.api.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService implements IMessagingService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String nameQueue, Object message){
        this.rabbitTemplate.convertAndSend(nameQueue, message);
    }
}
