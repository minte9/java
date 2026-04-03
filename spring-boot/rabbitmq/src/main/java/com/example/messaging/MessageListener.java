/**
 * MessageListener
 * ---------------
 * Receive messages from RabbitMQ.
 * 
 * Why @Component?
 *  - marks this class as a Spring bean.
 *  - enough when the class is a generic managed component
 * 
 * RabbitLitener:
 *  - listen to this queue, and whenever a message arrives,
 *    call this method automatically
 * 
 * This is event-driven programming:
 *  - no manual polling
 *  - no while loop checking the queue
 *  - RabbitMQ pushes work to the listener
 */
package com.example.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.config.RabbitConfig;
import com.example.model.RabbitMessage;

@Component
public class MessageListener {

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receiveMessage(RabbitMessage message) 
                    throws InterruptedException {

        Thread.sleep(5000); // simulate slow processing
        System.out.println("<<< CONSUMER received message: " + message.getText());
    }
}
