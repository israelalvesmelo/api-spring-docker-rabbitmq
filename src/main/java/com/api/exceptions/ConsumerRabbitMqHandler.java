package com.api.exceptions;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.util.ErrorHandler;

public class ConsumerRabbitMqHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable throwable) {

        String nameQueue = ((ListenerExecutionFailedException) throwable).getFailedMessage().getMessageProperties().getConsumerQueue();
        String message = new String(((ListenerExecutionFailedException) throwable).getFailedMessage().getBody());
        String error = throwable.getCause().getMessage();

        throw new AmqpRejectAndDontRequeueException(String.format("message [%s] must not return to queue [%s]. Error: %s", message, nameQueue, error));
    }
}
