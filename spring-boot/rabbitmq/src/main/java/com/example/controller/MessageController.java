/**
 * MessageController
 * -----------------
 * Expose HTTP endpoint so we can trigger RabbitMQ messaging
 * from a browser, Postman, curl, etc.
 */
package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.example.messaging.MessageProducer;
import com.example.model.RabbitMessage;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    
    private final MessageProducer messageProducer;

    public MessageController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    /**
     * POST /api/message
     * 
     * Example JSON request body:
     * {
     *   "text": "Hello RabbitMQ"
     * }
     * 
     * @RequestBody
     * Tells Spring to read JSON from HTTP body
     * and convert it into RabbitMessage object.
     */
    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody RabbitMessage message) {
        messageProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ: " + message.getText());
    }
}
