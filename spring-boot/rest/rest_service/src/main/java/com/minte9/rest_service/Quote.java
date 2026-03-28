/**
 * Resource Representation Class - Greeting
 */

package com.minte9.rest_service;

class Quote {

    private final Integer id;
    private final String content;

    public Quote(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}