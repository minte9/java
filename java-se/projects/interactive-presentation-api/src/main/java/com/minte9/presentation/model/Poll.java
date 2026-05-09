/**
 * Poll
 * ----------------------------------
 * Validation annotations ensure:
 *   - missing options/question -> 400
 */
package com.minte9.presentation.model;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class Poll {

    private String poll_id;

    @NotBlank(message = "Question is required")
    private String question;

    @NotEmpty(message = "Poll must contain at least one options")
    @Valid
    private List<PollOption> options;

    public Poll() {
        this.poll_id = UUID.randomUUID().toString();
    }

    public String getPoll_id() {
        return poll_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<PollOption> getOptions() {
        return options;
    }

    public void setOptions(List<PollOption> options) {
        this.options = options;
    }
}
