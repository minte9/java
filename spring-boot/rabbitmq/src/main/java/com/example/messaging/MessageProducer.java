/**
 * MessageProducer
 * ---------------
 * Send messages to RabbitMQ.
 * 
 * Why @Service?
 *  - Marks this class as a Spring-managed bean
 *  - Indicates business/service layer role
 * 
 * RabbitTemplate:
 *  - Main helper class provided by Spring AMQP for sending messages.
 */
package com.example.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.config.RabbitConfig;
import com.example.model.RabbitMessage;

@Service
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(RabbitMessage message) {
        rabbitTemplate.convertAndSend(
            RabbitConfig.EXCHANGE_NAME,
            RabbitConfig.ROUTING_KEY,
            message
        );

        System.out.println(">>> PRODUCER sent message: " + message.getText());
    }
    
}
