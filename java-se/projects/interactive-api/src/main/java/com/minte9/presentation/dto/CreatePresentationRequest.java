package com.minte9.presentation.dto;

import java.util.List;

import com.minte9.presentation.model.Poll;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public class CreatePresentationRequest {
    
    @NotEmpty(message = "Presentation must contain at least one poll")
    @Valid
    private List<Poll> polls;

    public CreatePresentationRequest() {}

    public List<Poll> getPolls() {
        return polls;
    }

    public void setPolls(List<Poll> polls) {
        this.polls = polls;
    }
}
