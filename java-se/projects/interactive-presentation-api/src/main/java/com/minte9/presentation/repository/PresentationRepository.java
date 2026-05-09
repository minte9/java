package com.minte9.presentation.repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.minte9.presentation.model.Presentation;

@Repository
public class PresentationRepository {
    
     /** In-memory storage for presentations.
     * 
     * ConcurrentHashMap insteed of HashMap because:
     *   - It is thread-safe
     *   - Multiple HTTP request can access/modify the map concurrently
     *   - Avoids race conditions without requiring explicit synchronization
     */
    private final Map<String, Presentation> presentations = new ConcurrentHashMap<>();

    public String save(Presentation presentation) {
        String presentationId = UUID.randomUUID().toString();
        presentations.put(presentationId, presentation);
        return presentationId;
    }

    /**
     * Returns a Presentation wrapped in Optional.
     * 
     * Optional:
     *   - The value may or many not be present
     *   - Avoids returning null and reduse risk of NullPointerException 
     */
    public Optional<Presentation> findById(String presentationId) {
        return Optional.ofNullable(presentations.get(presentationId));
    }
}
