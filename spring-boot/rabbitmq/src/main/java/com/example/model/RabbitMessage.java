/**
 * RabbitMessage
 * -------------
 * This is a simple POJO (Plain Old Java Object) 
 * used as the message payload.
 * 
 * Why have a class instead of sending raw String?
 *  - more realistic
 *  - easier to extend later
 *  - easier to map from JSON request body
 * 
 * String / Jackson can convert JSON <-> Java object automatically 
 * as long as getters/setters and default constructor exist. 
 */

package com.example.model;

public class RabbitMessage {
    
    private String text;

    public RabbitMessage() {
    }

    public RabbitMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
