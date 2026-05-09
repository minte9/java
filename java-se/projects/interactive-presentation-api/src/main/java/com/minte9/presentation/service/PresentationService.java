package com.minte9.presentation.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.minte9.presentation.exception.NoCurrentPollException;
import com.minte9.presentation.exception.NoMorePollException;
import com.minte9.presentation.exception.PresentationNotFoundException;
import com.minte9.presentation.model.Poll;
import com.minte9.presentation.model.Presentation;
import com.minte9.presentation.repository.PresentationRepository;

@Service
public class PresentationService {
    
    private final PresentationRepository repository;

    public PresentationService(PresentationRepository repository) {
        this.repository = repository;
    }

    public String save(Presentation presentation) {
        return repository.save(presentation);
    }

    public Optional<Presentation> findById(String presentationId) {
        return repository.findById(presentationId);
    }

    public Poll showNextPoll(String presentationId) {
        Presentation presentation = repository.findById(presentationId)
            .orElseThrow(PresentationNotFoundException::new);

        int nextPollIndex = presentation.getCurrent_poll_index() + 1;
        if (nextPollIndex >= presentation.getPolls().size()) {
            throw new NoMorePollException();
        }

        presentation.setCurrent_poll_index(nextPollIndex);  // update current poll

        Poll nextPoll = presentation.getPolls().get(nextPollIndex);
        return nextPoll;
    }

    public Poll getCurrentPoll(String presentationId) {
        Presentation presentation = repository.findById(presentationId)
            .orElseThrow(PresentationNotFoundException::new);

        int currentIndex = presentation.getCurrent_poll_index();

        if (currentIndex < 0) {
            throw new NoCurrentPollException();
        }

        Poll currentPoll = presentation.getPolls().get(currentIndex);
        return currentPoll;
    }
}
