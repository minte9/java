package com.minte9.presentation.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

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
}
