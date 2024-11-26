package com.travel_agency.api.web.controller;

import com.travel_agency.api.entity.Destination;
import com.travel_agency.api.entity.Review;
import com.travel_agency.api.service.DestinationService;
import com.travel_agency.api.service.ReviewService;
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
    private final ReviewService reviewService;

    @PostMapping()
    public ResponseEntity<Destination> createDestination(@RequestBody Destination data) {
        Destination destination = this.destinationService.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(destination);
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<Review> createReview(@PathVariable UUID id, @RequestBody Review data) {
        Destination destination = this.destinationService.findById(id);
        data.setDestination(destination);

        Review review = reviewService.create(data);

        //Recalculate rating average and save updated target
        destination.calculateAverage();
        this.destinationService.create(destination);

        return ResponseEntity.status(HttpStatus.CREATED).body(review);
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

    @PatchMapping("/reviews/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable UUID id, @RequestBody Review data) {
        Review review = this.reviewService.update(id, data);
        return ResponseEntity.ok().body(review);
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID id) {
        this.reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDestination(@PathVariable UUID id) {
        this.destinationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
