/**
 * PollResponse
 * -----------------------------------------------------------
 * Response DTO without votes field (not required by API tests)
 */
package com.minte9.presentation.dto;

import java.util.List;

import com.minte9.presentation.model.PollOption;

public class PollResponse {
    
    private String poll_id;
    private String question;
    private List<PollOption> options;

    public PollResponse(String poll_id, String question, List<PollOption> options) {
        this.poll_id = poll_id;
        this.question = question;
        this.options = options;
    }

    public String getPoll_id() {
        return poll_id;
    }

    public String getQuestion() {
        return question;
    }

    public List<PollOption> getOptions() {
        return options;
    }
}
