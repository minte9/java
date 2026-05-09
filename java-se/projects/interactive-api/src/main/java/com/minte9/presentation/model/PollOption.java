package com.minte9.presentation.model;

import jakarta.validation.constraints.NotBlank;

public class PollOption {
    
    @NotBlank(message = "Option key is required")
    private String key;

    @NotBlank(message = "Option value is required")
    private String value;

    public PollOption() {}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
