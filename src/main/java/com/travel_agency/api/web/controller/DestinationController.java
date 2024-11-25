package com.travel_agency.api.web.controller;

import com.travel_agency.api.entity.Destination;
import com.travel_agency.api.service.DestinationService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/destinations")
public class DestinationController {
    private final DestinationService destinationService;

    @PostMapping()
    public ResponseEntity<Destination> create(@RequestBody Destination data) {
        Destination destination = this.destinationService.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(destination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destination> findById(@PathVariable UUID id) {
        Destination destination = destinationService.findById(id);
        return ResponseEntity.ok(destination);
    }

    @GetMapping()
    public ResponseEntity<Page<Destination>> findAll(@RequestParam int page,
                                                     @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Destination> destinations = this.destinationService.findAll(pageable);
        return ResponseEntity.ok(destinations);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Destination>> findByNameOrLocation(@RequestParam String name,
                                                                  @RequestParam String location,
                                                                  @RequestParam int page,
                                                                  @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Destination> destinations = this.destinationService.findByNameOrLocation(name,
                location, pageable);
        return ResponseEntity.ok(destinations);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id ) {
        this.destinationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
