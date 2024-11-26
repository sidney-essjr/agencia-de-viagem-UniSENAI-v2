package com.travel_agency.api.web.controller;

import com.travel_agency.api.entity.Destination;
import com.travel_agency.api.entity.Review;
import com.travel_agency.api.service.DestinationService;
import com.travel_agency.api.service.ReviewService;
import com.travel_agency.api.web.dto.DestinationCreateDto;
import com.travel_agency.api.web.dto.DestinationResponseDto;
import com.travel_agency.api.web.dto.ReviewCreateDto;
import com.travel_agency.api.web.dto.ReviewResponseDto;
import com.travel_agency.api.web.dto.mapper.DestinationMapper;
import com.travel_agency.api.web.dto.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/destinations")
public class DestinationController {
    private final DestinationService destinationService;
    private final ReviewService reviewService;

    @PostMapping()
    public ResponseEntity<Destination> createDestination(@RequestBody DestinationCreateDto data) {
        Destination destination =
                this.destinationService.create(DestinationMapper.toDestination(data));
        return ResponseEntity.status(HttpStatus.CREATED).body(destination);
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<ReviewResponseDto> createReview(@PathVariable UUID id,
                                          @RequestBody ReviewCreateDto data) {
        Destination destination = this.destinationService.findById(id);

        Review review = reviewService.create(destination, ReviewMapper.toReview(data));

        //Recalculate rating average and save updated target
        destination.calculateAverage();
        this.destinationService.create(destination);

        return ResponseEntity.status(HttpStatus.CREATED).body(ReviewMapper.toDto(review));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationResponseDto> findById(@PathVariable UUID id) {
        Destination destination = destinationService.findById(id);
        return ResponseEntity.ok(DestinationMapper.toDto(destination));
    }

    @GetMapping()
    public ResponseEntity<List<DestinationResponseDto>> findAll(@RequestParam int page,
                                                                   @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Destination> destinations = this.destinationService.findAll(pageable);
        return ResponseEntity.ok(DestinationMapper.toListDto(destinations));
    }

    @GetMapping("/search")
    public ResponseEntity<List<DestinationResponseDto>> findByNameOrLocation(@RequestParam String name,
                                                                  @RequestParam String location,
                                                                  @RequestParam int page,
                                                                  @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Destination> destinations = this.destinationService.findByNameOrLocation(name,
                location, pageable);
        return ResponseEntity.ok(DestinationMapper.toListDto(destinations));
    }

    @PatchMapping("/reviews/{id}")
    public ResponseEntity<ReviewResponseDto> updateReview(@PathVariable UUID id,
                                                          @RequestBody ReviewCreateDto data) {
        Review review = this.reviewService.update(id, ReviewMapper.toReview(data));
        return ResponseEntity.ok().body(ReviewMapper.toDto(review));
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
