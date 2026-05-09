/**
 * Presentation
 * ----------------------------------
 * Validation annotations ensure:
 *   - missing polls -> 400
 *   - missing options/questions -> 400
 * 
 * Note:
 *   - current_poll_index = -1  // first PUT moves index 0
 */
package com.minte9.presentation.model;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public class Presentation {
    
    private int current_poll_index = 0;  // important for the "next poll" logic

    @NotEmpty
    @Valid
    private List<Poll>polls;

    public Presentation() {}

    public int getCurrent_poll_index() {
        return current_poll_index;
    }

    public void setCurrent_poll_index(int current_poll_index) {
        this.current_poll_index = current_poll_index;
    }

    public List<Poll> getPolls() {
        return polls;
    }

    public void setPolls(List<Poll> polls) {
        this.polls = polls;
    }
}
