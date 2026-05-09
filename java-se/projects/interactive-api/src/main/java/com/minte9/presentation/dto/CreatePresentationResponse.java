package com.minte9.presentation.dto;

public class CreatePresentationResponse {
    
    private String presentation_id;

    public CreatePresentationResponse(String presentation_id) {
        this.presentation_id = presentation_id;
    }

    public String getPresentation_id() {
        return presentation_id;
    }
}
