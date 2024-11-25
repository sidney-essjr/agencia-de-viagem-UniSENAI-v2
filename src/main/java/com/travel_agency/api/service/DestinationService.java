package com.travel_agency.api.service;

import com.travel_agency.api.entity.Destination;
import com.travel_agency.api.repository.DestinationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DestinationService {
    private final DestinationRepository destinationRepository;

    @Transactional
    public Destination findById(UUID id) {
        return this.destinationRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("The destination id %s was not found", id)));
    }

    @Transactional
    public Destination create(Destination destination) {
        return this.destinationRepository.save(destination);
    }

    @Transactional
    public Page<Destination> findAll(Pageable pageable) {
        return destinationRepository.findAll(pageable);
    }

    @Transactional
    public Page<Destination> findByNameOrLocation(String name, String location, Pageable pageable) {
       return this.destinationRepository.findByNameContainingIgnoreCaseOrLocationContainingIgnoreCase(name, location, pageable);
    }
}
