package com.minte9.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.minte9.presentation.dto.CreatePresentationRequest;
import com.minte9.presentation.dto.CreatePresentationResponse;
import com.minte9.presentation.dto.PollResponse;
import com.minte9.presentation.model.Poll;
import com.minte9.presentation.model.Presentation;
import com.minte9.presentation.service.PresentationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/presentations")
public class PresentationController {
    
    private final PresentationService presentationService;

    public PresentationController(PresentationService presentationService) {
        this.presentationService = presentationService;
    }

    /**
     * Creates a new presentation.
     * 
     * Validation:
     *   - Request must contain at least one poll
     *   - Polls must contain question and options
     * 
     * Returns:
     *   - 201 Created with generated presentation_id
     *   - 400 Bad request if validation fails 
     */
    @PostMapping
    public ResponseEntity<CreatePresentationResponse> createPresentation(
        @Valid @RequestBody CreatePresentationRequest request
    ) {
        Presentation presentation = new Presentation();
        presentation.setPolls(request.getPolls());

        String presentationId = presentationService.save(presentation);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreatePresentationResponse(presentationId));
    }

    /**
     * Moves presentation to the next poll (slide).
     * 
     * Logic:
     *   - Each call advances current_poll_index
     *   - First call moves -1 to 0
     * 
     * Returns:
     *   - 200 OK with current poll
     *   - 404 Not Found if presentation does not exist
     *   - 409 Conflict if no more polls are available
     */
    @PutMapping("/{presentationId}/polls/current")
    public ResponseEntity<PollResponse> showNextPoll(
        @PathVariable("presentationId") String presentationId
    ) {

        // Retrive presentation of return 404 if not found
        Presentation presentation = presentationService.findById(presentationId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // Calculate next poll index                    
        int nextPollIndex = presentation.getCurrent_poll_index() + 1;

        // If no more polls, return 409 Conflict
        if (nextPollIndex >= presentation.getPolls().size()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        // Update current poll
        presentation.setCurrent_poll_index(nextPollIndex);

        Poll currentPoll = presentation.getPolls().get(nextPollIndex);

        // Return poll without votes (as required by API/tests)
        return ResponseEntity.ok(
                new PollResponse(
                    currentPoll.getPoll_id(),
                    currentPoll.getQuestion(),
                    currentPoll.getOptions()
                )
        );
    }

    @GetMapping("/{presentationId}/polls/current")
    public ResponseEntity<PollResponse> getCurrentPoll(
        @PathVariable("presentationId") String presentationId
    ) {
        Presentation presentation = presentationService.findById(presentationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        int currentIndex = presentation.getCurrent_poll_index();

        if (currentIndex < 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        Poll currentPoll = presentation.getPolls().get(currentIndex);

        return ResponseEntity.ok(
                new PollResponse(
                    currentPoll.getPoll_id(),
                    currentPoll.getQuestion(),
                    currentPoll.getOptions()
                )
        );
    }
}
