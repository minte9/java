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

    @PutMapping("/{presentationId}/polls/current")
    public ResponseEntity<PollResponse> showNextPoll(
        @PathVariable("presentationId") String presentationId
    ) {
        Poll currentPoll = presentationService.showNextPoll(presentationId);
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
        Poll currentPoll = presentationService.getCurrentPoll(presentationId);
        return ResponseEntity.ok(
                new PollResponse(
                    currentPoll.getPoll_id(),
                    currentPoll.getQuestion(),
                    currentPoll.getOptions()
                )
        );
    }
}
