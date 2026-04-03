/**
 * RabbitConfig
 * ------------
 * This class defines RabbitMQ infrastructure as Spring bean.
 * 
 * Core RabbitMQ concepts used here:
 * 
 * 1) Queue
 *    A queue stores messages until they are consumed.
 * 
 * 2) Exchnage
 *    Producers usually send messages to an exchange, not directly to a queue.
 *    The exchange decides where the messages goes.
 * 
 * 3) Routing key
 *    A text value attached to the message by the producer.
 *    The exchange uses it to decide which queue should receive the message.
 * 
 * 4) Binding
 *    A rule connecting an exchange to a queue.
 * 
 * Why this config?
 *  - keeps messaging setup centralized
 *  - Spring can auto-create these objects in RabbitMQ on startup
 * 
 * Notes:
 *  - Queue durable true - means that queue definition survives broker restart
 *  - DirectExchange - routes messages to queue by exact routing key match
 */

package com.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitConfig {
 
    public static final String QUEUE_NAME = "demo.queue";
    public static final String EXCHANGE_NAME = "demo.exchange";
    public static final String ROUTING_KEY = "demo.routing.key";

    @Bean
    public Queue demoQueue() {
        return new Queue(QUEUE_NAME, true);  // durable = true
            
    }

    @Bean
    public DirectExchange demoExchange() {
        return new DirectExchange(EXCHANGE_NAME);  // easiest exchange type to understand 
    }

    @Bean
    public Binding demoBinding(Queue demoQueue, DirectExchange demoExchange) {
        return BindingBuilder
                .bind(demoQueue)
                .to(demoExchange)
                .with(ROUTING_KEY);

                // if a message reaches demo.exchange
                // with routing key 'demo.routing.key'
                // send it to demo.queue
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
