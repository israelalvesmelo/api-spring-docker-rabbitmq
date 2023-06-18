package com.api.conections.messaging;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class RabbitMQConnection {
    private AmqpAdmin amqpAdmin;

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
    private void add(){
        Queue queue = this.createQueue(MessagingConstants.COMMENT_QUEUE);

        DirectExchange exchange = this.createDirectExchange(MessagingConstants.EXCHANGE_NAME);

        Binding binding = this.createBinding(queue, exchange);

        //Creating queues in RabbitMQ
        this.amqpAdmin.declareQueue(queue);
        //Creating exchange in RabbitMQ
        this.amqpAdmin.declareExchange(exchange);
        //Creating relationship between queue and exchange in RabbitMQ
        this.amqpAdmin.declareBinding(binding);
    }
}
