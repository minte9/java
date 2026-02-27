package com.example;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageController {
    
    private final MessageRepository repository;

    public MessageController(MessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/hello")
    public String hello() {
        Message message = new Message("Hello World from Oracle");
        repository.save(message);
        
        return repository.findById(message.getId())
                //.map(Message::getText)
                .map(m -> "ID: " + m.getId() + ", Text: " + m.getText())
                .orElse("Not found");
    }
}