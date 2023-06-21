package com.api.conections.messaging.impl;

import com.api.conections.messaging.IMessageConnection;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
public class RabbitMQConnection implements IMessageConnection {
    private final AmqpAdmin amqpAdmin;

    @Autowired
    public RabbitMQConnection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue createQueue(String name){
        return new Queue(name, true, false, false);
    }

    private DirectExchange createDirectExchange(String name){
        return new DirectExchange(name);
    }

    private Binding createBinding(Queue queue, DirectExchange exchange){
        //Creating relationship -> The exchange redirects the message to the queue
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    public void createConnection(){
        Queue queue = this.createQueue(MessageConstants.COMMENT_QUEUE);

        DirectExchange exchange = this.createDirectExchange(MessageConstants.EXCHANGE_NAME);

        Binding binding = this.createBinding(queue, exchange);

        //Creating queues in RabbitMQ
        this.amqpAdmin.declareQueue(queue);
        //Creating exchange in RabbitMQ
        this.amqpAdmin.declareExchange(exchange);
        //Creating relationship between queue and exchange in RabbitMQ
        this.amqpAdmin.declareBinding(binding);
    }
}
